package wallet;

import java.util.*;

public class Validatior {
  public static boolean validation(long credit_number) {
    return (sizeCheck(credit_number)>= 13 && sizeCheck(credit_number) <= 16 &&
        (prefixCheck(credit_number, 4) || prefixCheck(credit_number, 5) ||
            prefixCheck(credit_number, 37) || prefixCheck(credit_number, 6)) &&
        (sumOfEven(credit_number)+sumOfOdd(credit_number)) % 10 == 0);
  }
  public static int sizeCheck(long c_num) {
    String num = c_num+"";
    return num.length();
  }
  public static boolean prefixCheck(long c_num, int d) {
    return getPrefix(c_num, sizeCheck(d)) == d;
  }
  public static long getPrefix(long c_num, int k) {
    if(sizeCheck(c_num)>k) {
      String num = c_num + "";
      return Long.parseLong(num.substring(0, k));
    }
    return c_num;
  }
  public static int sumOfOdd(long c_num) {
    int sum = 0;
    String num = c_num + "";
    for(int i = sizeCheck(c_num)-1; i >= 0; i -= 2) {
      sum += Integer.parseInt(num.charAt(i)+"");
    }
    return sum;
  }
  public static int sumOfEven(long c_num) {
    int sum = 0;
    String num = c_num + "";
    for(int i = sizeCheck(c_num)-1; i >= 0; i -= 2) {
      sum += getDigit(Integer.parseInt(num.charAt(i)+""));
    }
    return sum;
  }
  public static int getDigit(int num) {
    if(num<9)
      return num;
    return num/10 + num%10;
  }
  public void test() {
    Scanner sc = new Scanner(System.in);
    long credit_number;
    System.out.print("Enter credit card number: ");
    credit_number = sc.nextLong();
    sc.close();
    System.out.println(credit_number + " is "+ (validation(credit_number) ? "valid":"invalid"));
    if(prefixCheck(credit_number, 4)){
      System.out.print("The given credit card is visa card");
    }else if(prefixCheck(credit_number, 5)){
      System.out.print("The given credit card is master card");
    }else if(prefixCheck(credit_number, 6)){
      System.out.print("The given credit card is discover card");
    }else if(prefixCheck(credit_number, 37)){
      System.out.print("The given credit card is american express card");
    }else {
      System.out.print("The given credit card is does not belong given any given class");
    }
  }
}