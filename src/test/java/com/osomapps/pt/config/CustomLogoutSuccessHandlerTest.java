package com.osomapps.pt.config;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;

@RunWith(MockitoJUnitRunner.class)
public class CustomLogoutSuccessHandlerTest {

    @Test
    public void onLogoutSuccess() throws IOException, ServletException {
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        when(httpServletResponse.getWriter()).thenReturn(mock(PrintWriter.class));
        new CustomLogoutSuccessHandler().onLogoutSuccess(mock(HttpServletRequest.class),
                httpServletResponse, mock(Authentication.class));
        verify(httpServletResponse).setContentType(eq("application/json;charset=UTF-8"));
        verify(httpServletResponse).setStatus(eq(HttpServletResponse.SC_OK));
    }

}
