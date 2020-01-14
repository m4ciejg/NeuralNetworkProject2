package com.maciejg;

public interface Conversion {

     default double[] boolArrayToDouble(boolean[] source) {
        double[] result = new double[source.length];
        for (int i = 0; i < source.length; i++) {
            result[i] = source[i] ? 1 : 0;
        }
        return result;
    }

    default  boolean[] convert(Object[] array){
        boolean[] result = new boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = (boolean) array[i];
        }
        return result;
    }
}
