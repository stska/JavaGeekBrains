
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.management.openmbean.InvalidOpenTypeException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setScene(new Scene(root));

        primaryStage.setResizable(false);
        primaryStage.show();

            //обработка закрытия окна с диалоговым меню
           primaryStage.setOnCloseRequest(event -> {
            Alert dialogBox = new Alert(Alert.AlertType.CONFIRMATION,"Может не стоить закрывать и посидим еще?");
            dialogBox.setTitle("Выход");
            dialogBox.setHeaderText(null);
            dialogBox.setResizable(true);

            Optional <ButtonType> result = dialogBox.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK){
                System.out.println("Закрыть окно");
                Controller sendExit = new Controller();
                //не работает этот кусок, что-то с потоком при этом
      /*
                try {
                    sendExit.getOut().writeUTF("_exit_");
                    sendExit.getOut().flush();
                }catch (IOException e){
                    e.printStackTrace();
                }  */
                //--------------------------------------------------------
            }else event.consume();



        });
    }


    public static void main(String[] args) {
        launch(args);

    }

}
