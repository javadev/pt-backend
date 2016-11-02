package com.github.pt.controller;

import com.github.pt.model.Shipwreck;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ShipwreckControllerIntTest {
    
    @Autowired
    ShipwreckController shipwreckController;

    @Test
    public void testList() throws Exception {
        List<Shipwreck> results = shipwreckController.list();
        assertThat(results.size(), is(greaterThanOrEqualTo(0)));
    }

}
