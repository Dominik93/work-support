package com.slusarz.worksupport.web.host;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Init {

    @GetMapping("/")
    public String init() {
        return "index.html";
    }

}
