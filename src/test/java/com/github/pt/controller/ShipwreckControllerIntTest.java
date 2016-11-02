package com.github.pt.controller;

import com.github.pt.App;
import com.github.pt.model.Shipwreck;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
@TestPropertySource("/application-test.properties")
public class ShipwreckControllerIntTest {
    
    @Autowired
    ShipwreckController shipwreckController;

    @Test
    public void testList() throws Exception {
        List<Shipwreck> results = shipwreckController.list();
        assertThat(results.size(), is(greaterThanOrEqualTo(0)));
    }

}
