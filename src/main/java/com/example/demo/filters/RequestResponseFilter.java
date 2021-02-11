package com.example.demo.filters;

import com.example.demo.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.demo.controller.UserController.userCreateForm;

@Component
public class RequestResponseFilter implements Filter {
    private final static Logger LOG = LoggerFactory.getLogger(RequestResponseFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        if(req.getRequestURI().equals("/register") && userCreateForm!=null) {
            String email = userCreateForm.getEmail();
            if (!email.contains("@astanait.edu.kz")) {
                LOG.info("email is incorrect");
            } else {
                LOG.info("successful!");
            }
        }

        LOG.info("Logging Request  {} : {}", req.getMethod(), req.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        LOG.info("Initializing filter :{}", this);
    }

    @Override
    public void destroy() {
        LOG.warn("Destructing filter :{}", this);
    }


}
