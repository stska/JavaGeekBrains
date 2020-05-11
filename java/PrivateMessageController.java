import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.DataOutputStream;
import java.io.IOException;

public class PrivateMessageController {

    @FXML
    private TextField privateField;

    @FXML
    private Button privateSendBtn;

    @FXML
    private Label toUser;


   private String userFrom;
   private DataOutputStream out;

   void setToUser(String user){
       this.toUser.setText(user);
   }
   void setUserFrom(String user){ this.userFrom = user;}
   void setOut(DataOutputStream out){
       this.out = out;
   }
    public void privateSendAction(ActionEvent actionEvent) {
        System.out.println(privateField.getText());
        String pvtMessage = privateField.getText();
        try {
            out.writeUTF(("privateMessage@//" + userFrom + "@//" + toUser.getText() + "@//" + pvtMessage));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        privateField.setText("");

    }
}