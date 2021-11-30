package pl.uhu87.toolsborrower.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.uhu87.toolsborrower.UserService;
import pl.uhu87.toolsborrower.entity.User;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/addUser")
    public String createUser(Model model) {

        model.addAttribute("user", new User());
        return "registration/registration";
    }


    @PostMapping("/addUser")
    public String saveUser(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/login";
    }
}
