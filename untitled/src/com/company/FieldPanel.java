package com.company;
import javax.swing.*;
import java.awt.*;

public class FieldPanel extends JPanel {
    public static final int MODE_HVH = 0;
    public static final int MODE_HVA = 1;

    FieldPanel() {
        setBackground(Color.BLUE);
    }

    public void startNewGame(int gameMode, int fieldSizeX, int fieldSizeY, int winLength) {
        System.out.printf("damn mode: %d\nfield size: %d,\nwin length: %d\n", gameMode, fieldSizeX, winLength);
    }
}

/*
1. Полностью разобраться с кодом (попробовать полностью самостоятельно
переписать одно из окон)

2. Составить список вопросов и приложить в виде комментария к домашней
работе

3. * Расчертить панель Map на поле для игры, согласно fieldSize
* */
