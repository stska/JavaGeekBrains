package com.company;
/*
-Создайте два класса: беговая дорожка и стена, при прохождении через которые, участники должны выполнять соответствующие действия (бежать или прыгать),
    результат выполнения печатаем в консоль (успешно пробежал, не смог пробежать и т.д.).

 */
public class TreadMill {
    private int distance;

    public TreadMill(){
        distance = (int)(Math.random() * 5000);
    }

    public boolean run(int runAbility, String name){
        if(runAbility <= this.distance){
            System.out.println(name + " успешно пробежал: " + this.distance);
            return true;
        } else {
            System.out.println(name + " не смог пробежать: " + this.distance);
            return false;
        }
    }

}
