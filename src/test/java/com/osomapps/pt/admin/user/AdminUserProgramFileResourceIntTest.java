package com.osomapps.pt.admin.user;

import javax.servlet.http.HttpServletResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.FastByteArrayOutputStream;

@TestPropertySource("/application-test.properties")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AdminUserProgramFileResourceIntTest {

    @MockBean
    AdminUserProgramFileService programFileService;
    @Autowired
    AdminUserProgramFileResource adminUserProgramFileResource;

    @Test
    public void findOne() throws Exception {
        given(programFileService.createXlsx(eq(1L), any(FastByteArrayOutputStream.class))).willReturn(new ProgramResponseDTO());
        HttpServletResponse httpServletResponse = new MockHttpServletResponse();
        adminUserProgramFileResource.findOne(1L, "fileName", httpServletResponse);
        assertThat(httpServletResponse.getStatus(), equalTo(HttpStatus.OK.value()));
    }
}
