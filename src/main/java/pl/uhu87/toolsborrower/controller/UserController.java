package pl.uhu87.toolsborrower.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.uhu87.toolsborrower.entity.Tool;
import pl.uhu87.toolsborrower.repository.ToolRepository;
import pl.uhu87.toolsborrower.repository.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

        private final UserRepository userRepository;
        private final ToolRepository toolRepository;

    public UserController(UserRepository userRepository, ToolRepository toolRepository) {
        this.userRepository = userRepository;
        this.toolRepository = toolRepository;
    }

    @GetMapping("/all")
    public String allUsers(Model model){

        model.addAttribute("users", userRepository.findAll());
        return "user/allUsers";
    }


    @ModelAttribute("tools")
    public List<Tool> tools(){
        return toolRepository.findAll();
    }


}
