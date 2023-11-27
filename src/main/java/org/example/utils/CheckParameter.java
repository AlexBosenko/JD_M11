package org.example.utils;

public class CheckParameter {
    public static boolean of(Object object, String className) {
        if (object == null) {
            System.out.println("Parameter of type " + className + " cannot be null");
            return false;
        }
        return true;
    }
}
