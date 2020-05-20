import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {
//TODO убрть весь код, что задает стиль и сделать CSS файл

    public ScrollPane mainScrollChatField;
    public VBox vBox;
    //public Label userNickLabel;
    @FXML
    Label userNickLabel; // TODO сделать так чтобы при получении ника надпись не сЪезжала. Сейчас смотрится криво. Когда написано просто Вы, то норм, но с ником просто ужас.
    @FXML
    TextField userChatField;
    @FXML
    ListView usersList;

    private DataInputStream in;
    private DataOutputStream out;
    private Scanner cin;
    private OutputStream outMsgReconrd;

    public DataOutputStream getOut() {
        return out;
    }

    private Socket socket;
    final String SENDERID = "sender";
    final String RECEIVERID = "receiver";
    private String nick;
    private ArrayList<String> tmp;
   // private ArrayList<String> tmpMessagesToWrite;
    //private int tmpCounter = 0;


    public void sendButton(ActionEvent actionEvent) {
        vBox.heightProperty().addListener(observable -> mainScrollChatField.setVvalue(1.0));
        System.out.println(userChatField.getText());
        String textFromChatField = userChatField.getText();

        try {
            out.writeUTF(nick + ": " + textFromChatField);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        makeMessage("[Вы]: " + textFromChatField, SENDERID);
        userChatField.setText("");
        userChatField.requestFocus();

    }

    private void setUp() {
        try {
            socket = new Socket("localhost", 8189);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            cin = new Scanner(System.in);
            System.out.println("Соединения от клиента установлено");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void makeMessage(String text, String id) {
        FlowPane flowPane = new FlowPane();
        //TODO убрать бесполезный кусок и внести про ник в блок ниже

        if (text.contains("Hello /")) {
            String[] splIttext = text.split("/");
            String[] subText = splIttext[1].split("&"); //

            nick = subText[0];
            userNickLabel.setText(subText[0]);

            try {
                outMsgReconrd = new BufferedOutputStream(new FileOutputStream(nick + ".txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
//-------------------
        }
        //когда уже в чате и новый приходит, то добавить его в список онлайн
        if (text.contains("Поприветствум нового юзера! -> ")) {
            String[] newUserinList = text.split(">");
            usersList.getItems().add(newUserinList[1]);
        }
        if (text.contains("Hello /client")) {
            String[] splIttext = text.split("/");

            //---------------------------------------------------------
            String[] usersOnline = splIttext[1].split("&"); //????????????

            writeHistoryChatLog(usersOnline[0]);         //---------------------- ДЗ №3 для записи первой приветственной строки
            //   tmpMessagesToWrite.add(usersOnline[0]);
            //   tmpCounter++;
            flowPane.getChildren().add(new Label("Welcome!!! " + usersOnline[0]));
            for (String user : usersOnline) {
                if (!user.equals(nick)) {
                    usersList.getItems().add(user);
                }
            }
        } else if (text.contains("newNickName/")) {
            String[] splitTextArray = text.split("/");
            nick = splitTextArray[1];

            userNickLabel.setText(splitTextArray[1]); // Не обновляется label здесь TODO подумать как сделать без доп. потоков o_0


        } else {
            Label textLabel = new Label(text);
            textLabel.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
            flowPane.getChildren().add(textLabel);
            if (id.equals("sender")) {
                flowPane.setAlignment(Pos.BOTTOM_LEFT);
            } else flowPane.setAlignment(Pos.BASELINE_RIGHT);
            writeHistoryChatLog(text); //здесь пишется основной лог, все, что отображается в чате проходит тут, уже без служебных комманд
            //здесь планирую реализовать чтобы записывал по 100. Времени не хватило и что-то не так, поэтому закомментил пока.
            //Идея в том, чтобы во временный ArrayList добавлять сообщения и потом из него уже писать в файл, как только ArrayList достигает 100,
            // первое сообщение удаляется, а в конец добавляется новое.
            //Либо, еще хочу попробовать, просто открывать файл и добавлять туда и удалять от туда же, просто завести счетчик по которому будем первую строку удалять.
            //Если размышления не верны, то поправте и дайте совет , как будет по равильному.
         /*   if(tmpCounter < 100) {
               tmpMessagesToWrite.add(text);
           }else {
               tmpMessagesToWrite.remove(0);
               tmpMessagesToWrite.add(text);
            }
            tmpCounter++;  */
        }
        Platform.runLater(() -> vBox.getChildren().add(flowPane));
        mainScrollChatField.setContent(vBox);


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userChatField.requestFocus();
        System.out.println("test message");
        //boolean authFlag = false;
        // authInitialize();

        setUp();

        //------
        //  firstEntry();
        // String authMsg = authInitialize();
        //--------------------------------------------------------------new
        String authMsg = null;
        try {
            authMsg = firstEntry();
            //firstEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String finalAuthMsg = authMsg;
        Thread readT = new Thread(() -> {
            readFromHistoryAndWriteIt();
            if (finalAuthMsg != null) {

                makeMessage(finalAuthMsg, RECEIVERID);
                makeHistory(tmp);  //здесь почле приветсвия подкгружаем чат, по хорошему нужно, сделать приветствие после, но пока что вылетает exception,поэтому после
            }

            while (true) {
                // String message = null;
                try {
                    if (!socket.isClosed()) {
                        String message = in.readUTF();
                        if (message.equals("_exit_")) {
                            in.close();
                            out.close();
                            break;
                        }
                        if (!message.isEmpty()) {
                            System.out.println(message);
                            makeMessage(message, RECEIVERID);
                            //TODO попробовать вставить сюда работу по мониторингу и поддержанию актуального списка онлйан
                            //TODO вставить сюда запись изменения ника может он изменится в lavel
                            //testText = message;
                        }
                        Thread.sleep(1000);
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        readT.setDaemon(true);
        readT.start();


    }

    //временно пока таким окном, пока не прикручу другую сцену из EnterPoint и enterPoint.xml
    //TODO заменить на нормальный вариант когда с разными окнами и сценами
    private String authInitialize(String title) throws IOException {
        boolean flag = true;
        String returnMsg = null;
        String welcome = null;
        while (flag) {
            Dialog<Pair<String, String>> dialog = new Dialog<>(); //почему-то не работает при повторном вызове из функции makeMessage,
            // когда от сервера пришло, что мы не правильно ввели пароль или логин и выдает на этой строке java.lang.IllegalStateException: Not on FX application thread; currentThread = Thread-3

            if (title.equals("Вход")) {
                dialog.setTitle("Вход");
            } else dialog.setTitle("Регистрация");
            dialog.setHeaderText("Внимательно заоплните поля");

            //Задаем кнопки, в нашем случае мы описали кнопку Login вместо окей и потом просто добаляем кнопки, нашу и стандартную cancel)
            ButtonType loginBtn = new ButtonType("Login", ButtonBar.ButtonData.APPLY.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginBtn, ButtonType.CANCEL);

            //Создаем поля для логина и пароля и размещаем их на сетке
            GridPane grid = new GridPane();
            grid.setHgap(15);
            grid.setVgap(15);
            grid.setPadding(new Insets(25, 150, 15, 15));

            TextField userName = new TextField();
            userName.setPromptText("OK");
            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("Пароль");

            grid.add(new Label("Пользователь"), 0, 0);
            grid.add(userName, 1, 0);
            grid.add(new Label("Пароль"), 0, 1);
            grid.add(passwordField, 1, 1);

            Node loginButton = dialog.getDialogPane().lookupButton(loginBtn);
            loginButton.setDisable(true);

            userName.textProperty().addListener((observable, oldValue, newValue) -> {
                loginButton.setDisable(newValue.trim().isEmpty());
            });
            dialog.getDialogPane().setContent(grid);
            Platform.runLater(userName::requestFocus);
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginBtn) {
                    return new Pair<>(userName.getText(), passwordField.getText());
                }
                return null;
            });
            // String returnMsg = null;
            Optional<Pair<String, String>> result = dialog.showAndWait();
            /*
            result.ifPresent(userNamePwd -> {
                System.out.println("Login = " + userNamePwd.getKey() + ", Password = " + userNamePwd.getValue());

            }); */
            if (result.isPresent()) {
                if (title.equals("Регистрация")) {
                    returnMsg = "serverAuth" + "@/" + result.get().getValue() + "@/" + result.get().getKey();
                } else returnMsg = "signIn" + "@/" + result.get().getValue() + "@/" + result.get().getKey();
            }
            //на сервер отправили логин и пароль с пометкой signIn@/, если есть такой, то появится окно чата с приветствием, если нет, то пробуем дальше.

            out.writeUTF(returnMsg);

            if (returnMsg.contains("signIn@/")) {
                String messageFrom = in.readUTF();
                if (!messageFrom.contains("newAttemptToSignIn@/")) {
                    flag = false;
                    welcome = messageFrom;
                }
            } else {
                welcome = in.readUTF();
                flag = false;
            }

        }
        return welcome;

    }


    public void usersListClick(MouseEvent mouseEvent) throws IOException {

        //TODO нормальный ListView<Item> а не как сейчас. Тогда через метод getSelectionModel().getSelectedIndex() получения индекса выделенного, я буду получать элемент, а не как сейчас
        //првоерка, чтобы не реагировал на клики на пустые листы
        if (usersList.getSelectionModel().getSelectedIndices().size() == 0) {
            mouseEvent.consume();
            usersList.getSelectionModel().clearSelection();
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("privateMessage.fxml"));
        Parent root = loader.load();
        PrivateMessageController privateMsgCont = loader.getController();
        String userToChat = usersList.getSelectionModel().getSelectedItem().toString();
        privateMsgCont.setToUser(userToChat);
        privateMsgCont.setUserFrom(nick);
        privateMsgCont.setOut(out);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Private Message");
        stage.show();
        usersList.getSelectionModel().clearSelection(); //чтобы не снять выделение с пользователя кому отправлено сообщение личное

    }

    //При нажатии на Nick под аватаром появитс яокно смены
    public void changeNickName(MouseEvent mouseEvent) throws IOException {

        TextInputDialog newNickNameDialog = new TextInputDialog();
        newNickNameDialog.setTitle("Смена ника");

        newNickNameDialog.setContentText("Введите новый nick");
        Optional<String> result = newNickNameDialog.showAndWait();
        if (result.isPresent()) {
            out.writeUTF("nick/" + result.get());
            out.flush();

        }
    }

    private String firstEntry() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Выбор входа");
        alert.setContentText("Выберите, что вы хотите сделать.");

        ButtonType buttonTypeOne = new ButtonType("Войти");
        ButtonType buttonTypeTwo = new ButtonType("Зарегистрироваться");
        ButtonType buttonTypeCancel = new ButtonType("Отмена", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            System.out.println(1);
            return authInitialize("Вход");

        } else if (result.get() == buttonTypeTwo) {
            System.out.println(2);
            return authInitialize("Регистрация");
            // ... user chose CANCEL or closed the dialog
        }
        return null;
    }
//------------------------------------------------------------ДЗ №3
    private void readFromHistoryAndWriteIt() {
        try {
            File file = new File("client#1.txt"); //я понимаю, что здесь должен подкидываться логин,сейчас в качестве проверки стоит это название для подргузки, к след. разу заменю на переменную которая берется из логина.
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line;
            tmp = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                System.out.println(line); //для отладки
                tmp.add(line);

            }
            fr.close();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
//функция для подгрузки истории сообщений. При вызове функции, передается ArrayList tmp в который мы считали данные из файла.
    private void makeHistory(ArrayList<String> hist) {
        for (String msg : hist) {
            if (msg.contains("Вы")) {
                makeMessage(msg, SENDERID);
            } else makeMessage(msg, RECEIVERID);
        }
        tmp.clear();
    }
    //сюда передаем по строке из чата, добавляем переход на новую строку в конце и записываем в файл
    private void writeHistoryChatLog(String text) {
        StringBuilder writeClientMsg = new StringBuilder();
        writeClientMsg.append(text).append("\n");
        byte[] clientMsgNew = writeClientMsg.toString().getBytes();
        try {
            outMsgReconrd.write(clientMsgNew);
            outMsgReconrd.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}