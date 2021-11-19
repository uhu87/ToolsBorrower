package pl.uhu87.toolsborrower.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class home {



    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
