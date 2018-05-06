package ru.dinis.docs.filters;


import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by dinis of 30.04.18.
 */
public class ResponseFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse myResponse = (HttpServletResponse) servletResponse;
        MyResponse response = new MyResponse(myResponse);
        response.addHeader("Access-Control-Allow-Origin", "*");
        filterChain.doFilter(servletRequest, myResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
