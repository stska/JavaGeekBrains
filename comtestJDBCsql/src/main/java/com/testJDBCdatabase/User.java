package com.testJDBCdatabase;

public class User {

    private int id;
    private String name;
    private int age;
    private String email;

    public User(){

    }

    public User(String name, String email){
        this.name = name;
        this.email = email;
    }
    public User(String name, int age, String email){
        this.name = name;
        this.age = age;
        this.email = email;
    }
    public User(int id, String name, int age, String email){
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
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

    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id: " + id + ", name: " + name + ", email:" + email + "}";
    }
}
