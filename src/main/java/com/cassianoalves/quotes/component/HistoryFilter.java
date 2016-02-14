package com.cassianoalves.quotes.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class HistoryFilter implements Filter {
//    @Autowired
//    HistoryComponent historyComponent;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init - filterConfig: " + filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("doFilter - servletRequest: " + servletRequest
                + " servletResponse: " + servletResponse
                + " filterChain: " + filterChain
                + " auth: " + authentication);



        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
