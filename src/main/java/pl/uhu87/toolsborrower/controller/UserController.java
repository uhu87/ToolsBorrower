package pl.uhu87.toolsborrower.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.uhu87.toolsborrower.entity.Tool;
import pl.uhu87.toolsborrower.entity.User;
import pl.uhu87.toolsborrower.entity.UserTool;
import pl.uhu87.toolsborrower.repository.ToolRepository;
import pl.uhu87.toolsborrower.repository.UserRepository;
import pl.uhu87.toolsborrower.repository.UserToolRepository;


import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

        private final UserRepository userRepository;
        private final UserToolRepository userToolRepository;
        private final ToolRepository toolRepository;


    public UserController(UserRepository userRepository, UserToolRepository userToolRepository, ToolRepository toolRepository) {
        this.userRepository = userRepository;
        this.userToolRepository = userToolRepository;
        this.toolRepository = toolRepository;
    }

    @GetMapping("/all")
    public String allUsers(Model model){

        model.addAttribute("users", userRepository.findAll());
        return "user/allUsers";
    }

    @GetMapping("/userTools/{userId}")
    public String allToolsByUser(Model model, @PathVariable("userId") Long userId){

        User user = userRepository.getById(userId);
        model.addAttribute("userTools", userToolRepository.findAllByUser(user));
        model.addAttribute("userToolsAvailable", userToolRepository.findAllByUserAndAvailibleTrue(user));
        model.addAttribute("userToolsBorrowed", userToolRepository.findAllByUserAndAvailibleFalse(user));
        return "user/userTools";
    }


    @ModelAttribute("userTools")
    public List<UserTool> userTools(){
        return userToolRepository.findAll();
    }

}
