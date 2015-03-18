package staticInnerClass;

class ArrayAlg
{
   /**
    * A pair of int-point numbers
    */
   public static class Pair
   {
      /**
       * Constructs a pair from two int-point numbers
       * @param f the first number
       * @param s the second number
       */
      public Pair(int f, int s)
      {
         first = f;
         second = s;
      }

      /**
       * Returns the first number of the pair
       * @return the first number
       */
      public int getFirst()
      {
         return first;
      }

      /**
       * Returns the second number of the pair
       * @return the second number
       */
      public double getSecond()
      {
         return second;
      }

      private int first;
      private int second;
   }

   /**
    * Computes both the minimum and the maximum of an array
    * @param d an array of int-point numbers
    * @return a pair whose first element is the minimum and whose second element
    * is the maximum
    */
   public static Pair minmax(int[] d)
   {
      int min = Integer.MAX_VALUE;
      int max = Integer.MIN_VALUE;
      for (int v : d)
      {
         if (min > v) min = v;
         if (max < v) max = v;
      }
      return new Pair(min, max);
   }
}

