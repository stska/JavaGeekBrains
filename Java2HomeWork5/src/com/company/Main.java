package com.company;

import java.util.concurrent.*;

public class Main {
     static final int CARS_COUNT = 4;
    static final CyclicBarrier BARRIER = new CyclicBarrier(CARS_COUNT,new StartAnnouncement());            //--------------
    static  CountDownLatch CDLATCH = new CountDownLatch(CARS_COUNT/2);    //---------------------
    static Semaphore semaphore = new Semaphore(CARS_COUNT/2);
    static final CountDownLatch CDLATCHSTART = new CountDownLatch(CARS_COUNT/2);
    static boolean winerFlag = false;
    private static boolean isFinished = true;

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));

        }

        ExecutorService executorService = Executors.newFixedThreadPool( cars.length);
        for (int i = 0; i < cars.length; i++) {
            executorService.execute(cars[i]);
        }
        executorService.shutdown();
        while (isFinished){
            /////////Мне это так не нравится, но что-то получилось пока только так, подскажите нормальный способ
            if(executorService.isTerminated()){
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ »> Гонка закончилась!!!");
                isFinished = false;
            }
        }
    }


    public static class StartAnnouncement implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(500);
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            } catch (InterruptedException e) {
            }
        }
    }


}



