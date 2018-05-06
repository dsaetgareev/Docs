package ru.dinis.docs.filters;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Create by dinis of 30.04.18.
 */
public class MyResponse extends HttpServletResponseWrapper {

    public MyResponse(HttpServletResponse response) {
        super(response);
    }

}
