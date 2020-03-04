package com.company;
import java.util.Scanner;

public class Main {
    // 1. Создать пустой проект в IntelliJ IDEA и прописать метод main()
    public static void main(String[] args){
        //  2.Создать переменные всех пройденных типов данных и инициализировать их значения.
        short year = 2020;
        int index = 141600;
        long timer = 1000000L;
        float decimal = 36.6f;
        double temperature = 123.245;
        char character = 'c';
        boolean flag = true;

        System.out.println(checkInRange(21.42f, 30.542f, 40.22f, 20.12f)); //вызов метода для вычисления a * (b + (c / d))
        System.out.println(sumInRange(10,5)); //вызов метода проверяющих два числа лежащих в пределах 10-20(включительно)
        checkPositive(-2); //вызов метода проверяющий положительное число или нет
        checkNegative(5); //вызов метода проверяюзего отрицательное число или нет
        speech("Mr.Robot"); //вызов метода подставляющего переданную строку в предложение

        checkLeapYear();
    }

// 3. Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат,
//где a, b, c, d – аргументы этого метода, имеющие тип float.
    public static float checkInRange(float a, float b, float c, float d) {
        return (a*(b+(c/d)));
    }

    //4. Написать метод, принимающий на вход два целых числа и проверяющий,
    // что их сумма лежит в пределах от 10 до 20 (включительно), если да – вернуть true, в противном случае – false.

    public static boolean sumInRange(int a,int b){
        return (a + b) > 10 && (a + b) <= 20;
    }

    //5.Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль,
    // положительное ли число передали или отрицательное. Замечание: ноль считаем положительным числом.
    public static void checkPositive(int a){
        if(a >= 0)  System.out.println("Вы ввели положительное число");
        else System.out.println("Вы ввели отрицательное число");
    }
    //6. Написать метод, которому в качестве параметра передается целое число. Метод должен вернуть true, если число отрицательное.
    public static boolean checkNegative(int number){
        return number < 0;
    }

    //7. Написать метод, которому в качестве параметра передается строка, обозначающая имя.
    // Метод должен вывести в консоль сообщение Привет,указанное_имя!».
    public static void speech(String name){
        System.out.println("Привет! " + name);
    }

    // 8*Написать метод, который определяет, является ли год високосным, и выводит сообщение в консоль. Каждый 4-й год
    // является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.

   public static void checkLeapYear(){
       Scanner myObj = new Scanner(System.in);
       System.out.println("Введите год, для проверски високостный он или нет");
       int year = myObj.nextInt();

      if( year % 4 ==0 && year % 100 != 0 || year % 400 == 0){
          System.out.println("Yes");
       } else System.out.println("No");
         myObj.close();
   }

}
