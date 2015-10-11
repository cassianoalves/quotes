package com.cassianoalves.quotes.component;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        response.setHeader("Access-Control-Allow-Origin", handleHttpResponseSplitting(request.getHeader("Origin")));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        if (!"OPTIONS".equals(request.getMethod())) {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {

    }

    private String handleHttpResponseSplitting(String str) {
        String result;
        if (str == null) {
            result = "";
        } else {
            //Tratamento para evitar HTTP Response Splitting; ver https://www.owasp.org/index.php/HTTP_Response_Splitting
            int idxCRLF = str.indexOf("\r\n"); //CRLF
            result = ((idxCRLF >= 0) ? str.substring(0, idxCRLF) : str);
        }
        return result;
    }

}
