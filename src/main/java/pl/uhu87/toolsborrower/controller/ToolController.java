package pl.uhu87.toolsborrower.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.uhu87.toolsborrower.entity.Tool;
import pl.uhu87.toolsborrower.entity.User;
import pl.uhu87.toolsborrower.repository.ToolRepository;
import pl.uhu87.toolsborrower.repository.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/tool")
public class ToolController {

    private final UserRepository userRepository;
    private final ToolRepository toolRepository;

    public ToolController(UserRepository userRepository, ToolRepository toolRepository) {
        this.userRepository = userRepository;
        this.toolRepository = toolRepository;
    }

    @GetMapping("/all")
    public String allTools(Model model){

        model.addAttribute("tools", toolRepository.findAll());
        return "tool/allTools";
    }


    @GetMapping("/user/{id}")
    public String toolsByUserId(Model model, @PathVariable("id") Long id){
        User user = userRepository.getById(id);
        model.addAttribute("tools", toolRepository.findAllByUsers(user));
        return "tool/byUserId";
    }






    @ModelAttribute("users")
    public List<User> users(){
        return userRepository.findAll();
    }


}
