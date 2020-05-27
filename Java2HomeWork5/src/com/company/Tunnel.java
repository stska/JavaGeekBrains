package com.company;

public class Tunnel extends Stage {
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            try {

                Main.semaphore.acquire();
                System.out.println(c.getName() + " готовится к этапу ...........: " + description);
                Main.semaphore.release();

                Main.semaphore.acquire();
                System.out.println(c.getName() + " начал >>>>>>>>>>>>>: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап |: " + description);
                Main.semaphore.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
