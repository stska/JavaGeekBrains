package com.company;

public class ArraySizeException extends RuntimeException{
    private String message = "на вход подан не верного размера: ";
    private long wrongDimensionalSize;
    public ArraySizeException(long wrongDimensionalSize){

        this.wrongDimensionalSize = wrongDimensionalSize;
    }

    @Override
    public String toString() {
        return message + wrongDimensionalSize;
    }
}
