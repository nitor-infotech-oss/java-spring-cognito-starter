package com.nitor.cognitostarter.security;


import com.nitor.cognitostarter.exception.ApplicationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AwsCognitoJwtAuthFilter extends GenericFilter {

    private static final Log logger = LogFactory.getLog(AwsCognitoJwtAuthFilter.class);
    private final AwsCognitoIdTokenProcessor cognitoIdTokenProcessor;

    public AwsCognitoJwtAuthFilter(AwsCognitoIdTokenProcessor cognitoIdTokenProcessor) {
        this.cognitoIdTokenProcessor = cognitoIdTokenProcessor;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication;
        String path = ((HttpServletRequest) request).getRequestURI();
        if (path.contains("swagger") || path.contains("/api-docs") || path.contains("/uapi")) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            authentication = this.cognitoIdTokenProcessor.authenticate((HttpServletRequest) request);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            } else {
                setErrorResponse(HttpStatus.UNAUTHORIZED, (HttpServletResponse) response, "Required Header not present");
            }
        } catch (Exception ex) {
            logger.error("Cognito ID Token processing error", ex);
            SecurityContextHolder.clearContext();
            setErrorResponse(HttpStatus.UNAUTHORIZED, (HttpServletResponse) response, "Token not valid");
        }
    }

    public void setErrorResponse(HttpStatus status, HttpServletResponse response, String message) {
        response.setStatus(status.value());
        response.setContentType("application/json");
        // A class used for errors
        ApplicationException apiError = new ApplicationException(message, 1005, status);
        try {
            String json = apiError.convertToJson();
            System.out.println(json);
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}