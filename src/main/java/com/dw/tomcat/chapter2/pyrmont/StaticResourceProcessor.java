package com.dw.tomcat.chapter2.pyrmont;

import java.io.IOException;
/**
 * @author DW
 * @version  1.0
 */
public class StaticResourceProcessor {
    public void process(Request request, Response response){
        try{
            response.sendStaticResource();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
