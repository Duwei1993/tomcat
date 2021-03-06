package com.dw.tomcat.chapter2.pyrmont;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author DW
 * @version  1.0
 */
public class HttpServer1 {
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer1 server = new HttpServer1();
        server.await();
    }

    public void await(){
        ServerSocket serverSocket = null;
        int port = 8080;
        try{
            serverSocket = new ServerSocket(port,1, InetAddress.getByName("127.0.0.1"));
        }catch(IOException e){
            e.printStackTrace();
            System.exit(1);
        }

        //Loop waiting for a request
        while (!shutdown){
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;
            try{
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                //create Request object and parse
                Request request = new Request(input);
                request.parse();

                //create response object
                Response response = new Response(output);
                response.setRequest(request);

                //check if this is a request for a servlet or
                // static resource
                //a request for a servlet begins with "/servlet/"
                if (request.getUri().startsWith("/servlet")){
                    ServletProcessor1 processor = new ServletProcessor1();
                    processor.process(request,response);
                }else{
                    StaticResourceProcessor processor = new StaticResourceProcessor();
                    processor.process(request,response);
                }

                //Close the socket
                socket.close();
                //check if the previous URL is a shutdown command
                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);

            }catch (Exception e){
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
