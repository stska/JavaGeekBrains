package com.company;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class WishesClient {
    private static final String ADRESS = "127.0.0.1";
    private static final int PORT = 5000;

    public static void main(String[] args) {

        String message1 = "Hello my little friend";
        try {
            Socket sock = new Socket(ADRESS,PORT);
            OutputStream ostream = sock.getOutputStream();
            DataOutputStream dos = new DataOutputStream(ostream);
            dos.writeBytes(message1);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
