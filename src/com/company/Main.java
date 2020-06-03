package com.company;


public class Main {



    public static void main(String[] args) {
        Class<?> testOne = TestClassOne.class;
        Class<?> testTwo = TestClassSecond.class;
        Class<?> testThree = TestThree.class;
        Class<?> testFour = TestClassFour.class;
        //логика обработки тестов в классе RunTests.class, из него просто вызываем статический метод start
        RunTests.start(testOne);
        RunTests.start(testTwo);
        RunTests.start(testThree);
        RunTests.start(testFour); //будет выдать RuntimeException так как второй раз будем пытаться вызвать метод с аннотацией @AfterSuite

    }
    //сделана просто для примера, чтобы показать не тест пустышку, а что-то делаюший. Для теста из класса TestThree.class
    private static boolean sumForTest(int a, int b,int result){
        return (a + b) == result;
    }


}
