package pl.uhu87.toolsborrower.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.uhu87.toolsborrower.UserService;
import pl.uhu87.toolsborrower.entity.User;
import pl.uhu87.toolsborrower.repository.UserRepository;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final UserRepository userRepository;

    public RegistrationController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/addUser")
    public String createUser(Model model) {

        model.addAttribute("user", new User());
        return "registration/registration";
    }


    @PostMapping("/addUser")
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model){
        if(result.hasErrors()){
            return "registration/registration";
        }
        for(User u :userRepository.findAll()){
            if (u.getUsername().equals(user.getUsername())){
               return "registration/userDuplicate";
            }
        }
        userService.saveUser(user);
        return "redirect:/login";
    }
}
