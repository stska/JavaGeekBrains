package com.company;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JFrame {
    //------------------------------------------ Блок обЪявления и инициализации
    private static final int WINDOW_WIDTH = 350; //обЪявление и инициализация статической констансты(final) типа integer с идентификатором WINDOW_WIDTH и значением 350. Так как static, то будет принадлежать классу, а не обЪекту и не доступной вне класса из-за private
    private static final int WINDOW_HEIGHT = 270;
    private static final int MIN_WIN_LENGTH = 3;
    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 10;
    private static final String FIELD_SIZE_PREFIX = "Field size is: ";
    private static final String WIN_LENGTH_PREFIX = "Win length is: ";
   //----------------------------------------------------------------- Блок обЪявления переменных различных типов с тоступом только в этом классе(private)
    private MainGameWindow gameWindow;   //объявление переменной типа GameWindow с идентификатором gameWindow и доступной только данному классу, так как private.
    private JRadioButton humVSAI;     // обЪявление переменной типа JRadioButton, для создания кнопки с поведением типа "radio button" в последствии и выбором "человек против компютера"
    private JRadioButton humVShum;    //обЪявление переменной типа JRadioBurron, для дальнейшего создания кнопки типа "radio button" для выбора "человек vs человек"
    private JSlider slideWinLen;       //обЪявление переменной типа JSlider, для создания в последствии слайдера для выбора выигрышной длины.
    private JSlider slideFieldSize;     // -/- будет отвечать за размер поля
  //------------------------------------------
    //Создание своего конструктора для класса SettingWindow и передачей в него переменной типа GameWindow
    SettingsWindow(MainGameWindow gameWindow) {
        this.gameWindow = gameWindow;          //при создании конструктора,объекту, вызвавшему его, будет присвоено значение gameWindow. this - ссылка на текущий обЪект. таким образом gameWindow текущего обЪекта
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);   //при помози "сеттера" задаем ширину и высоту окна
        Rectangle gameWindowBounds = gameWindow.getBounds(); //обЪявляем переменную типа Rectangle и присваиваем ей значений путем вызова метода getBounds() из объекта gameWindow. getBounds возвращает прямоуголник. У меня возник вопрос сейчас правда, почему мы берем это из GameWindow???
        int posX = (int) gameWindowBounds.getCenterX() - WINDOW_WIDTH / 2; //обЪявляем переменную и инициализируем ее используя gameWindowBounds инициализированный ранее и представляющий из себя прямоугольник. У этого объекта мы вызавыем метод возвращающий центр по X. Из этого мы вычитаем текущую ширину окна настроек деленную на 2.
        int posY = (int) gameWindowBounds.getCenterY() - WINDOW_HEIGHT / 2;
        setLocation(posX, posY);   //с помощью сеттера задаем координату для левой верхнуй точки откуда будет отсчитываться размеры нашего окна.
        setResizable(false); //устанавливаем что мы не можем изменять размер окна
        setTitle("Creating new game"); //задаем с помощью сеттера название нашего окна
        setLayout(new GridLayout(10, 1)); //через сеттер мы соб.....................................................
        addGameControlsMode(); //вызов метода без параметров, созданного нами.
        addGameControlsField(); //выоз метода без параметров, созданного нами.
        JButton btnStart = new JButton("Start new game");  //создали(new) новый обЪект класса JButton c идентификатором btnStart и  передали в конструктов класса JButton текст для задания свойства. Фактически это наша кнопка старт с текстом внутри Start new game
        //к  созданной нами кнопке мы подцепляем "слушателя". Это обработчик события,в нашем случае на нажатие.
        btnStart.addActionListener(new ActionListener() {         //вызываем метод addActionListener объекта brnStart и передаем ему только что созданный безымянный класс,реализующий интерфейс ActionListener, который содержит один метод actionPerformed
            @Override
            public void actionPerformed(ActionEvent e) {      //вызываем метод actionPerformed с параметром - объект, которы содержит информацию о нажатии
                btnStartClick(); //вызываем реализованный нами ниже метод, без параметров /см. строку 88
            }
        });
        add(btnStart); //вызываем метод add с параметром brnStart, который добавляем кнопку в окно. п.с если я правильно понял, то он применяеся к текущему обЪекту, так как в описании метода указано this.
    }
