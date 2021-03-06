package com.osomapps.pt.admin.exercise;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@TestPropertySource("/application-test.properties")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class AdminExerciseResourceIntTest {

    @Autowired AdminExerciseResource adminExerciseResource;

    @Test
    public void testList() throws Exception {
        List<ExerciseResponseDTO> results = adminExerciseResource.findAll();
        assertThat(results.size(), is(greaterThanOrEqualTo(0)));
    }
}
