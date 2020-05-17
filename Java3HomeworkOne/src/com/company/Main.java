package com.company;

/*
1)Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
2)Написать метод, который преобразует массив в ArrayList;
3)Задача:
Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
Для хранения фруктов внутри коробки можно использовать ArrayList;
Сделать метод getWeight(), который высчитывает вес коробки. Задать вес одного фрукта и их количество: вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той, которую подадут в compare() в качестве параметра. true – если их массы равны, false в противоположном случае.
Можно сравнивать коробки с яблоками и апельсинами;Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую. Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;
Не забываем про метод добавления фрукта в коробку.

 */

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        //1)Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] arrayString = {"a", "b", "c", "d", "e", "f"};

        System.out.println("Массив Integer до замены: " + Arrays.toString(array));
        replacement(array, 3, 5);
        System.out.println("Массив Integer после замены  элемента  c индексом 3 на элемент с индексом 5 : " + Arrays.toString(array));

        System.out.println("Массив String до замены: " + Arrays.toString(arrayString));
        replacement(arrayString, 2, 0);
        System.out.println("Массив Integer после замены  элемента  c индексом 2 на элемент с индексом 0 : " + Arrays.toString(arrayString));
        //---------------------------------------------------------------------------------------------------------------
        //2)Написать метод, который преобразует массив в ArrayList;
        ArrayList<Integer> listInteget = fromArrayToArrayList(array);
        ArrayList<String> listString = fromArrayToArrayList(arrayString);
        //-------------------------------------------------------------------------------------------------------------
         /*Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
         Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
         Для хранения фруктов внутри коробки можно использовать ArrayList;
         Сделать метод getWeight(), который высчитывает вес коробки. Задать вес одного фрукта и их количество: вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
         Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той, которую подадут в compare() в качестве параметра. true – если их массы равны, false в противоположном случае.
         Можно сравнивать коробки с яблоками и апельсинами;Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую. Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
         Соответственно, в текущей коробке фруктов не остается,
         а в другую перекидываются объекты, которые были в первой;
         Не забываем про метод добавления фрукта в коробку. */
        Apple golden = new Apple();
        Apple gala = new Apple();
        Apple randomeApple = new Apple();


        Orange marokanOrange = new Orange();
        Orange southOrange = new Orange();
        Orange randomOrange = new Orange();

        Box<Apple> firstBox = new Box<>(golden, gala);
        Box<Orange> secondBox = new Box<>(marokanOrange, southOrange);

        //добавление
        firstBox.addInBox(randomeApple);
        secondBox.addInBox(randomOrange);
        //вес
        System.out.println("Коробка с яблоками весит: " + firstBox.getWeight());
        System.out.println("Коробка с апельсинами весит: " + secondBox.getWeight());
        //сравнение 2х коробок
        System.out.println(firstBox.compareBoxes(secondBox));

        //из одной коробки в другую
        Box<Apple> boxForTransferring = new Box<>();
        firstBox.moveToNewBox(boxForTransferring);
        System.out.println("Новая коробка с яблоками весит: " + boxForTransferring.getWeight());
        System.out.println("Старая коробка с яблоками весит: " + firstBox.getWeight());

    }

    // метод из первого задания
    private static <T> void replacement(T[] array, int takeFrom, int putInto) {
        T tmp = array[takeFrom];
        array[takeFrom] = array[putInto];
        array[putInto] = tmp;
    }

    //метод из второго задания
   // private static <E> ArrayList<E> fromArrayToArrayList(E[] array) {
    private static <E> ArrayList<E> fromArrayToArrayList(E[] array) {
        return new ArrayList<E>(Arrays.asList(array));
    }
}
