package com.company;

//Создать класс "Сотрудник" с полями: Фамилия, зарплата, возраст, должность;
//Конструктор класса должен заполнять эти поля при создании объекта;
//Внутри класса «Сотрудник» написать методы, которые возвращают значение каждого поля;
//Вывести при помощи методов из пункта 3 ФИО и должность.
//Создать массив из 5 сотрудников. С помощью цикла вывести информацию только о сотрудниках старше 40 лет;
//* Создать метод, повышающий зарплату всем сотрудникам старше 45 лет на 5000.
//* Подсчитать средние арифметические зарплаты и возраста сотрудников из п.5
//*** Продумать конструктор таким образом, чтобы при создании каждому сотруднику присваивался личный уникальный идентификационный порядковый номер


public class Employee {
    private static final int ACTUAL_YEAR = 2020;

    //Создать класс "Сотрудник" с полями: Фамилия, зарплата, возраст, должность;
    private String secondName;
    private int salary;
    private final int birth;
    private String post;

    private int id = 1;                     //наш идентификатор
    private static int idCounter = 1;          //вспомогалка для id. Есть ли вариант сделать по-другому?


    //Внутри класса «Сотрудник» написать методы, которые возвращают значение каждого поля;

    public String getSecondName(){
        return secondName;
    }
    public void setSecondName(String secondName){
        this.secondName = secondName;
    }

    public int getSalary(){
        return salary;
    }
    public void setSalary(int salary){
        this.salary = salary;
    }

    public int getAge(){
        return ACTUAL_YEAR - birth;
    }

    public String getPost(){
        return post;
    }
    public void setPost(String post){
        this.post = post;
    }
    public int getId(){
        return id;
    }
    private int setId(int id){
        return id++;
    }



    //Конструктор класса должен заполнять эти поля при создании объекта;
    Employee(String secondName,int salary,int birth,String post) {
        this.secondName = secondName;
        this.salary = salary;
        this.birth = birth;
        this.post = post;
        this.id = idCounter++;               // Есть ли вариант сделать по-другому? на деле вроде как работает, но выглядит уж больно стремно такое решение

    }
}



