package com.company;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

public class TestClassOne {
    @BeforeSuite
    public static boolean greeting(){
        System.out.printf("---------------------\n @BeforeSuite Поехали!!! ");
        return true;
    }
    @Test
    public static boolean testOne(){
        int a = 1;
        int b = 2;
        int result = 3;
        //просто для проверки такой тест)
        return result == (a + b);

    }
    @Test(priority = 2)
    public static boolean testTwo(){
        System.out.println("Здравствуйте, у меня приоритет 2");
        return true;
    }
    @Test(priority = 0)
    public static boolean testZero(){
        System.out.println("Просто тест с приоритетом 0");
        return true;
    }
    @Test
    public static boolean testNew(){
        System.out.println("Просто тест cо стандартный приоритетом 1");
        return false;
    }
    @AfterSuite
    public static boolean farewell(){
        System.out.printf(" @AfterSuite Прощай!!! \n-------------------------");
        return true;
    }
}
