
package com.company;

public class TTT {
    private char [][] board ;
    private char currentPlayerMark;

    public TTT() {   //This is the constructor. It will be responsible for ensuring the board gets initialized properly,
        board = new char [3][3];
        currentPlayerMark = 'x';
        initializeBoard();
    }

    public  void initializeBoard(){       //This method will initialize the board variable such that all slots are empty
        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = '-';
            }
        }
    }

    public char getCurrentPlayerMark()
    {
        return currentPlayerMark;
    }
    public void printBoard() {     ////This method will print the Tic-Tac-Toe board to standard output.
        System.out.println("------------");

        for(int i = 0; i < 3; i++){
            System.out.print("| ");
            for(int j = 0; j < 3; j++){
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("------------");
        }
    }
    public boolean isBoardFull(){       ////This method will check whether or not the board is full. It will return true if the board is full and a false otherwise.
        boolean isFull = true;

        for (int i = 0; i < 3; i++){
            for(int j = 0; j <3; j++){
                if(board[i][j] == '-'){
                    isFull = false;
                }
            }
        }
        return isFull;

    }
    public boolean checkForWin(){ return checkColumnsForWin() || checkRowsForWin() || checkDiagonalsForWin(); } //This method will check to see if a player has won, and if so, it will return true.


    private boolean checkRowsForWin() {          //This method will specifically check the rows for a win.
            for(int i = 0; i < 3; i++){
                if(checkRowCol(board[i][0], board[i][1], board[i][2])){
                    return true;
                }
            }
            return false;
    }
    private boolean checkColumnsForWin(){       ////This method will specifically check the columns for a win.
        for(int i = 0; i < 3; i++){
            if(checkRowCol(board[0][i],board[1][i],board[2][i])){
                return true;
            }
        }
        return false;
    }
    private boolean checkDiagonalsForWin(){          //This method will specifically check the diagonals for a win.
           return checkRowCol(board[0][0],board[1][1],board[2][2]) || checkRowCol(board[2][0],board[1][1],board[0][2]);

    }
    private boolean checkRowCol(char c1, char c2, char c3) {return ((c1 != '-') && (c1 == c2) && (c2 == c3)); }//This method will check the three specified characters taken in to see if all three are the same ‘x’ or ‘o’ letter. If so, it will return true.

    public void changePlayer() {
        currentPlayerMark = currentPlayerMark == 'x' ? 'o' : 'x';
    }
    public boolean placeMark(int row, int col) {
        if((col >= 0) && (col < 3)){
            if((row >= 0) && (row < 3)){
                if(board[row][col] == '-'){
                    board[row][col] = currentPlayerMark;
                    return true;
                }
            }
        }
        return false;
    }

}

