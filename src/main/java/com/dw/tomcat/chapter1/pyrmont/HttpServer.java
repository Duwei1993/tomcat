package dw.tomcat.chapter1.pyrmont;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author DW
 * @date 2021年3月25日19:03:39
 * @version 1.0
 */
public class HttpServer {
    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
    private boolean shutdown = false;

    public static void main(String[] args) throws Exception {
        HttpServer server = new HttpServer();
        server.await();
    }

    public void await() throws Exception {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        //loop waiting for a request
        while (!shutdown) {
            System.out.println("---");
            Socket socket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                socket = serverSocket.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
                //create a request and parse
                Request request = new Request(inputStream);
                request.parse();
                //create response
                Response response = new Response(outputStream);
                response.setRequest(request);
                response.sendStaticResource();
                //close socket
                socket.close();
                //check if the previous URI is shutdown command
                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }
}
