package com.osomapps.pt.reportworkout;

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
public class ReportWorkoutResourceIntTest {
    
    @Autowired
    ReportWorkoutResource reportWorkoutResource;

    @Test
    public void findAll() throws Exception {
        List<WorkoutReportResponseDTO> results = reportWorkoutResource.findAll("");
        assertThat(results.size(), is(greaterThanOrEqualTo(0)));
    }

}
