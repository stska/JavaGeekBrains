
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javax.management.openmbean.InvalidOpenTypeException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {


    public ScrollPane mainScrollChatField;
    public VBox vBox;
    @FXML
    TextField userChatField;

   private   DataInputStream in;
   private DataOutputStream out;
   private   Scanner cin;

    private Socket socket;
    final String SENDERID = "sender";
    final String RECEIVERID = "receiver";
    private String nick;

    public void sendButton(ActionEvent actionEvent) {
        vBox.heightProperty().addListener(observable -> mainScrollChatField.setVvalue(1.0));
        System.out.println(userChatField.getText());
        String textFromChatField = userChatField.getText();

        try{
            out.writeUTF(nick + ": " + textFromChatField);
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
        makeMessage("[Вы]: " + textFromChatField ,SENDERID);
        userChatField.setText("");
        userChatField.requestFocus();
    }
    private void setUp(){
        try{

            socket = new Socket("localhost", 8189);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            cin = new Scanner(System.in);
            System.out.println("Соединения от клиента установлено");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

  void makeMessage(String text, String id) {
      FlowPane flowPane = new FlowPane();
      if (text.contains("Hello /")) {
          String[] splIttext = text.split("/");
          nick = splIttext[1];
      }
      if (text.contains("Hello /client")) {
          String[] splIttext = text.split("/");
          flowPane.getChildren().add(new Label("Welcome!!! " + splIttext[1]));
      } else {
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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userChatField.requestFocus();
        System.out.println("test message");

        setUp();
        new Thread(() -> {
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
        }).start();
    }
}