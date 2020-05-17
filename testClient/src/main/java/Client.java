import javafx.scene.control.Label;

import javax.management.openmbean.InvalidOpenTypeException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Controller implements  Runnable {
    //public static void main(String[] args) {
        /*
        try(Socket socket = new Socket("localhost", 8189)){
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            boolean running = true;
            Scanner cin = new Scanner(System.in);
*/
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner cin;

    public Client(DataInputStream in, DataOutputStream out, Scanner cin) {
        this.in = in;
        this.out = out;
        this.cin = cin;
    }

    @Override
    public void run() {
        //  Thread thread = new Thread(()->{

        while (true) {
            String message = null;
            try {
                message = in.readUTF();
                if (message.equals("_exit_")) {
                    in.close();
                    out.close();
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(!message.isEmpty()) {
                System.out.println(message);
                //Label testText = new Label(message);
                try {
                    makeMessage(message, RECEIVERID);
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        }
        // });


      /*  while (true) {
            String line = cin.nextLine();
            if (line.equals("exit")) {
                try {
                    out.writeUTF("_exit_");
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            try {
                out.writeUTF(line);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } */

    }
}
  //  }

//}
