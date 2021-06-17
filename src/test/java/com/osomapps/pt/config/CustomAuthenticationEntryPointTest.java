package com.osomapps.pt.config;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.AuthenticationException;

@RunWith(MockitoJUnitRunner.class)
public class CustomAuthenticationEntryPointTest {

    @Test
    public void commence() throws IOException, ServletException {
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        when(httpServletResponse.getWriter()).thenReturn(mock(PrintWriter.class));
        new CustomAuthenticationEntryPoint()
                .commence(
                        mock(HttpServletRequest.class),
                        httpServletResponse,
                        mock(AuthenticationException.class));
        verify(httpServletResponse).setStatus(eq(HttpServletResponse.SC_UNAUTHORIZED));
    }

    @Test
    public void commence_null() throws IOException, ServletException {
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        new CustomAuthenticationEntryPoint()
                .commence(mock(HttpServletRequest.class), httpServletResponse, null);
        verify(httpServletResponse, never()).setStatus(eq(HttpServletResponse.SC_UNAUTHORIZED));
    }
}
