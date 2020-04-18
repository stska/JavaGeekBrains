package com.company;
//В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException и MyArrayDataException и вывести результат расчета.
public class Main {

    public static void main(String[] args) {
        Object [][] array = {{1,2,3,4},{4,3,2,1}};           //массив подходящий для вычислений
        Object [][] array2 = {{1,2,3},{4,3,2,1}};         //массив неверной размерности
        Object [][] array3 = {{1,2,3,"i"},{4,3,2,1}};            //массив с нечисловым значением по индексу [0][3]
        ArrayUtils testInstance = new ArrayUtils();
        try{
            long sum = testInstance.arraySum(array);
            long sum2 = testInstance.arraySum(array2);
            long sum3 = testInstance.arraySum(array3);

            System.out.println(sum);
            System.out.println(sum2);
            System.out.println(sum3);
        }catch (ArraySizeException e){
            System.out.println(e);
        }catch (ArrayDataException e){
            System.out.println(e);
        }
    }
}