//метод описывающий отображение и добавление вариантов игры
    private void addGameControlsMode() {           //описывам метод доступный только этому классу (private), ничего не возвращающий (void) и не передающий параметров ()
        add(new JLabel("Choose game mode"));      //вызываем метод add в котором в качестве параметров мы передаем только что созданный объект класса JLabel и передаем в его конструктор текс для задания св-ва
        humVSAI = new JRadioButton("Human vs. AI", true); //создаем экземпляр, с помощью оператора new, класса JRadioButton и возвращаем ссылку на вновь созданный обЪект, для обЪявленной ранее переменной с идентификатором humVAIS, в конструкторе передаем text с названием и параетр true,означающий, что это радио-кнопка будет отмечена выбранной по уколчанию
        humVShum = new JRadioButton("Human vs. Human"); // -//-
        ButtonGroup gameMode = new ButtonGroup(); //обЪявляем переменную типа класса ButtonGroup с идентификатором имени gameMode, затем резервируем память под обЪект. Демаем это для последующего обЪекдинения наших кнопок в группу
        gameMode.add(humVSAI); // вызываем метод add у обЪекта gameMode и передаем обЪект, в данном случае нашу кнопку. Здесь мы добавляем кнопку в группу
        gameMode.add(humVShum); // --/--
        add(humVSAI);          //вызываем метод add с параметром в ввиде обЪекта, в данном случае нашей кнопкой. Для добавления ее на канвас.
        add(humVShum);  //      --/--
    }

    //Метод добавления/указания размера поля и добавление/указания выигрышной композиции
    private void addGameControlsField() {      //создаем метод доступный только в этом классе (private), ничего не возвращающий (void) с именем addGameControlsField, без параметром ()
        JLabel lbFieldSize = new JLabel(FIELD_SIZE_PREFIX + MIN_FIELD_SIZE);  //в левой части обЪвляем переменную типа класса JLabel с идентификатором lbFieldSize, в правой части с помощью оператора new мы cоздаем экземпляр класса JLabel и возвращаем ссылку на внось созданный обЪект, также мы резервируем память. С помощью оператора равно присваивается ссылка на новый созданный обЪект. В конструктор передаем два параметра(строковые константы в нашем случае)
        JLabel lbWinLength = new JLabel(WIN_LENGTH_PREFIX + MIN_WIN_LENGTH); // --//-- только уже для выигрышной длины
        slideFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE); //инициализируем новый о
        slideWinLen = new JSlider(MIN_WIN_LENGTH, MIN_FIELD_SIZE, MIN_FIELD_SIZE); //инициализируем новый обЪект сопровожденный вызовом конструктора, к который передаются параметры( в нашем случае константи типа int)
        //в этом блоке мы вешаем обработчий события для слайдера размера поля
        slideFieldSize.addChangeListener(new ChangeListener() { // через точку вызываем метод addChangeListener у обЪекта sliderFieldSize, где в качестве параметров мы передаем только что обЪявленный анонымный класс,реализующий интерфейс ChangeListener, содержащий только один метод stateChanged
            @Override
            public void stateChanged(ChangeEvent e) {  //вызываем метод stateChange , который ничего не возвращает, но передает обЪект класса ChangeEvent (событие e), как параметр
                int currentValue = slideFieldSize.getValue(); //создаем пепеменную типа int и инициализируем ее присваивая значение путем вызова метода getValue через оператор доступа "." для обЪекта sliderFieldSize. Фактически сюда попадает значение которые мы выбрали на слайдоре
                lbFieldSize.setText(FIELD_SIZE_PREFIX + currentValue);   //задаем текущее значение слайдора в наше поле путем вызова метода setText , через точечную нотацию к экземпляру класса, где в качестве параметров мы передали текстовую константу string с описанием поля и численным значением int полученным из слайдора
                slideWinLen.setMaximum(currentValue); //    --//-- фактически задали максимальное значение для слайдера, в данном случае для слайдера с выигрышной длинной.
            }
        });

        //в этом блоке мы вешаем обработчий события для слайдера c установкой выигрышного значения
        slideWinLen.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lbWinLength.setText(WIN_LENGTH_PREFIX + slideWinLen.getValue());   // --//-- фактически устанавливаем текстовое значение для выбранного значения слайдера. В качестве араметров мы прередаем, конкатенируя строковую константу с полем и текущее значение слайдера
            }
        });
        add(new JLabel("Choose field size"));    // --//-- фактически добавили поле в наше окно с настройками и задали текс для поля
        add(lbFieldSize);          // --//-- фактически добавили текстовое поле для отображаемого значения слайдера
        add(slideFieldSize);         //  --//-- добавили слайдер размера поля в наше окно
        add(new JLabel("Choose win length")); // --//-- добавили вспомогательную подпись на экран для слайдера с выигрышеой длиной
        add(lbWinLength);      // --//-- текстовое поле отображаемое выбранное значение слайдера
        add(slideWinLen);     // --//-- добавили слайдер
    }
// метод с именем btnStartClick доступный только классу SettingWindow (т.к private), который ничего не возвращает (void)  и без входных параметров ().       Описываем здесь поведение при нажатии на кномку Старт
    private void btnStartClick() {
        int gameMode;              //объявили переменную типа int с идентификатором имени gameMode
        if (humVSAI.isSelected()) {                     //цикл ветвления if, в тело которого  мы зайдем при условии, что метод isSelected вызванный через точечную нотацию у обЪекта humVSAI вернет true. Вернут тру, если выбран этот мод
            gameMode = FieldPanel.MODE_HVA;            // инициализируем переменную gameMode путем задания параметра 1 в нашем случае. не создавая нового экземпляра класса FieldPanel мы получаем доступ к свой-ву этого класса, так как MODE_HVA у нас static,что значит они принадлежит этому классу,а не оюЪекту и мы можем вызвать ее не создавая обЪект.
        } else if (humVShum.isSelected()) {           // --//-- войдем в внутрь тела, при условии, что выбран режим человек против человека
            gameMode = FieldPanel.MODE_HVH;           // --//- присвоим 0 в нашем случае.
        } else {
            throw new RuntimeException("Unexpected Spanish inquisition!");        //выкидываем исключеним
        }
        int fieldSize = slideFieldSize.getValue();   // обЪявляем переменную fieldSize типа int инициализируем присваивая значения полученного из слайдера размера поля
        int winLen = slideWinLen.getValue();           // --//-- то же самое для выигрыной длины
        gameWindow.startNewGame(gameMode, fieldSize, fieldSize, winLen);     //вызывает метод startNewGame у обЪекта gameWindow класса  GameWindow и передаем в метод параметры: выюранный мод,размер поля по X и по Y и выигрышную длину. п.с тогда для читаемости лучше назвать fieldSizeX и fieldSizeY, как в оригинальеом методе.
        setVisible(false);          // скрываем окно с настройками новой игры
    }

}
