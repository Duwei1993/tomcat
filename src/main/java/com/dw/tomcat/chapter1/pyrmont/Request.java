package com.dw.tomcat.chapter1.pyrmont;

import com.dw.tomcat.utils.BufferUtil;

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
        uri = parseUri(BufferUtil.readInputStream(inputStream));
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
