package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    //1.Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
// С помощью цикла и условия заменить 0 на 1, 1 на 0;
//2.Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
//3.Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
//4.Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью цикла(-ов) заполнить его диагональные элементы единицами;

    public static void main(String[] args) {
        int [] array = new int [] {1,1,0,0,1,0,1,1,0,0}; //Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]
        int [] arr = new int[8];  //Задать пустой целочисленный массив размером 8
        int [] arrayB = new int[] { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 }; //Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ]
        int [][] squareArray = new int [][]{{0,0},{0,0}}; //Создать квадратный двумерный целочисленный массив

        int [] minMax  = {4,2,5,1,0,9,5};

        replaceNumbers(array);
        fillInArray(arr);
        multiplicationNumberslessThanSix(arrayB);
        System.out.println("Square matrix");
        makingMainDiagonal(squareArray);

        maxAndMin(minMax);

        int[] arrayC = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        int [] shifterArray = shiftOfNumbers(arrayC, 3);

        int [] arrayForCheckingSum = {1,1,1,2,1};
        System.out.println(checkBalance(arrayForCheckingSum));

    }
    //С помощью цикла и условия заменить 0 на 1, 1 на 0
    public static void replaceNumbers(int [] array){
        for(int i=0; i< array.length; i++){
           /* if(array[i] == 0){
                array[i] = 1;
            }else array[i] = 0; */
           array[i] = array[i] == 0 ? 1 : 0;
           System.out.println(array[i]);
        }
    }
    // С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21
    public static int[] fillInArray(int[] arr){
        int [] fillingArray = new int[] {0,3,6,9,12,15,18,21};
        for(int i = 0; i< arr.length; i++){
            arr[i]=fillingArray[i];
            System.out.println(arr[i]);
        }
        return arr;
    }
    //пройти по массиву циклом, и числа меньшие 6 умножить на 2;
    public static int[] multiplicationNumberslessThanSix (int[] arrayB){
        for(int i = 0; i < arrayB.length; i++){
            arrayB[i] = arrayB[i] < 6 ? arrayB[i]*2 : arrayB[i];
            System.out.println(arrayB[i]);
        }
        return arrayB;
    }
    public static void makingMainDiagonal(int arr[][]){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                if(i == j){
                    arr[i][j]= 1;
                }
                System.out.println(arr[i][j]+ "\t");
            }
            System.out.println();
        }
    }
    //-** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);

    public static void maxAndMin(int[] arr){
        int max = 0;
        int min = 0;

        for(int iteration: arr){
            max = iteration > max ? iteration : max;
            min = iteration < min ? iteration : min;
        }
        System.out.println("Max = " + max);
        System.out.println("Min = " + min);
    }


    //-**** Написать метод, которому на вход подается одномерный массив и число n (может быть положительным, или отрицательным),
    // при этом метод должен сместить все элементы массива на n позиций. Для усложнения задачи нельзя пользоваться вспомогательными массивами.
    private static int[] shiftOfNumbers(int[] array, int n) {
        System.out.println("Array before shifting: ");
        System.out.println(Arrays.toString(array));

         //сдвиг вправо
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                int lastElement = array[array.length - 1];
                if (array.length - 1 >= 0) System.arraycopy(array, 0, array, 1, array.length - 1);
                array[0] = lastElement;
            }
        }
        //Сдвиг влево
        if (n < 0) {
            for (int i = 0; i > n; i--) {
                int lastElement = array[0];
                System.arraycopy(array, 1, array, 0, array.length - 1);
                array[array.length - 1] = lastElement;
                //смотрим матрицу после каждого сдвига
                for (int j : array) System.out.print(j + " ");
                System.out.print("\r\n");
            }
        }
        System.out.println("Array after shifting: ");
        System.out.println(Arrays.toString(array));
        return array;
    }
//-** Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true, если
    // в массиве есть место, в котором сумма левой и правой части массива равны. Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true,
    // checkBalance([1, 1, 1, || 2, 1]) → true, граница показана символами ||, эти символы в массив не входят.
private static boolean checkBalance(int[] array) {
    int leftSum, rightSum;

    for (int i = 0; i < array.length + 1; i++) {
        leftSum = 0;
        rightSum = 0;

        //сумма для левой части
        for (int j = 0; j < i; j++) {
            leftSum += array[j];
        }
        //сумма для правой части
        for (int j = i; j < array.length; j++) {
            rightSum+= array[j];
        }

        if (leftSum == rightSum) return true;
    }
    return false;
}
}
