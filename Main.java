package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 1) Создают одномерный длинный массив, например:
 * static final int size = 10000000;
 * static final int h = size / 2;
 * float[] arr = new float[size];
 * 2) Заполняют этот массив единицами;
 * 3) Засекают время выполнения: long a = System.currentTimeMillis();
 * 4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
 * arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
 * 5) Проверяется время окончания метода System.currentTimeMillis();
 * 6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);
 * Отличие первого метода от второго:
 * Первый просто бежит по массиву и вычисляет значения.
 * Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.
 * <p>
 * Пример деления одного массива на два:
 * System.arraycopy(arr, 0, a1, 0, h);
 * System.arraycopy(arr, h, a2, 0, h);
 * <p>
 * Пример обратной склейки:
 * System.arraycopy(a1, 0, arr, 0, h);
 * System.arraycopy(a2, 0, arr, h, h);
 * <p>
 * Примечание:
 * System.arraycopy() копирует данные из одного массива в другой:
 * System.arraycopy(массив-источник, откуда начинаем брать данные из массива-источника, массив-назначение, откуда начинаем записывать данные в массив-назначение, сколько ячеек копируем)
 * По замерам времени:
 * Для первого метода надо считать время только на цикл расчета:
 * for (int i = 0; i < size; i++) {
 * arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
 * }
 */
public class Main {
    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) throws InterruptedException {
        float[] arr = new float[size];

        fillArray(arr); //заполняеи массив arr единицами

        testOne(arr);     //первый метод с простым обходом по циклу и пересчет. время замера только для цикла for, где идёт пересчет

        testTwo(arr);
        testThree();

        long a = System.currentTimeMillis();
        testFour(arr);
        System.out.println("------Время четвертой реализации 2ого метода " + (System.currentTimeMillis()- a));
    }

    private static void testOne(float[] arr) {
         fillArray(arr); //снова заполняем единицами
        long b = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("------Время 1ого метода " + (System.currentTimeMillis() - b));
    }

    private static void testTwo(float[] arr) throws InterruptedException {

        float[] a1 = new float[h/2]; //Отсюда у меня вопрос. зачем нам так делать, если далее мы копируем сюда колл-во чисел равное size/2?
        float[] a2 = new float[h/2];
        System.arraycopy(arr, 0, a1, 0, h);      //скопировали числа из массива arr с элемента под индексом [0] до  в массив a 1 от нуля и колличеством size/2.
        System.arraycopy(arr, h, a2, 0, h);

        long startTime = System.currentTimeMillis(); //начальное время замера


        Thread firstThread = new Thread(new Test(a1));      //создали поток и передали ему обЪект типа Runnable
        Thread secondThread = new Thread(new Test(a2));

        firstThread.start();             //запустили поток
        secondThread.start();

        firstThread.join(10);      //метод приостановит выполнение текущего потока до тех пор, пока другой поток не закончит свое выполнение
        secondThread.join(10);

        //копируем обратно пересчитанные значения в массив arr
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        System.out.println("----------Время второго метода: " + (System.currentTimeMillis() - startTime));


    }

    private static void mathCalculation(float[] arr) {
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

        }

    }
    private static void fillArray(float[] arr){
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
    }

    private static void testThree() throws InterruptedException {
        float[] arr = new float[size];
        Arrays.fill(arr, 1);
        float[] a1 = new float[size];
        float[] a2 = new float[size];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        long start = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(2);


        Thread firstThread = new Thread(new Test(a1));
        Thread secondThread = new Thread(new Test(a2));


        executorService.submit(firstThread);   //ставит задачу в очередь на выполнение
        executorService.submit(secondThread);   //ставит задачу в очередь на выполнение

        executorService.shutdown(); //Упорядоченное завершение работы, при котором ранее отправленные задачи выполняются, а новые задачи не принимаются
        executorService.awaitTermination(1, TimeUnit.MILLISECONDS);     //Блокировка до тех пор, пока все задачи не завершат выполнение после запроса на завершение работы

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        System.out.println("Время третьего: " + (System.currentTimeMillis() - start));
        System.out.println(arr[2]);                //просто проверка значений, что действительно был пересчет
        System.out.println(arr[1000]);             //просто проверка значений, что действительно был пересчет


    }
    private static void testFour(float[] arr){

        float[] a1 = new float[size]; //Отсюда у меня вопрос. зачем нам так делать, если далее мы копируем сюда колл-во чисел равное size/2?
        float[] a2 = new float[size];
        System.arraycopy(arr, 0, a1, 0, h);      //скопировали числа из массива arr с элемента под индексом [0] до  в массив a 1 от нуля и колличеством size/2.
        System.arraycopy(arr, h, a2, 0, h);

        new Thread(() -> {
            System.out.println("Начало первого потока");

            mathCalculation(a1);
            System.out.println("начало склейки 1ого");
            System.arraycopy(a1, 0, arr, 0, h);
            System.out.println("склеили 1ый");
           try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
               System.out.println("Прервали первый поток");
            }

            System.out.println("Конец первого потока");
        }).start();
        new Thread(() -> {

            System.out.println("Начало второго потока");
            mathCalculation(a2);
            System.out.println("начало склейки 2ого");
            System.arraycopy(a2, 0, arr, 0, h);
            System.out.println("склеили 2ой");

           try {
               Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Прервали второй поток");
            }

           System.out.println("Конец 2-ого потока");
        }).start();
    }

}
