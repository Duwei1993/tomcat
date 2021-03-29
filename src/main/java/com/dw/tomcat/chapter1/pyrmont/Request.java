package com.dw.tomcat.chapter1.pyrmont;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author DW
 * @date 2021年3月25日19:03:39
 * @version 1.0
 */
public class Request {
    private InputStream inputStream;
    private String uri;

    public Request(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public void parse(){
        //read a set of characters from the socket
        StringBuffer request = new StringBuffer(2048);
        int i;
        byte[] buffer = new byte[2048];
        try {
            i = inputStream.read(buffer);
        }catch (IOException e){
            e.printStackTrace();
            i = -1;
        }

        for (int j = 0; j < i; j++){
            request.append((char) buffer[j]);
        }
        System.out.println(request.toString());
        uri = parseUri(request.toString());
    }

    private String parseUri(String requestStr){
        int index1,index2;
        index1 = requestStr.indexOf(' ');
        if (index1 != -1){
            index2 = requestStr.indexOf(' ',index1 + 1);
            if (index2 > index1){
                return requestStr.substring(index1 + 1, index2);
            }
        }
        return null;
    }

    public String getUri(){
        return uri;
    }
}
