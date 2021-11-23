package pl.uhu87.toolsborrower.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class LoginController {

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login() {
        return "login";
    }
}
