package com.osomapps.pt.admin.ptuser;

import com.osomapps.pt.admin.program.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@TestPropertySource("/application-test.properties")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AdminPtUserResourceIntTest {
    
    @Autowired
    AdminPtUserResource adminPtUserResource;

    @Test
    public void testList() throws Exception {
        List<PtUserResponseDTO> results = adminPtUserResource.findAll();
        assertThat(results.size(), is(greaterThanOrEqualTo(0)));
    }

}
