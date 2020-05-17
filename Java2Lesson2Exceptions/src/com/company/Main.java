package com.company;

import javax.swing.*;
import java.util.Scanner;

/*
Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4. При подаче массива другого размера необходимо бросить исключение MyArraySizeException.
Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать. Если в каком-то элементе массива преобразование не удалось
(например, в ячейке лежит символ или текст вместо числа), должно быть брошено исключение MyArrayDataException с детализацией, в как1ой именно ячейке лежат неверные данные.
В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException и MyArrayDataException и вывести результат расчета.

 */
public class Main {

    public static void main(String[] args) {
        int [][] array1 = {{1,2,3,4},{4,3,2,1}};
        int [][] array2 = {{1,2,3},{4,3,2,1}};
       // int [][] array3 = {{1,}};
        int summ = sum(array1);
        int summ2 = sum(array2);
        System.out.println(summ);
        System.out.println(summ2);


       // System.out.println("Для проверки зададим двумерный массив 4x4. Пожалуйста введите первый массив:");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите колличество строк массива");
        int m = scanner.nextInt();
        System.out.println("Введите колличество столбцов массива");
        int n = scanner.nextInt();
        String [][] arr = new String [m][n];
            for(int i = 0; i < arr.length; i++){
                for(int j = 0; j < arr[i].length; j++){
                    System.out.println("Ввведите элемент массива индексом ["+ i + "][" + j + "]" );
                    arr[i][j] = scanner.nextLine();
                }
            }

           // int sum3 = sum(arr);
        int[][] a = str2int(new String[] { "1", "23", "456", "7890" });
            System.out.println(a);



    }
    private static int sum(int[][] array){
        int sum = 0;
        for (int[] ints : array) {
            if (array.length != 2) {
               // System.out.println(array.length );

                try {
                    throw new MyArraySizeException("на вход подан не двумерный массив");
                } catch (MyArraySizeException e) {
                    System.out.println(e.getMessage());
                }
                return 0;
            } else {
                for (int j = 0; j < ints.length; j++) {
                   // System.out.println(ints.length);
                   if(ints.length != 4){
                       try {
                           throw new MyArraySizeException("размерность двумерного массива не 4x4 ");
                       } catch (MyArraySizeException e) {
                           System.out.println(e.getMessage());
                       }
                       return 0;
                   }else {
                       sum += ints[j];
                   }
                }
            }
        }
        return sum;
    }
    private static int[][] str2int(String[] str) {
        int[][] a = new int[str.length][];
        for (int i = 0; i < str.length; i++) {
            a[i] = new int[str[i].length()];
            for (int j = 0; j < str[i].length(); j++)
                a[i][j] = (int)str[i].charAt(j) - 48;
        }
        return a;
    }
}
