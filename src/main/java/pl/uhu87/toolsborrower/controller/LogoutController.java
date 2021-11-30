package pl.uhu87.toolsborrower.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogoutController {

    @GetMapping("/logout1")
    public String logout(){
        return "logout";
    }
}