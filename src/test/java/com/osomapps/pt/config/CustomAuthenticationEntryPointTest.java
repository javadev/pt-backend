package com.osomapps.pt.config;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.AuthenticationException;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CustomAuthenticationEntryPointTest {

    @Test
    public void commence() throws IOException, ServletException {
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        when(httpServletResponse.getWriter()).thenReturn(mock(PrintWriter.class));
        new CustomAuthenticationEntryPoint().commence(mock(HttpServletRequest.class),
                httpServletResponse, mock(AuthenticationException.class));
        verify(httpServletResponse).setStatus(eq(HttpServletResponse.SC_UNAUTHORIZED));
    }

    @Test
    public void commence_null() throws IOException, ServletException {
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        when(httpServletResponse.getWriter()).thenReturn(mock(PrintWriter.class));
        new CustomAuthenticationEntryPoint().commence(mock(HttpServletRequest.class),
                httpServletResponse, null);
        verify(httpServletResponse, never()).setStatus(eq(HttpServletResponse.SC_UNAUTHORIZED));
    }
}
