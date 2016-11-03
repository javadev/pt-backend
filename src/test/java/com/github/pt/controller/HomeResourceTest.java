package com.github.pt.controller;

import com.github.pt.home.HomeResource;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HomeResourceTest {

    @Test
    public void testHome() {
        HomeResource hc = new HomeResource();
        String result = hc.home();
        assertEquals(result, "Das Boot home route");
    }
}