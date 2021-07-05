package ru.geekbrains.java_one.lesson_c.online;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '_';

    private static int fieldSizeX;
    private static int fieldSizeY;
    private static char[][] field;

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();

    private static void initField() {
        fieldSizeY = 3;
        fieldSizeX = 3;
        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = DOT_EMPTY;
            }
        }
    }

    private static void printField() {
        System.out.println("------");
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                System.out.print(field[y][x] + "|");
            }
            System.out.println();
        }
    }

    private static boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >=0 && y < fieldSizeY;
    }

    private static boolean isEmptyCell(int x, int y) {
        return field[y][x] == DOT_EMPTY;
    }

    private static void humanTurn() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты хода X и Y (от 1 до 3) через пробел >>> ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isValidCell(x, y) || !isEmptyCell(x, y));
        field[y][x] = DOT_HUMAN;
    }

    private static void aiTurn() {
        int x;
        int y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(x, y));
        field[y][x] = DOT_AI;

    }

    private static boolean isFieldFull() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == DOT_EMPTY) return false;
            }
        }
        return true;
    }
//-------------------------------------------------------------------------------------------------------------
    private static boolean checkWin() {
      /*  if (field[0][0] == c && field[0][1] == c && field[0][2] == c) return true;
        if (field[1][0] == c && field[1][1] == c && field[1][2] == c) return true;
        if (field[2][0] == c && field[2][1] == c && field[2][2] == c) return true;

        if (field[0][0] == c && field[1][0] == c && field[2][0] == c) return true;
        if (field[0][1] == c && field[1][1] == c && field[2][1] == c) return true;
        if (field[0][2] == c && field[1][2] == c && field[2][2] == c) return true;

        if (field[0][0] == c && field[1][1] == c && field[2][2] == c) return true;
        if (field[0][2] == c && field[1][1] == c && field[2][0] == c) return true;
        return false; */
      return (check_row (field,fieldSizeX, fieldSizeY) || check_col (field,fieldSizeX, fieldSizeY) || diagonals());
    }
        public static boolean check_row (char[][] field,int fieldSizeX,int fieldSizeY){
            boolean flag = false;
            for(int i = 0; i < fieldSizeY; i++){
                int flagCounter = 0;
                for(int j = 1 ;j < fieldSizeX;j++){
                    if(field[i][j] == field[i][j-1]){
                        flag = true;
                        flagCounter++;
                        if(flagCounter == (fieldSizeY-1)){
                            System.out.println("Всё работает, проверка по строкам: " + flag);
                        }
                    } else flag = false;
                }
            }
            return flag;
        }
        public static boolean check_col(char[][] field,int fieldSizeX,int fieldSizeY){
           boolean flag = false;
            for (int i = 1; i < fieldSizeY; i++){
                int flagCounter = 0;
                for (int j = 0; j < fieldSizeX; j++){
                    if(field[i][j] == field[i-1][j]){
                        flag = true;
                        flagCounter++;
                        if(flagCounter == (fieldSizeX-1)){
                            System.out.println("Все работает, проверка по столбцам: " + flag);
                        }
                    } else flag = false;
                }
            }
            return flag;

        }

        public static boolean diagonals() {
        return checkMainDiagonal(field, fieldSizeX, fieldSizeY) ||  checkReverseMainDiagonal(field, fieldSizeX, fieldSizeY);
        }

    public static boolean checkMainDiagonal(char[][] field, int fieldSizeX, int fieldSizeY){
        boolean flag = false;
       // checkRowCol(board[0][0],board[1][1],board[2][2]) || checkRowCol(board[2][0],board[1][1],board[0][2]);
        for( int i = 1; i < fieldSizeY; i++){
            for(int j = 1; j < fieldSizeX; j++){
                if(i == j){
                    flag = (field[i][j] == field[i-1][j-1]) ? true : false;
                }
            }
        }
        return flag;
    }
    public static boolean checkReverseMainDiagonal(char[][] field, int fieldSizeX, int fieldSizeY){
        boolean flag = false;
        char additinalFlag = field[0][fieldSizeY];
        for(int i = 0; i < fieldSizeX; i++){
            for( int j = fieldSizeY-1; j < fieldSizeY; j--){
                if((i+j) == fieldSizeY){
                    //flag = (field[i][j] == additinalFlag) ? true : false;
                    //additinalFlag = field[i][j];
                    if(field[i][j] == additinalFlag){
                        continue;
                    } else return false;
                }
                // additinalFlag = field[i][j];
            }
        }
        return flag;
    }


//------------------------------
    public static void main(String[] args) {
//        while (true) {
            playOneRound();
//            System.out.println("Play again?");
//            if (SCANNER.next().equals("no"))
//                break;
//        }
    }

    private static void playOneRound() {
        initField();
        printField();
        while (true) {
            humanTurn();
            printField();
         //   if (checkWin(DOT_HUMAN)) {
            if (checkWin()) {
                System.out.println("Выиграл игрок!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Ничья!");
                break;
            }
            aiTurn();
            printField();
           //
            if (checkWin()) {
                System.out.println("Выиграл компьютер!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Ничья!");
                break;
            }
        }
    }

}
