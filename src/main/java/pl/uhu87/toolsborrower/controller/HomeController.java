package pl.uhu87.toolsborrower.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.uhu87.toolsborrower.entity.CurrentUser;
import pl.uhu87.toolsborrower.entity.User;

@Controller
public class HomeController {

    @GetMapping("/")

    public String dashboard(){

        return "redirect:user/dashboard";
    }

    @GetMapping("/hello")

    public String hello(){

        return "hello";
    }
}

