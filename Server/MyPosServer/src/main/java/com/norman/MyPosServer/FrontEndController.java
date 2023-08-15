package com.norman.MyPosServer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontEndController {
    @RequestMapping(path={"items", "items/", "users", "users/"})
    public String toItems() {
        return "forward:/index.html";
    }
}
