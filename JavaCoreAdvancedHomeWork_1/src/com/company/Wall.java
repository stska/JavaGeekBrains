package com.company;
/*
-Создайте два класса: беговая дорожка и стена, при прохождении через которые, участники должны выполнять соответствующие действия (бежать или прыгать),
        результат выполнения печатаем в консоль (успешно пробежал, не смог пробежать и т.д.).
  */
public class Wall {
    private int height;

    public Wall(){
        height = (int)( Math.random() * 30);
    }

    public boolean jump(int jumpAbility,String name){
        if(jumpAbility <= this.height){
            System.out.println(name + " успешно перепрыгнул:" + this.height);
            return true;
        } else {
            System.out.println(name + " не смог перепрыгнуть: " + this.height);
            return false;
        }
    }

}
