package com.company;
//Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4. При подаче массива другого размера необходимо бросить исключение MyArraySizeException.
//Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать. Если в каком-то элементе массива преобразование не удалось
//(например, в ячейке лежит символ или текст вместо числа), должно быть брошено исключение MyArrayDataException с детализацией, в как1ой именно ячейке лежат неверные данные.

public class ArrayUtils {
    public long arraySum(Object[][] array) throws ArrayDataException, ArraySizeException {
        long sum = 0;

        if (array.length != 2) {
            throw new ArraySizeException(array.length);
        } else {
            for (int i = 0; i < array.length; i++) {
                if (array[i].length != 4) {
                    throw new ArraySizeException(array[i].length);
                } else {
                    for (int j = 0; j < array[i].length; j++) {
                        if (!(array[i][j] instanceof Integer)) {
                            throw new ArrayDataException(i, j);
                        } else {
                            sum += ((Number)array[i][j]).longValue();
                        }
                    }
                }

            }
            return sum;
        }
    }
}

