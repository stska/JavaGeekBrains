package com.company;
//Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся). Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
// Посчитать, сколько раз встречается каждое слово.
//Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и телефонных номеров. В этот телефонный справочник с помощью метода add() можно добавлять записи,
// а с помощью метода get() искать номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев), тогда при запросе такой фамилии
// должны выводиться все телефоны. Желательно не добавлять лишний функционал (дополнительные поля (имя, отчество, адрес), взаимодействие с пользователем через консоль и т.д). Консоль использовать
// только для вывода результатов проверки телефонного справочника.


import java.util.*;

public class Main {

    public static void main(String[] args) {

        taskOne(); //первое задание, описание функции в самом низу

        //начало второго задания
        ArrayList<PhoneBook> users = new ArrayList<>();

        PhoneBook phonebookUser1 = new PhoneBook();
        PhoneBook phonebookUser2 = new PhoneBook();
        PhoneBook phonebookUser3 = new PhoneBook();
        PhoneBook  phonebookUser4 = new PhoneBook();


        try{
            phonebookUser1.add("Sasha","89032741154");
            phonebookUser2.add("Mary","79266665542");
            phonebookUser3.add("Mark","79035554126");
            phonebookUser4.add("Putnin","000001");
        }catch (Exception e){
            e.printStackTrace();
        }

        phonebookUser1.add("89032741100");

        phonebookUser1.get("Sasha");
        phonebookUser2.get("Mary");
        phonebookUser1.get("Sashaaa");

        users.add(phonebookUser1);
        users.add(phonebookUser2);
        users.add(phonebookUser3);
        users.add(phonebookUser4);

        //просто чтобы посомтреть на всех юзеров и их номера
        for (PhoneBook user: users){
             System.out.println("User: " + user.getSecondName() + " Phone numbers: " + user.getPhoneNumber());
        }


    }
    private static void taskOne(){
        Map <String,Integer> mapArrayOfFrequency = new HashMap<>();
        ArrayList<String> vocabularyArray = new ArrayList<>();

        vocabularyArray.add("one");
        vocabularyArray.add("two");
        vocabularyArray.add("two");
        vocabularyArray.add("three");
        vocabularyArray.add("three");
        vocabularyArray.add("three");
        vocabularyArray.add("pineapple");


        for (String s : vocabularyArray) {
            if (!mapArrayOfFrequency.containsKey(s)) {
                mapArrayOfFrequency.put(s, 1);
            } else mapArrayOfFrequency.put(s, mapArrayOfFrequency.get(s) + 1);
        }

        for(Map.Entry<String,Integer> entry : mapArrayOfFrequency.entrySet()){
            System.out.println("Слово: " + entry.getKey() + ", повторяется " + entry.getValue() + " раз");
        }
    }
}
