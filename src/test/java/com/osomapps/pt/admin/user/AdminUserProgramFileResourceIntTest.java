package com.osomapps.pt.admin.user;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import javax.servlet.http.HttpServletResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FastByteArrayOutputStream;

@TestPropertySource("/application-test.properties")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AdminUserProgramFileResourceIntTest {

    @MockBean AdminUserProgramFileService programFileService;
    @Autowired AdminUserProgramFileResource adminUserProgramFileResource;

    @Test
    public void findOne() throws Exception {
        given(programFileService.createXlsx(eq(1L), any(FastByteArrayOutputStream.class)))
                .willReturn(new ProgramResponseDTO());
        HttpServletResponse httpServletResponse = new MockHttpServletResponse();
        adminUserProgramFileResource.findOne(1L, "fileName", httpServletResponse);
        Assert.assertThat(httpServletResponse.getStatus(), equalTo(HttpStatus.OK.value()));
    }
}
