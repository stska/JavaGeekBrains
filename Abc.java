package com.company;
//1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС). Используйте wait/notify/notifyAll.
public class Abc {

    private final Object mutex = new Object();
    private volatile char currentLetter = 'A';
    public static void main(String[] args) {
        Abc m = new Abc();
        Thread t1 = new Thread(() -> {
            m.printA();
        });
        Thread t2 = new Thread(() -> {
            m.printB();
        });
        Thread t3 = new Thread(() -> {
            m.printC();
        });
        t1.start();
        t2.start();
        t3.start();
    }

    private void printA() {
        synchronized ( mutex) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'A') {
                        mutex.wait();
                    }
                    System.out.print("A");
                    currentLetter = 'B';
                    mutex.notifyAll();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printB() {
        synchronized ( mutex) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'B') {
                        mutex.wait();
                    }
                    System.out.print("B");
                    currentLetter = 'C';
                    mutex.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void printC(){
        synchronized ( mutex){
            try{
                for(int i = 0; i < 5; i ++){
                    while (currentLetter != 'C'){
                        mutex.wait();
                    }
                  System.out.print("C");
                 currentLetter = 'A';
                    mutex.notifyAll();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
