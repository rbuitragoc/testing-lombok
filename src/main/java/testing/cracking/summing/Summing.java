package testing.cracking.summing;

/**
 * Credits to https://prismoskills.appspot.com/lessons/Bitwise_Operators/Sum_using_only_bitwise_ops.jsp
 */
public class Summing {
  
  public static void main(String ...strings) {
    new Summing().add(4, 4);
  }
  
  private void printBinary(int num) {
    int n = num;
    String s = "";
    for (int i = 0; i < 8; i++) {
      s = (n & 1) + s;
      n = n >> 1;
    }
    System.out.println(num + " = " + s);
  }
  
  private int add(int i, int j) {
    // Debug code
    printBinary(i);
    printBinary(j);
    System.out.println();
    
    // quick exits:
    if (i == 0) {
      return j;
    }
    
    if (j == 0) {
      return i;
    }

    // Actual algorithm
    int uncommonBitsFromBoth = i ^ j;
    int commonBitsFromBoth = i & j;

    if (commonBitsFromBoth == 0)
      return uncommonBitsFromBoth;

    return add(uncommonBitsFromBoth, commonBitsFromBoth << 1);
  }

}
