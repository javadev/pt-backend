package com.osomapps.pt.config;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Test;
import org.springframework.security.core.AuthenticationException;

public class CustomLoginFailureHandlerTest {

    @Test
    public void onAuthenticationSuccess() throws IOException, ServletException {
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        when(httpServletResponse.getWriter()).thenReturn(mock(PrintWriter.class));
        new CustomLoginFailureHandler()
                .onAuthenticationFailure(
                        mock(HttpServletRequest.class),
                        httpServletResponse,
                        mock(AuthenticationException.class));
        verify(httpServletResponse).setContentType(eq("application/json;charset=UTF-8"));
        verify(httpServletResponse).setStatus(eq(HttpServletResponse.SC_UNAUTHORIZED));
    }
}
