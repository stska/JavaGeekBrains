package com.company;
// ограничения на действия (бег: кот 200 м., собака 500 м., Лошадь 1500 м., Птица 5 м.,; прыжок: кот 2 м., собака 0.5 м., Лошадь 3 м., Птица 0.2 м. ; плавание: кот и птица не умеет плавать, собака 10 м., лошадь 100 м.).
public class Bird extends Animal{
    String breed;
    Bird(int age,String name,String colour, String breed){
        super(age,name,colour);
        this.breed = breed;
    }

    @Override
    public String run(int value){
          if(value < 5){
              return "Почта успешно доставлена вашей птицей";
          }else return "Извините, птица не долетела";
    }

    @Override
    public String jumpOver(float value){
         if(value < 0.2){
             return "Прыжок успешно совершен, птица отдыхает на другой ветке";
         }else return "Извинте, но птица не допрыгнула и разбилась((";
    }

    /* @Override
    public String swim(float value){
        return "Вы серьезно!? Хватить мучит бедную птичку. Птицы не плавают!";
    } Раз не могу, то решил убрать миетод. Думаю в задании это и подразумевается) */
}
