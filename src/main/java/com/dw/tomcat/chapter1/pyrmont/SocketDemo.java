package com.dw.tomcat.chapter1.pyrmont;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author DW
 */
public class SocketDemo {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("www.baidu.com", 80);
        OutputStream outputStream = socket.getOutputStream();
        boolean autoflush = true;
        PrintWriter outWriter = new PrintWriter(socket.getOutputStream(), autoflush);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //send an http request to the web server
        outWriter.println("GET / HTTP/1.1");
        outWriter.println("Host: www.baidu.com:80");
        outWriter.println("Connection: Close");
        outWriter.println();

        //read the response
        boolean loop = true;
        StringBuffer stringBuffer = new StringBuffer(8096);
        while (loop) {
            if (in.ready()) {
                int i = 0;
                while (i != -1) {
                    i = in.read();
                    stringBuffer.append((char) i);
                }
                loop = false;
            }
            Thread.sleep(50);
        }
        System.out.println(stringBuffer.toString());
        socket.close();
    }
}
