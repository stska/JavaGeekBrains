package com.company;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class WishesServer {
    public static void main(String args[]) throws Exception {

        ServerSocket sersock = new ServerSocket(5000);
        System.out.println("server is ready");

        Socket sock = sersock.accept();

        InputStream istream = sock.getInputStream();
        DataInputStream dstream = new DataInputStream(istream);

        String message2 = dstream.readUTF();
        System.out.println(message2);

        dstream.close();
        istream.close();
        sock.close();
        sersock.close();

    }

}

