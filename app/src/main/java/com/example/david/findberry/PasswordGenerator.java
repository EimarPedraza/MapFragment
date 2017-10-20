package com.example.david.findberry;

public class PasswordGenerator {

    public static String NUMEROS = "0123456789";

    public static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxy";

    public static String VOCALES = "aeiou";

    public static String CONSONANTESMIN = "bcdfghjklmnpqrstvwxyz";

    public static String CONSONANTESMAY = "BCDFGHJKLMNPQRSTVWXYZ";

    public static String ESPECIALES = "ñÑ";

    //
    public static String getPinNumber() {
        return getPassword(NUMEROS, 4);
    }

    public static String getPassword() {
        return getPassword(8);
    }

    public static String getPassword(int length) {
        return getPassword(NUMEROS + MAYUSCULAS + MINUSCULAS, length);
    }

    public static String getPassword(String key, int length) {
        String pswd = "";

        for (int i = 0; i < length; i++) {
            pswd+=(key.charAt((int)(Math.random() * key.length())));
        }

        return pswd;
    }


    public static String getUsername(String key, String key2, String key3,String key4) {
        String pswd = "";

        pswd+=(key2.charAt((int)(Math.random() * key2.length())));
        pswd+=(key3.charAt((int)(Math.random() * key3.length())));
        pswd+=(key.charAt((int)(Math.random() * key.length())));
        pswd+=(key3.charAt((int)(Math.random() * key3.length())));
        pswd+=(key.charAt((int)(Math.random() * key.length())));
        pswd+=(key3.charAt((int)(Math.random() * key3.length())));
        pswd+=(key4.charAt((int)(Math.random() * key4.length())));
        pswd+=(key4.charAt((int)(Math.random() * key4.length())));

        return pswd;
    }

    public static String getUsername() {
        return getUsername(CONSONANTESMIN,CONSONANTESMAY,VOCALES,NUMEROS);
    }
}
