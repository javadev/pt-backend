package com.github.pt.home;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @RequestMapping("/")
    public String home() {
        return "Das Boot home route";
    }
}
