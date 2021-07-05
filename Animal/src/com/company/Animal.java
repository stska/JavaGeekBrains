package com.company;

//Создать классы Собака, Лошадь, Птица и Кот с наследованием от класса Животное.
//
//Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие. В качестве параметра каждому методу передается величина, означающая или длину препятствия (для бега и плавания), или высоту (для прыжков).
//
//У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м., Лошадь 1500 м., Птица 5 м.,; прыжок: кот 2 м., собака 0.5 м., Лошадь 3 м., Птица 0.2 м. ; плавание: кот и птица не умеет плавать, собака 10 м., лошадь 100 м.).
//
//При попытке животного выполнить одно из этих действий, оно должно сообщить результат. (Например, dog1.run(150); -> результат: 'Пёсик пробежал!')
//
//Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может быть 400 м., у другой 600 м.i

public abstract class Animal {
   private int age;
   private String name;
   private String colour;


   public Animal(int age,String name,String colour){
       this.age = age;
       this.name = name;
       this.colour = colour;
   }

    public int getAge(){
        return  this.age;
    }
    public void setAge(int age){
        this.age = age;
    }

    public  String getName(){
       return this.name;
    }
    public void setName(String name){
       this.name = name;
    }

    public String getColour(){
       return this.colour;
    }
    private void setColour(){
       this.colour = colour;
    }



    protected abstract String run(int value);
    protected abstract String jumpOver(float value);
    //protected abstract String swim(float value);


}
