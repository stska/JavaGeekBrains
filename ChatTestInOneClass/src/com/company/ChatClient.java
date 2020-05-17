package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    JTextArea incoming;
    JTextField outgoing;


    BufferedReader reader;
    PrintWriter writer;
    Socket socket;

    public static void main (String[] args){
        ChatClient client = new ChatClient();
        client.go();
    }

    public void go(){
        JFrame frame = new JFrame("Easy chat");
        JPanel mainPanel = new JPanel();
        incoming = new JTextArea(15,50);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);
        incoming.setVisible(true);
        JScrollPane qScroller = new JScrollPane(incoming);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        outgoing = new JTextField(20);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());
        mainPanel.add(qScroller);
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);

        setUp();

        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();

        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setSize(400,500);
        frame.setVisible(true);
    }
    private void setUp(){
        try{
            socket = new Socket("127.0.0.1",5000);
            InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(socket.getOutputStream());
            System.out.println("seems it's working");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public class SendButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try{
                writer.println(outgoing.getText());
                writer.flush();
            }catch (Exception e){
                e.printStackTrace();
            }
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }
    public class IncomingReader implements Runnable{
        public void run(){
            String message;
            try{
                while((message = reader.readLine()) != null){
                    System.out.println("read from Client class" + message);
                    incoming.append(message);

                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}