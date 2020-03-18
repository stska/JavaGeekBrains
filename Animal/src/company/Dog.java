package com.company;
// действия (бег: кот 200 м., собака 500 м., Лошадь 1500 м., Птица 5 м.,; прыжок: кот 2 м., собака 0.5 м., Лошадь 3 м., Птица 0.2 м. ; плавание: кот и птица не умеет плавать, собака 10 м., лошадь 100 м.).
public class Dog extends Animal {
    String breed;
   Dog(int age,String name,String colour, String breed){
        super(age,name,colour);
        this.breed = breed;
    }

    @Override
    public String run(int value){
         if(value < 500){
          return "Ваша собака успешно пробежала";
         }else return "Собака устала раньше времени и не добежала";
    }

    @Override
    public String jumpOver(float value){
        if(value < 0.5){
         return "Успешно преодолено препятствие";
        }else return "Собака даже не стала прыгать!Слишком большое значение";
    }


    public String swim(float value){
      if(value < 10){
       return "Успешно проплыла";
      }else return "Дистанция слишком длинная, собака не доплыла(((";
    }
}
