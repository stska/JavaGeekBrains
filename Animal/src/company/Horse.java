package com.company;
//ограничения на действия (бег: кот 200 м., собака 500 м., Лошадь 1500 м., Птица 5 м.,; прыжок: кот 2 м., собака 0.5 м., Лошадь 3 м., Птица 0.2 м. ; плавание: кот и птица не умеет плавать, собака 10 м., лошадь 100 м.).
public class Horse extends  Animal{
    String breed;
    Horse(int age,String name,String colour, String breed){
        super(age,name,colour);
        this.breed = breed;
    }

    @Override
    public String run(int value){
        if (value < 1500){
            return "Дистанция успешно преодолена!Мои поздравления";
        }else return "Вы совсем загнали лошадь и она умерла";
    }

    @Override
    public String jumpOver(float value){
          if(value < 3){
              return "Успешно преодолено препятствие";
          }else return  "Слишком много!Лошади не летают!";
    }


    public String swim(float value){
            if(value < 100){
                return "Успешно переплыли!";
            }else return "Лошадь утонула";
    }
}

