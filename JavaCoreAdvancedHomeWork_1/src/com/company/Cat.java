package com.company;

public class Cat implements Action{
    private String name;
    private int age;
    private int runAbility;
    private int jumpAbility;

    private static final int MAXRUN = 2000;
    private static final int MAXJUMP = 20;
    private static final int MINRUN = 10;
    private static final int MINJUMP = 1;


    public Cat(String name,int age){
        this.name = name;
        this.age = age;
        runAbility = (int)(MINRUN + Math.random() * MAXRUN);
        jumpAbility = (int)(MINJUMP + Math.random() * MAXJUMP);
    }

    public void setName(String name){
           this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setAge(int age){
        this.age = age;
    }
    public int getAge(){
        return age;
    }

    public int getRunAbility(){
        return runAbility;
    }
    public int getJumpAbility(){
        return jumpAbility;
    }

    @Override
    public void run() {
        System.out.println("Котик по имени " + this.name + "умеет бегать");

    }

    @Override
    public void jump() {
        System.out.println("Котик по имени " + this.name + "умеет прыгать");

    }
}
