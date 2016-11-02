package com.github.pt.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HomeControllerTest {

    @Test
    public void testHome() {
        HomeController hc = new HomeController();
        String result = hc.home();
        assertEquals(result, "Das Boot home route");
    }
}