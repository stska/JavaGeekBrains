package server;

import javax.annotation.processing.SupportedSourceVersion;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatServer {
    static int portNumber = 4444;


    //static Scanner input = new Scanner(System.in);
    static private boolean flag = true;

    public static void main(String[] args) {
        // BufferedReader in;
        // PrintWriter out;
        //  Scanner input = new Scanner(System.in);

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket socket = serverSocket.accept();
            System.out.println("Соединение с  " + socket.getInetAddress() + " выполнено успешно");
         //   PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
           // BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
           Scanner input = new Scanner(System.in);
            out.writeUTF("Добро пожаловать в чат");
         //   Object mutex = new Object();

            Thread inputThread = new Thread(() -> {
                //while (flag) {
                   // synchronized (mutex) {
                       // mutex.notify();
                while(flag) {
                   // Scanner input = new Scanner(System.in);
                        String messageFromServer = input.nextLine();

                            try {
                                if (socket.getInetAddress().isReachable(1000)) {////////--------------new
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
            //inputThread.start();

            Thread readingThread = new Thread(() -> {

                while (flag) {
                  //  synchronized (mutex){
                    try {
                      //  mutex.notify();

                            String clientMessage = in.readUTF();

                            if (clientMessage.equals("quit")) {
                                System.out.println("Byee");
                                flag = false;
                              //  in.close();
                             //   out.close();
                              //  input.close();
                                //return;
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
           // System.exit(1);
        }

    }

    // out = new PrintWriter(socket.getOutputStream(),true);
    //            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


}


/*
public class ChatServer {
    static int portNumber = 4444;
    static ServerSocket serverSocket = null;
  //  static ArrayList<ClientThread> clients;
    public static void main(String[] args) {

        try {
            serverSocket = new ServerSocket(portNumber);
            acceptClients();
        } catch (IOException e) {
            System.err.println("Не могу работать с этим портом " + portNumber);
            System.exit(1);
        }

    }
    private static void acceptClients() {

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                ClientThread client = new ClientThread(socket);
                Thread t = new Thread(client);
                t.start();
                System.out.println("Hello user " + socket.getInetAddress());

                //clients.add(client);
            } catch (IOException e) {
                System.out.println("Не приняли клиента, не знаю почему, на порте " + portNumber);
            }
        }
    }
}
 */

/*----------------------------------------------------------------------------------------
Another VERSIONNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN
public class ChatServer {
    static int portNumber = 4444;
    static BufferedReader in;
    static PrintWriter out;
    static Scanner input = new Scanner(System.in);
    static private boolean flag = true;

    public static void main(String[] args) {
        // BufferedReader in;
        // PrintWriter out;
        //  Scanner input = new Scanner(System.in);

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            acceptClients(serverSocket);
        } catch (IOException e) {
            System.err.println("Не могу работать с этим портом " + portNumber);
            System.exit(1);
        }

    }

    // out = new PrintWriter(socket.getOutputStream(),true);
    //            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    static void acceptClients(ServerSocket serverSocket) {

        while (true) {
            try {

                Socket socket = serverSocket.accept();
                System.out.println("Соединение с  " + socket.getInetAddress() + " выполнено успешно");
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Thread inputThread = new Thread(() -> {
                    while (flag) {
                        String messageFromServer = input.nextLine();
                        if (flag) {
                            try {
                                out.write("Это от сервера: " + messageFromServer);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }


                });

                inputThread.start();

                while (true) {

                    String clientMessage = in.readLine();
                    if (clientMessage.equals("quit")) {
                        System.out.println("Byee");
                        flag = false;
                        return;
                    }else if(clientMessage.equals("Hello")){
                        out.write(socket.getInetAddress() + "I got you");
                    }else {
                        System.out.println("Сообщение от пользователя " + clientMessage);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
 */
/*
--------------------не кадждая строка
public class ChatServer {
    static int portNumber = 4444;


    static Scanner input = new Scanner(System.in);
    static private boolean flag = true;

    public static void main(String[] args) {
        // BufferedReader in;
        // PrintWriter out;
        //  Scanner input = new Scanner(System.in);

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket socket = serverSocket.accept();
            System.out.println("Соединение с  " + socket.getInetAddress() + " выполнено успешно");
         //   PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
           // BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            Thread inputThread = new Thread(() -> {
                while (flag) {
                    String messageFromServer = input.nextLine();
                    if (flag) {
                        try {
                            out.writeUTF("Это от сервера: " + messageFromServer);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }


            });
            inputThread.start();

            Thread readingThread = new Thread(() -> {
                while (true) {
                    try{
                    String clientMessage = in.readUTF();
                   // System.out.println("Сообщение от пользователя 1" + clientMessage);
                    if (clientMessage.equals("quit")) {
                        System.out.println("Byee");
                        flag = false;
                        return;
                    } else if (clientMessage.equals("Hello")) {
                        // out.write(socket.getInetAddress() + "I got you");\
                        out.writeUTF(socket.getInetAddress() + "I got you");
                        out.flush();
                    } else {
                        System.out.println("Сообщение от пользователя 2" + clientMessage);
                    }


                }catch (IOException e){
                        e.printStackTrace();
                    }
            }});
            readingThread.start();



        } catch (IOException e) {
            e.printStackTrace();
           // System.exit(1);
        }

    }

    // out = new PrintWriter(socket.getOutputStream(),true);
    //            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


}
 */