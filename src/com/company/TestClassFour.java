package com.company;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestClassFour {
    @BeforeSuite
    public static boolean greeting(){
        System.out.printf("---------------------\n @BeforeSuite Поехали!!! \n");
        return true;
    }

    @Test(priority = -1)
    public static boolean testTwo(){
        System.out.println("ToDo Smth with -1 priority ");
        return false;
    };
    @Test(priority = 0)
    public static boolean testZero(){
        System.out.println("ToDo Smth with default priority");
        return false;
    }
    @Test
    public static boolean testNew(){
        System.out.println("ToDoSmth again with default priority");
        return false;
    }
    @AfterSuite
    public static boolean farewell(){
        System.out.printf("@AfterSuite Прощай!!! \n-------------------------");
        return false;
    }
    @AfterSuite
    public static boolean shouldBringRuntimeException(){
        System.out.printf(" @AfterSuite Прощай!!! \n-------------------------");
        return false;
    }
}
