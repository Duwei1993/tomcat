package com.dw.tomcat.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author DW
 * @version  1.0
 */
public class BufferUtil {

    /**
     * read InputStream and convert to String
     * @param inputStream
     * @return  java.lang.String
     *
     */
    public static String readInputStream(InputStream inputStream){
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
        return request.toString();
    }
}
