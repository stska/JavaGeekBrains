package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    static boolean flag =  true;;
    public static void main (String[] args) throws IOException {

        int portNumber = 4444;
       // BufferedReader serverIn;
       // BufferedReader userIn;
      //   PrintWriter out;



           Socket socket = new Socket("localhost",portNumber);

           // out = new PrintWriter(socket.getOutputStream(),true);
           // serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //userIn = new BufferedReader((new InputStreamReader(System.in)));
            DataInputStream serverIn = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner userIn = new Scanner(System.in);

//------new paert
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


        /*
            while(true){
               // String message = userIn.readLine();
                String message = userIn.nextLine();
                if(message.equals("quit")){
                    System.out.println("Finished");
                    out.writeUTF(message);
                    out.flush();
                    break;
                }
               // out.write(message);
                out.writeUTF(message);
                out.flush();
                //System.out.println(serverIn.readLine());
                System.out.println(serverIn.readUTF());


            }
             */

    }
}
/*
public class Client {
    public static void main (String[] args){
        //Socket socket = null;
        int portNumber = 4444;
        try{
           Socket socket = new Socket("localhost",portNumber);
            Thread.sleep(1000);
            Thread server = new Thread(new ServerThread(socket,"test"));
            server.start();

        }catch (IOException e) {
            System.err.println("Oppss не могу соединиться");
        }catch (InterruptedException e){
            System.err.println("Ошибка соединения");
            e.printStackTrace();
        }
    }
}
 */
/*
-----------------------------------------работает но не с каждой сторой
public class Client {
    public static void main (String[] args) throws IOException {

        int portNumber = 4444;
       // BufferedReader serverIn;
       // BufferedReader userIn;
      //   PrintWriter out;


           Socket socket = new Socket("localhost",portNumber);

           // out = new PrintWriter(socket.getOutputStream(),true);
           // serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //userIn = new BufferedReader((new InputStreamReader(System.in)));
            DataInputStream serverIn = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner userIn = new Scanner(System.in);

            while(true){
               // String message = userIn.readLine();
                String message = userIn.nextLine();
                if(message.equals("quit")){
                    System.out.println("Finished");
                    out.writeUTF(message);
                    out.flush();
                    break;
                }
               // out.write(message);
                out.writeUTF(message);
                out.flush();
                //System.out.println(serverIn.readLine());
                System.out.println(serverIn.readUTF());


            }


    }
}
 */