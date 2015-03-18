package staticInnerClass;

import java.util.Arrays;

/**
 * This program demonstrates the use of static inner classes.
 */
public class StaticInnerClassTest
{
   public static void main(String[] args)
   {
     int[] d = new int[20];
      for (int i = 0; i < d.length; i++){
    	  d[i] = (int) (100 * Math.random());
      }
      System.out.println(Arrays.toString(d));
      ArrayAlg.Pair p = ArrayAlg.minmax(d);
      System.out.println("min = " + p.getFirst());
      System.out.println("max = " + p.getSecond());
   }
}