package server;

import javax.annotation.processing.SupportedSourceVersion;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatServer {
    private static int portNumber = 4444;

    static private boolean flag = true;

    public static void main(String[] args) {


        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket socket = serverSocket.accept();
            System.out.println("Соединение с  " + socket.getInetAddress() + " выполнено успешно");

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

           Scanner input = new Scanner(System.in);
            out.writeUTF("Добро пожаловать в чат");
         //   Object mutex = new Object();

            Thread inputThread = new Thread(() -> {

                   // synchronized (mutex) {
                       // mutex.notify();
                while(flag) {
                   // Scanner input = new Scanner(System.in);
                        String messageFromServer = input.nextLine();

                            try {
                                if (socket.getInetAddress().isReachable(1000)) {
                                    out.writeUTF("Это от сервера: " + messageFromServer);
                                //   mutex.wait();
                            }
                                Thread.sleep(1000);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }


               // }


            });

            Thread readingThread = new Thread(() -> {

                while (flag) {
                  //  synchronized (mutex){
                    try {
                      //  mutex.notify();

                            String clientMessage = in.readUTF();

                            if (clientMessage.equals("quit")) {
                                System.out.println("Byee");
                                flag = false;
                                System.exit(0);
                            } else if (clientMessage.equals("Hello")) {
                                // out.write(socket.getInetAddress() + "I got you");\
                                out.writeUTF(socket.getInetAddress() + "I got you");
                                out.flush();
                            } else {
                                System.out.println("Сообщение от пользователя " + clientMessage);
                            }
                            //    mutex.wait();

                            Thread.sleep(1000);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
               // }
            }});

            inputThread.start();
            readingThread.start();



        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

}


