package com.dw.tomcat.chapter3.prymont.connector.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author DW
 * @version 1.0
 * @date 2021/3/31 21:34
 **/
public class HttpConnector implements Runnable {
    boolean stooped;
    private String scheme = "http";

    public String getScheme(){
        return scheme;
    }

    /**
     * Implements the java.lang.Runnable interface method to instantiate a
     * serverSocket and ready to accept request
     */
    @Override
    public void run() {
        ServerSocket serverSocket = null;
        int prot = 8080;
        //Instantiate the serverSocket
        try {
            serverSocket = new ServerSocket(prot,1, InetAddress.getByName("127.0.0.1"));
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }

        while (!stooped){
            //Accept the next incoming connection from the server socket
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            }catch (Exception e){
                continue;
            }
            //instantiate a httpProcessor for every httpRequest and
            // hand this socket off to an HttpProcessor

        }
    }

    /**
     * Instantiate a new thread to execute the run method
     */
    public void start(){
        Thread thread = new Thread(this);
        thread.run();
    }
}
