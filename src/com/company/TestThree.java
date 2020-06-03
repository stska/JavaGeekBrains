package com.company;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestThree {
    @BeforeSuite
    public static boolean greeting(){
        System.out.printf("---------------------\n @BeforeSuite Поехали!!! \n");
        return true;
    }
    @Test
    public static boolean testOne(){
        int a = 1;
        int b = 2;
       int c = 3;


        boolean flagResult = false;
        try {
            Method method = Main.class.getDeclaredMethod("sumForTest",int.class,int.class,int.class);
            method.setAccessible(true);
            Object object = new Object();
            if((Boolean) (method.invoke(object,a,b,c))){
                flagResult = true;
                System.out.printf("%d + %d = %d \n",a,b,c);
            }
        }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
        }
        return flagResult;
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

}
