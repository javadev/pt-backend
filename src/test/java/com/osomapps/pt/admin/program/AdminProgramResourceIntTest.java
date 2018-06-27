package com.osomapps.pt.admin.program;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@TestPropertySource("/application-test.properties")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AdminProgramResourceIntTest {
    
    @Autowired
    AdminProgramResource adminProgramResource;

    @Test
    public void testList() throws Exception {
        List<ProgramResponseDTO> results = adminProgramResource.findAll();
        assertThat(results.size(), is(greaterThanOrEqualTo(0)));
    }

}
