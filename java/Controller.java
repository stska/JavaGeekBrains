
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
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.management.openmbean.InvalidOpenTypeException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {


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

    public DataOutputStream getOut() {
        return out;
    }

    private Socket socket;
    final String SENDERID = "sender";
    final String RECEIVERID = "receiver";
    private String nick;

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
        //убрать бесполезный кусок и внести про ник в блок ниже

        if (text.contains("Hello /")) {
            String[] splIttext = text.split("/");
            String[] subText = splIttext[1].split("&"); //

            nick = subText[0];
            userNickLabel.setText(subText[0]);
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
            String[] usersOnline =splIttext[1].split("&"); //????????????
            flowPane.getChildren().add(new Label("Welcome!!! " + usersOnline[0]));
            for (String user : usersOnline) {
                   if(!user.equals(nick)){
                    usersList.getItems().add(user);
                }
            }
            }else if(text.contains("newNickName/")){
                  String[] splitTextArray = text.split("/");
                  nick = splitTextArray[1];

                  userNickLabel.setText(splitTextArray[1]); // Не обновляется label здесь TODO подумать как сделать без доп. потоков o_0


        }
        else{
                Label textLabel = new Label(text);
                textLabel.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
                flowPane.getChildren().add(textLabel);
                if (id.equals("sender")) {
                    flowPane.setAlignment(Pos.BOTTOM_LEFT);
                } else flowPane.setAlignment(Pos.BASELINE_RIGHT);
            }
            Platform.runLater(() -> vBox.getChildren().add(flowPane));
            mainScrollChatField.setContent(vBox);

        }


        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
            userChatField.requestFocus();
            System.out.println("test message");

            setUp();
            Thread readT = new Thread(() -> {
                while (true) {
                    // String message = null;
                    try {
                        String message = in.readUTF();
                        if (message.equals("_exit_")) {
                            in.close();
                            out.close();
                            break;
                        }
                        if (!message.isEmpty()) {
                            System.out.println(message);
                            makeMessage(message, RECEIVERID);
                            //testText = message;
                        }
                        Thread.sleep(1000);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            readT.setDaemon(true);
            readT.start();
        }

    public void usersListClick(MouseEvent mouseEvent) throws IOException {

        //TODO нормальный ListView<Item> а не как сейчас. Тогда через метод getSelectionModel().getSelectedIndex() получения индекса выделенного, я буду получать элемент, а не как сейчас
        //првоерка, чтобы не реагировал на клики на пустые листы
        if(usersList.getSelectionModel().getSelectedIndices().size() == 0){
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
        if(result.isPresent()){
            out.writeUTF("nick/" + result.get());
            out.flush();

        }
    }
}