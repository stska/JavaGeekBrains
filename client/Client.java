package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    static boolean flag =  true;;
    public static void main (String[] args) throws IOException {

        int portNumber = 4444;



           Socket socket = new Socket("localhost",portNumber);

            DataInputStream serverIn = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner userIn = new Scanner(System.in);


                new Thread(() -> {
                    while (flag) {

                        try {

                                String message = userIn.nextLine();
                            if (message.equals("quit")) {
                                System.out.println("Finished");
                                out.writeUTF(message);
                                out.flush();
                                flag = false;
                                serverIn.close();
                                out.close();
                                userIn.close();
                                //break;
                                System.exit(0);
                            }
                            out.writeUTF(message);
                            out.flush();
                            Thread.sleep(100);

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                new Thread(() -> {
                    while (flag) {
                        try {
                            System.out.println(serverIn.readUTF());
                            Thread.sleep(100);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();




    }
}
 */