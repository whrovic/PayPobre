package util;

public class Macros {
    public static boolean ComparePass(String dbPass, String insertPass){
        if(dbPass == null || insertPass == null){
            System.out.println("Parameters are null");
            return false;
        }
        System.out.println("db Pass length: " + dbPass.length() + " Inserted pass length " + insertPass.length());
        int length = Math.min(dbPass.length(), insertPass.length());

        int dif = 0;
        char[] array1 = dbPass.toCharArray();
        char[] array2 = insertPass.toCharArray();

        for(int i = 0; i < length; i++){
            System.out.println("x: " + array1[i] + " == y: " + array2[i]);
            if(array1[i] != array2[i]) dif++;
        }

        System.out.println("dif = " + dif);
        return dif == 0;
    }
}
