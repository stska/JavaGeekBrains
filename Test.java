package com.company;

public class Test implements Runnable {
    private float [] arr;
    private static final int size = 10000000;

    public Test(float[] arr){
        this.arr = arr;
    }
    @Override
    public void run() {
               for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}
