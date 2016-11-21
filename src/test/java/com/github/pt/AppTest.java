package com.github.pt;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AppTest {
    static {
        System.setProperty("spring.config.location", "classpath:/application-test.properties");
    }

    @Test
    public void main() {
        App.main(new String[] {""});
    }
}
