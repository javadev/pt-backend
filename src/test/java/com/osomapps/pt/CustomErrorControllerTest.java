package com.osomapps.pt;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.context.request.RequestAttributes;

@ExtendWith(MockitoExtension.class)
public class CustomErrorControllerTest {
    
    @Mock
    private ErrorAttributes errorAttributes;
    
    @InjectMocks
    private CustomErrorController customErrorController;
    
    @Test
    public void error() {
        customErrorController.error(new MockHttpServletRequest(), new MockHttpServletResponse());
        verify(errorAttributes).getErrorAttributes(any(RequestAttributes.class), anyBoolean());
    }

    @Test
    public void getErrorPath() {
        assertThat(customErrorController.getErrorPath(), equalTo("/error"));
    }
}
