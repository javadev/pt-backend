package com.osomapps.pt.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, Authentication authentication)
            throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        final WebAuthenticationDetails webAuthenticationDetails =
                (WebAuthenticationDetails) authentication.getDetails();
        httpServletResponse.getWriter().write("{\"authenticated\": \"true\", \"password\":"
                + " \"***\", \"sessionId\":\"" + webAuthenticationDetails.getSessionId() + "\"}");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    }
}
