package ru.dinis.docs.filters;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Create by dinis of 30.04.18.
 */
public class MyRespons extends HttpServletResponseWrapper {

    public MyRespons(HttpServletResponse response) {
        super(response);
    }

}
