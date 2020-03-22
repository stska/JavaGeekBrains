package com.company;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Main {

    public static void main(String[] args) throws Exception {

        WriteFile example = new WriteFile();
        example.writeTwo();//вызов метода зписи текста в файл
        example.writeOne();//вызов метода зписи текста в файл
        String data = "";
        String dataSecondReadFile = "";

         data = example.readFileAsString("D:\\Repositories\\GitHub\\JavaHomeWork\\JavaGeekBrains\\Files\\file_one.txt"); //вызов метода чтения из файла и присвоении текста
         dataSecondReadFile = example.readFileAsString("D:\\Repositories\\GitHub\\JavaHomeWork\\JavaGeekBrains\\Files\\file_one.txt");

         example.merging(data,dataSecondReadFile);               //вызов метода склеивающий файлы


    }
}
