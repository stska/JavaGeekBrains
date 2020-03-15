package com.company;

public class Main {
    private static final int salaryRiseRate = 5000;       //доп. коэф. на который увеличиваем зарплату

    private static void checkEmployees(Employee[] array){
        //Employee [] employeeTicked = new Employee[];

        int counter = 1;          //для подсчета среднего в дальнейшем
        int averageSum = 0;      //для подсчета среднего в дальнейшем
        int averageAge = 0;     //для подсчета среднего в дальнейшем


        for(int i = 0; i < array.length; i++ ){


            if( array[i].getAge() > 40){              //вызвваем метод для отображения всех сотрудников старше 40
                 showEmployees(array[i]);         //вызвваем метод для отображения инф. о сотруднике
                 counter++;
                averageSum += array[i].getSalary();      //получаем текущую зарплату сотрудника и прибавляем ее к переменной, храняшей сумму зарплат
                averageAge += array[i].getAge();         //получаем текущую возраст сотрудника и прибавляем ее к переменной, храняшей сумму возрастов

            }
            if(array[i].getAge() > 45){
                riseSalary(array[i]);          //метод для повышения зарплаты
            }

        }
        average(averageSum,averageAge,counter);       //вызываем метод для рассчета средней зарплаты и возраста и передаем так же кол-во сотрудников из пула

    }
    private static void showEmployees(Employee employee){
        System.out.println(" Фамилия: " + employee.getSecondName() + " Зарплата: " + employee.getSalary() + " Возраст: " +employee.getAge() + " Должность: " + employee.getAge() + "/ id:" + employee.getId());
    }
    private static void riseSalary(Employee employee){
        employee.setSalary(employee.getSalary() + salaryRiseRate);
        System.out.println("Сотрутрик получивший повышение з.п: " + employee.getSecondName());
    }
    private static void average(int averageSum,int averageAge,int counter){
        System.out.println("--------------------------");
        System.out.println("Средний возраст" + averageAge/counter);
        System.out.println("Средняя зарпоаиа " + averageSum/counter);
        System.out.println("--------------------------");
    }


    public static void main(String[] args) {

        Employee employeeA = new Employee("Putin",12000,1955,"president");

        //Вывести при помощи методов из пункта 3 ФИО и должность.
        System.out.println("Фамилия: " + employeeA.getSecondName() + "/ Должность: " + employeeA.getPost() + "/ id:" + employeeA.getId());

        Employee employeeB = new Employee("Medvedev",10000, 1962,"unemployed");
        Employee employeeC = new Employee("Trump",30000,1952,"president");
        Employee employeeD = new Employee("Winnie-the-Pooh",20000,1992,"confectioner");
        Employee employeeE = new Employee("Wick",100000,1972,"killer");

        Employee[]  arrayOfEmploees = new Employee[]{employeeA, employeeB, employeeC, employeeD,  employeeE};

        checkEmployees(arrayOfEmploees);

    }



}

// System.out.println(" Фамилия: " + array[i].getSecondName() + " Зарплата: " + array[i].getSalary() + " Возраст: " + array[i].getAge() + " Должность: " +array[i].getAge());

// array[i].setSalary(array[i].getSalary() + salaryRiseRate);
// System.out.println("Сотрутрик получивший повышение з.п: " + array[i].getSecondName());