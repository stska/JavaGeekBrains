package com.company;
//Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и телефонных номеров. В этот телефонный справочник с помощью метода add() можно добавлять записи,
// а с помощью метода get() искать номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев), тогда при запросе такой фамилии
// должны выводиться все телефоны. Желательно не добавлять лишний функционал (дополнительные поля (имя, отчество, адрес), взаимодействие с пользователем через консоль и т.д). Консоль использовать
// только для вывода результатов проверки телефонного справочника.

import java.util.ArrayList;

public class PhoneBook {

    private String secondName;
    private ArrayList<String> phoneNumber;

    public PhoneBook(){
        phoneNumber = new ArrayList<>();
    }
    public PhoneBook(String secondNamename){
        this.secondName = secondNamename;
        phoneNumber = new ArrayList<>();
    }

    public void add(String secondName, String number){
        this.secondName = secondName;
        this.phoneNumber.add(number);
    }
    public void add(String number){
        this.phoneNumber.add(number);
    }
    public void get(String secondName){
        if(secondName.equals(this.secondName) ){
            System.out.println("Телефон:"  + phoneNumber);
        } else System.out.println("Не найден");
    }
    public String getSecondName(){
        return secondName;
    }
    public ArrayList<String> getPhoneNumber(){
        return phoneNumber;
    }
}
