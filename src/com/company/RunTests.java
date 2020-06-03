package com.company;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

public class RunTests {
    public static Object obj;

    public static void start(Class test) {
        int amountOfTests = 0;      //общее кол-во загруженных тестов
        int testsPassed = 0;       //кол-во успешно-прошедших тестов.


        int aSuite = 0;  //счестчик @AfterSuite, чтобы отслеживать кол-во не превышающее единицу.
        int bSuite = 0;   //счетик @BeforeSuite


        Method before = null;   //сюда запишем метод для аннотации @BeforeSuite
        Method after = null; //  --//-- @AfterSuite

        ArrayList<Method> arayOfMethods = new ArrayList<>();  //сюда записываес метода

        //в этом цикле записываем в arayOfMethods с аннотацией @Test, в before для аннотации @BeforeSuite и в after для @AfterSuite
        for (Method m : test.getDeclaredMethods()) {
            amountOfTests++;

            if ( m.isAnnotationPresent(BeforeSuite.class)) {
                if(bSuite <= 0) {
                    bSuite++;
                    before = m;
                }else throw new RuntimeException("Нельзя передавать два раза анотацию @BeforeSuite");

            }
            if (m.isAnnotationPresent(Test.class)) {
                arayOfMethods.add(m);

            }
            if (m.isAnnotationPresent(AfterSuite.class)) {
                if(aSuite <= 0) {
                    aSuite++;
                    after = m;
                }else throw new RuntimeException("Нельзя передавать два раза анотацию @AfterSuite");

            }
        }
        arayOfMethods.sort(Comparator.comparing(obj -> obj.getAnnotation(Test.class).priority()));  //сортируем по приоритету

        arayOfMethods.add(0, before); //вставляем первым с аннотацией @BeforeSuite
        arayOfMethods.add(after);//вставляем в цонец с аннотацией @AfterSuite
        try {
            obj = test.newInstance();
        }catch (IllegalAccessException | InstantiationException e){
            e.printStackTrace();
        }

        for(Method m : arayOfMethods){
            try {
                if((Boolean)(m.invoke(obj))){
                    testsPassed++;

                }else  System.out.println(m.getName() + " не сработал");

            }catch (IllegalAccessException | InvocationTargetException e){
                e.printStackTrace();
            }
        }
        System.out.printf("\nДля " + test.getSimpleName() + " всего тестов: %d\n",amountOfTests);
        System.out.printf("Для " + test.getSimpleName()+ "Тестов успешно пройденных %d\n",testsPassed);

    }
}
