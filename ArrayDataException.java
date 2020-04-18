package com.company;
//должно быть брошено исключение MyArrayDataException с детализацией, в как1ой именно ячейке лежат неверные данные.
public class ArrayDataException extends Exception{
    private int indexI;
    private int indexJ;
    String errorMessage = "Элемент массива не является числом. Индексы этого элемента:";

    public ArrayDataException ( int indexI,int indexJ) {
        this.indexI = indexI;
        this.indexJ = indexJ;
           }

    @Override
    public String toString() {
        return errorMessage + "[" + indexI + "]"+ "[" + indexJ + "]";
    }

}
