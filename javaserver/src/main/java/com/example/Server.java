package com.example;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.BorderLayout;



/**
 * Created by Gary on 16/5/28.
 */
public class Server  extends JFrame implements Runnable{
    private Thread thread;
    private ServerSocket servSock;
    private String msg;
    JFrame demo = new JFrame();
    JLabel label;
    boolean read=false;
    Socket clntSock;
    InputStream in;

    public Server(){
        setLayout(null);
        demo.setSize(400, 300);
        demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        try {
            // Detect server ip
            InetAddress IP = InetAddress.getLocalHost();
            msg = new String("IP of my system is : "+IP.getHostAddress());
            System.out.println(msg);
            System.out.println("Waitting to connect......");

            // Create server socket
            servSock = new ServerSocket(2000);

            // Create socket thread
            thread = new Thread(this);
            thread.start();
        } catch (java.io.IOException e) {
            System.out.println("Socket啟動有問題 !");
            System.out.println("IOException :" + e.toString());
        } finally{

        }

        label = new JLabel(msg);
        demo.getContentPane().add(BorderLayout.NORTH , label);
        demo.setVisible(true);
    }

    @Override
    public void run(){
        // Running for waitting multiple client
        while(true){
            if (!read){
                try{
                    // After client connected, create client socket connect with client

                    clntSock = servSock.accept();
                    in = clntSock.getInputStream();

                    System.out.println("Connected!!");

                    // Transfer data
                    byte[] b = new byte[1024];
                    int length;

                    length = in.read(b);
                    String s = new String(b);
                    System.out.println("[Server Said]" + s);
                    label.setText(s);
                    read=true;
//                demo.getContentPane().add(BorderLayout.NORTH , label);
//                demo.setVisible(true);

                }
                catch(Exception e){
                    //System.out.println("Error: "+e.getMessage());
                }

            }else {
                try{
                    // Display what the client send

                    in = clntSock.getInputStream();

                    // Transfer data
                    byte[] b = new byte[1024];
                    int length;

                    length = in.read(b);
                    String s = new String(b);
                    label.setText("The result from app is : "+s);

//                demo.getContentPane().add(BorderLayout.NORTH , label);
//                demo.setVisible(true);

                }
                catch(Exception e){
                    //System.out.println("Error: "+e.getMessage());
                }

            }
        }
    }
}
