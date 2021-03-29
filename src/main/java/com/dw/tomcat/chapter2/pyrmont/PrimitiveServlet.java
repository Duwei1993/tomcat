package com.dw.tomcat.chapter2.pyrmont;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author DW
 * @version 1.0
 * @date 2021年3月29日15:37:37
 */
public class PrimitiveServlet implements Servlet{
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("from service");
        PrintWriter out = res.getWriter();
        out.println("Hello. Roses are red");
        out.println("Violets are blue");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
