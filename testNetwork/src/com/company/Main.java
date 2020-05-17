package com.company;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(50 00);
        System.out.println(socket.getInetAddress());
    }
}
