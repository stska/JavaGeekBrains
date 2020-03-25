package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGameWindow extends JFrame {

    private static final int GAME_WINDOW_HEIGHT = 480;
    private static final int GAME_WINDOW_WIDTH = 640;

    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int WINDOW_SCREEN_WIDTH = size.width;
         int WINDOW_SCREEN_HEIGHT = size.height;
     private final int WINDOW_POSITION_X = WINDOW_SCREEN_WIDTH/2 - GAME_WINDOW_HEIGHT/2;
     private final int WINDOW_POSITION_Y = WINDOW_SCREEN_HEIGHT/2 - GAME_WINDOW_WIDTH/2;

     private FieldPanel fieldPanel;
     private SettingsWindow settingsWindow;

    MainGameWindow(){
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);   //установили поведение окна при нажатии на крестик
    setSize(GAME_WINDOW_WIDTH,GAME_WINDOW_HEIGHT); //задали размер окна;
        setLocation(WINDOW_POSITION_X,WINDOW_POSITION_Y); //задали положение окна путем задания точки координат(x,y);
        setTitle("The most amazing Tic Tac Toe game EVER"); //Задали название окна

         ImageIcon start = new ImageIcon(new ImageIcon(".src/com/company/start.png").getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT));  //для изменения размера картинки
         JButton startButton = new JButton(start);
         ImageIcon stop = new ImageIcon(new ImageIcon(".src/com/company/stop.png").getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT));  //для и
         JButton stopButton = new JButton(stop);
         settingsWindow = new SettingsWindow(this);
      //повесили обработчик события по нажатию на кнопку stop. При нажатии, произойдет завершение программы с успешным кодом 0 . Подробно мое видение работы обработчика описано в классе SettingWindow
         stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
//вешаем обработчик событий на кнопку старт, который в свою очередь задаст окну видимое состояние.
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                settingsWindow.setVisible(true);
            }
        });
        //если я понял верно, то идеология такая: 1)создаем контейнер и расчерчиваем его по сетке (одна строка и два столбца). В один столбец кладем одну кнопку, в другой другую. Этот контейней мы затем добавляем к нашему текущему окну(т.к просто add, котороу у нас управляеся через layout BorderLayout, состоящий из 5 частей. В одну из частей мы кладем нашу сетку с кнопками
        JPanel canvas = new JPanel(new GridLayout(1,2));//Создаем новую панель - контейней, в котором будем размещать нащи обЪекты. Внутрь него мы помещаем еще один контейнер, в виде таблице с ячейками. одинакового размера. Здесь мы обЪявили переменную типа класса JPanel и инициализировали создавая новый экземляр класса JPanel (new), резеввируем память под него и  передаем в конструктор класса JPanel сетку (новый обЪект класса GridLayout в констуктор которого мы передали два значения. Значенис строки и столбца.
        canvas.add(startButton); //добавили кнопку старт в нашу сетку
        canvas.add(stopButton);     //добавили кнопку старт в нашу сетку
        add(canvas, BorderLayout.SOUTH);//добавили нашу сетку с кнопками (старт и стоп) в контейней вниз(SOUTH), так как BorderLayout используется в JAva Swing для расположения компонентов , контейнер разбит на 5 частей.
        fieldPanel  = new FieldPanel(); // инициализировали новый обЪект с вызовом дефолтного конструктора без передачи значения. Дфолтный конструтор установит значение фона нашего окна в голубой цвет
        add(fieldPanel);      //добавили голубой фон
        setVisible(true); //задали текущему окну состояние true, таким образов сделали его видимым

}
      // описали метод с default модификатором доступа, таким образом, метод виден во всем нашем пакете. Метод ничего не возвращает (void), но на вход принимает 4 параметра
    void startNewGame(int gameMode, int fieldSizeX, int fieldSizeY, int winLength) {
        fieldPanel.startNewGame(gameMode, fieldSizeX, fieldSizeY, winLength); //вызываем метод обЪекта fieldPanel в который передаем параметры для отображения этих параметров в консоле
    }
}
