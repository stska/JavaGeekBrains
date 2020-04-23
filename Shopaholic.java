package com.company;

public class Shopaholic implements Comparable<Shopaholic>{
    private String name;
    private String secondName;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setSecondName(String secondName){
        this.secondName = secondName;
    }
    public String getSecondName(){
        return secondName;
    }

    @Override
    public int compareTo(Shopaholic shopaholic) {
        return name.compareTo(shopaholic.name);
    }

  //  @Override
   // public String toString() {
  //      return this.name + this.secondName;
  //  }
}

