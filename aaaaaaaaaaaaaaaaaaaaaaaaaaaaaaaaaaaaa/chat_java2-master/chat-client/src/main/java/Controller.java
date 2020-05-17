
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
    DataInputStream in;
    DataOutputStream out;
    Scanner cin;
    Socket socket;
    final String SENDERID = "sender";
    final String RECEIVERID = "receiver";

    public void sendButton(ActionEvent actionEvent) {
        vBox.heightProperty().addListener(observable -> mainScrollChatField.setVvalue(1.0)); //чтобы скрол всегда был внизу

        /*
        final String SENDERID = "sender";
        final String RECEIVERID = "receiver";
        //mainScrollChatField.setVvalue(1.0);

        System.out.println(userChatField.getText());
        Label testText = new Label(userChatField.getText());
        //пока для иллюстрации изображено, потом буду различать уже как-то когда будет и сервер и клиентская часть)
        if(!userChatField.getText().isEmpty()){
            makeMessage(testText, SENDERID);
            makeMessage(testText, RECEIVERID);
        }
        */
        System.out.println(userChatField.getText());
        try{
            out.writeUTF(userChatField.getText());
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
        //final String SENDERID = "sender";
        //Label testText = new Label(userChatField.getText());
        makeMessage(userChatField.getText(),SENDERID);



    }
    /*
    @FXML
    public void initialize() {
        System.out.println("test message");
        userChatField.requestFocus();     //не работает тут (( не знаю уже куда поставить, чтобы в чате сразу был фокус здесь

        setUp();
        Thread readerT = new Thread(new Client(in,out,cin));
        readerT.start();

    }  */
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

  void makeMessage(String testText, String id) {
        // HBox hBox = new HBox();  закомментил, так как мне переносится на другую строку сообщение и решил попробовать с FlowPane, но всеравно идет вширь....походу я выбрал не ту обертку у родителя
        FlowPane flowPane = new FlowPane();
        flowPane.setPrefWrapLength(50);
        Label textLabel = new Label(testText);
       // if (id.equals("sender")) {

            textLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            flowPane.getChildren().add(textLabel);
            flowPane.setPadding(new Insets(10, 0, 5, 10));
            flowPane.setAlignment(Pos.BOTTOM_LEFT);
            // hBox.setBackground(new Background(new BackgroundFill(Color.rgb(255, 235, 179), CornerRadii.EMPTY, Insets.EMPTY)));
            // hBox.getChildren().add(testText);
            //  hBox.setPadding((new Insets(10, 0, 5, 10)));
            // hBox.setAlignment(Pos.BOTTOM_LEFT);
            userChatField.setText("");
        //} else {
          //  Label label = new Label("Не пиши мне больше!!!!");
           // label.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
           // vBox.setAlignment(Pos.BASELINE_RIGHT); ???????????????????????????????????????????????????????????????????????????????????
            //  hBoxTest.setBackground(new Background(new BackgroundFill(Color.BISQUE, CornerRadii.EMPTY, Insets.EMPTY)));

            //hBox.getChildren().add(label);
            //  hBox.setAlignment(Pos.BASELINE_RIGHT);
            //test--------------------------------------
       //     flowPane.getChildren().add(textLabel);
         //   flowPane.setAlignment(Pos.BASELINE_RIGHT);

      //  }
        // vBox.getChildren().add(hBox);
        vBox.getChildren().add(flowPane);
        vBox.setSpacing(10);
        mainScrollChatField.setContent(vBox);
        userChatField.requestFocus();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userChatField.requestFocus();
        System.out.println("test message");

        setUp();
        Thread readerT = new Thread(new Client(in,out,cin));
        readerT.start();
    }
}