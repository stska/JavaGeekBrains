package com.company;
//Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие. В качестве параметра каждому методу передается величина, означающая или длину препятствия (для бега и плавания), или высоту (для прыжков).
//
//У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м., Лошадь 1500 м., Птица 5 м.,; прыжок: кот 2 м., собака 0.5 м., Лошадь 3 м., Птица 0.2 м. ; плавание: кот и птица не умеет плавать, собака 10 м., лошадь 100 м.).
//
//При попытке животного выполнить одно из этих действий, оно должно сообщить результат. (Например, dog1.run(150); -> результат: 'Пёсик пробежал!')
//
//Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может быть 400 м., у другой 600 м.i

public class Main {

    public static void main(String[] args) {
	    Dog tuzik = new Dog(5,"Tuzik","black","husky");
	    System.out.println(tuzik.jumpOver(100));

	    Cat catOne = new Cat(1,"Barsik","ginger","siberian");


	    String catRunResult = catOne.run(61);
	    System.out.println(catOne.getRange());
        System.out.println(catRunResult);

    }
}

//Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может быть 400 м., у другой 600 м.i
// СДЕЛАЛ ТОЛЬКО ДЛЯ КОТА и одного метода. Делал очень поздно, поэтому не было смысла спрашивать у кого-то рахЪяснение задания. Пожалуйста напишите была ли идея такой или другая, если другой, то дайте пожалуйста комментарий.
//Я после прочтения на работе хоть сделаю, как того требовалось. Спасибо!
