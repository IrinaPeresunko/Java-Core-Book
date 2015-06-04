package indexableHashMapImplementation;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class IndexableHashMapImplementation<K, V> extends java.util.HashMap<K, V> {

  private static final long serialVersionUID = 1L;

  public static <K, V> IndexableHashMap<K, V> unmodifiable(IndexableHashMap<K, V> map) {
    return map.setImmutable(true);
  }

  private boolean immutable;
  private Map<MapIndex<?, V>, Multimap<Object, V>> idxMap; // immutable

  public IndexableHashMapImplementation(MapIndex<?, V>... indexes) {
    this(Arrays.asList(indexes));
  }

  public IndexableHashMapImplementation(Collection<MapIndex<?, V>> indexes) {
    init(indexes);
  }

  private void init(Collection<MapIndex<?, V>> indexes) {
    Map<MapIndex<?, V>, Multimap<Object, V>> newIdxMap = new HashMap<>();
    for (MapIndex<?, V> index : indexes) {
      Multimap<Object, V> idxKey2Val = ArrayListMultimap.create();
      newIdxMap.put(index, idxKey2Val);
    }
    idxMap = Collections.unmodifiableMap(newIdxMap);
  }

  private IndexableHashMap<K, V> setImmutable(boolean immutable) {
    this.immutable = immutable;
    return this;
  }

  private void checkMutable() {
    if (immutable) {
      throw new UnsupportedOperationException("IndexableHashMap " + this + " is immutable!");
    }
  }

  @Override
  public V put(K key, V value) {
    checkMutable();
    for (Map.Entry<MapIndex<?, V>, Multimap<Object, V>> e : idxMap.entrySet()) {
      Object idxKey = e.getKey().val2IdxKey(value);
      e.getValue().put(idxKey, value);
    }
    return super.put(key, value);
  }

  @Override
  public void putAll(Map<? extends K, ? extends V> m) {
    checkMutable();
    // since Java8 we can no no longer delegate to super...
    for (java.util.Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
      put(entry.getKey(), entry.getValue());
    }
  }


  @Override
  public V remove(Object key) {
    checkMutable();
    V removed = super.remove(key);
    for (Map.Entry<MapIndex<?, V>, Multimap<Object, V>> e : idxMap.entrySet()) {
      Object idxKey = e.getKey().val2IdxKey(removed);
      e.getValue().remove(idxKey, removed);
    }
    return removed;
  }

  @Override
  public void clear() {
    checkMutable();
    for (Multimap<Object, V> idxVals : idxMap.values()) {
      idxVals.clear();
    }
    super.clear();
  }

  public Set<MapIndex<?, V>> getIndices() {
    return idxMap.keySet();
  }

  public <IK> Collection<V> getByIdx(MapIndex<?, V> index, IK idxKey) {
    return idxMap.get(index).get(idxKey);
  }

  public <IK> V getByUniqueIdx(MapIndex<?, V> idx, IK idxKey) {
    Collection<V> vals = getByIdx(idx, idxKey);
    if (vals == null || vals.isEmpty()) {
      return null;
    } else if (vals.size() == 1) {
      return vals.iterator().next();
    } else if (!idx.isUnique()) {
      throw new IllegalArgumentException("Index " + idx + " not unique! Values for key " + idxKey + ": " + vals);
    } else {
      throw new IllegalStateException("Multiple values found for unique index " + idx + "! Values for key " + idxKey + ": " + vals);
    }
  }

  public static abstract class MapIndex<IK, V> {
    private final boolean unique;

    public MapIndex(boolean unique) {
      this.unique = unique;
    }

    protected abstract IK val2IdxKey(V value);

    public boolean isUnique() {
      return unique;
    }

    @Override
    public String toString() {
      return getClass().getSimpleName();
    }
  }

}


Usage:

  public static class PrefixIndex extends MapIndex<String, String> {
    private final int prefixLength;

    public PrefixIndex(int prefixLength) {
      super(false);
      this.prefixLength = prefixLength;
    }

    @Override
    protected String val2IdxKey(String value) {
      return value == null ? "" : value.substring(0, Math.min(prefixLength, value.length()));
    }

  }

  @Test
  public void testUsingIndex() {
    PrefixIndex index = new PrefixIndex(1);
    IndexableHashMap<String, String> map = new IndexableHashMap<String, String>(index);
    map.put("Key1", "AX-Val");
    map.put("Key2", "BY-Val");
    map.put("Key3", "BZ-Val");
    assertEquals("AX-Val", map.get("Key1"));
    assertEquals("BY-Val", map.get("Key2"));
    assertEquals("BZ-Val", map.get("Key3"));
    assertEquals(1, map.getByIdx(index, "A").size());
    assertEquals(2, map.getByIdx(index, "B").size());
  }