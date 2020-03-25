package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class WriteFile {
   // String one = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";

   // String two = "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    public static void writeOne() throws IOException {
        String one = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";
       // String nameFileOne;
        OutputStream out;
        out = new FileOutputStream("file_one.txt");

          out.write(one.getBytes());
        out.flush();
        out.close();
    }
    public static void writeTwo(){
        String two = "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

        try {
            PrintStream ps = new PrintStream(new FileOutputStream("file_two.txt"));
            ps.println(two);
            ps.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void merging(String firstFileName, String secondFileName) throws Exception {
        try {
            FileWriter merged = new FileWriter("mergedFile.txt");
            merged.write(firstFileName);
            merged.write(secondFileName);
            merged.close();
            System.out.println("Успешно записали");
        } catch (IOException e) {
            System.out.println("чтто-то пошло не так.");
            e.printStackTrace();
        }

    }
    public static String readFileAsString(String fileName)throws Exception
    {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }
}

