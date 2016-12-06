package com.github.pt.admin.program;

import com.github.pt.ResourceNotFoundException;
import com.github.pt.programs.ProgramRepository;
import java.io.ByteArrayInputStream;
import javax.servlet.http.HttpServletResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@TestPropertySource("/application-test.properties")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AdminProgramFileResourceIntTest {

    @MockBean
    AdminProgramService programService;
    @Autowired
    AdminProgramFileResource adminProgramFileResource;

    @Test
    public void findOne() throws Exception {
        given(this.programService.findOne(1L)).willReturn(new ProgramResponseDTO());
        given(this.programService.dataUrlToInputStream(anyString())).willReturn(new ByteArrayInputStream(new byte[]{}));
        HttpServletResponse httpServletResponse = new MockHttpServletResponse();
        adminProgramFileResource.findOne(1L, "fileName", httpServletResponse);
        Assert.assertThat(httpServletResponse.getStatus(), equalTo(HttpStatus.OK.value()));
    }
}
