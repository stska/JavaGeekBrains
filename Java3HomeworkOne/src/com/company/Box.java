package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Box <T extends Fruit> {

 private ArrayList<T> edible;

 public Box(T... edible){
     this.edible = new ArrayList<T>(Arrays.asList(edible));
 }

 void addInBox(T smth){
     this.edible.add(smth) ;
 }
 float getWeight(){
     if(edible.isEmpty()){
         return 0.0f;
     } else {
         return edible.size()* edible.get(0).getFruitWeight();
     }
 }
 boolean compareBoxes(Box box){
     return this.getWeight() == box.getWeight();
 }
 private void clearBox(){
     edible.clear();
 }
 void moveToNewBox(Box<? super T> box) {
     box.edible.addAll(this.edible);
     clearBox();
 }

}

/*Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
         Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
         Для хранения фруктов внутри коробки можно использовать ArrayList;
         Сделать метод getWeight(), который высчитывает вес коробки. Задать вес одного фрукта и их количество: вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
         Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той, которую подадут в compare() в качестве параметра. true – если их массы равны, false в противоположном случае.
         Можно сравнивать коробки с яблоками и апельсинами;Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую. Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
         Соответственно, в текущей коробке фруктов не остается,
         а в другую перекидываются объекты, которые были в первой;
         Не забываем про метод добавления фрукта в коробку. */

