package com.company;

public class TestClassSecond {
    @BeforeSuite
    public static boolean greeting(){
        System.out.printf("---------------------\n @BeforeSuite Поехали!!! \n");
        return true;
    }
    @Test
    public static boolean testOne(){
        int a = 2;
        int b = 3;
        int result = 6;
        return result == (a * b);
    }
    @Test(priority = 5)
    public static boolean testTwo(){
        System.out.println("Im a test with the 5th priority ");
        return true;
    };
    @Test(priority = 0)
    public static boolean testZero(){
        System.out.println("I'm a test with default priority");
        return true;
    }
    @Test
    public static boolean testNew(){
        System.out.println("I'm also a test with default priority");
        return true;
    }
    @AfterSuite
    public static boolean farewell(){
        System.out.printf(" @AfterSuite Прощай!!! \n-------------------------");
        return true;
    }
}
