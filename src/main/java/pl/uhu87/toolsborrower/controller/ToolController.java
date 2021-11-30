package pl.uhu87.toolsborrower.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.uhu87.toolsborrower.entity.CurrentUser;
import pl.uhu87.toolsborrower.entity.Tool;
import pl.uhu87.toolsborrower.entity.User;
import pl.uhu87.toolsborrower.entity.UserTool;
import pl.uhu87.toolsborrower.repository.ToolRepository;
import pl.uhu87.toolsborrower.repository.UserRepository;
import pl.uhu87.toolsborrower.repository.UserToolRepository;


import java.util.List;

@Controller
@RequestMapping("/tool")
public class ToolController {

    private final UserRepository userRepository;
    private final ToolRepository toolRepository;
    private final UserToolRepository userToolRepository;

    public ToolController(UserRepository userRepository, ToolRepository toolRepository, UserToolRepository userToolRepository) {
        this.userRepository = userRepository;
        this.toolRepository = toolRepository;
        this.userToolRepository = userToolRepository;
    }

    @GetMapping("/all")
    public String allTools(Model model){

        model.addAttribute("tools", toolRepository.findAll());
        return "tool/allTools";
    }

    @GetMapping("/toolUsers/{toolId}")
    public String allUsersByTool(Model model, @PathVariable("toolId") Long tooLId){

        model.addAttribute("userTools", userToolRepository.findAllByToolId(tooLId));
        return "tool/toolUsers";
    }


    @GetMapping("/addUserTool")
    public String addUserTool(Model model) {

        model.addAttribute("userTool", new UserTool());
        return "tool/userToolForm";
    }

    @PostMapping("/addUserTool")
    public String addUserToolPost(@ModelAttribute("userTool") UserTool userTool, @AuthenticationPrincipal CurrentUser currentUser){

        userTool.setUser(currentUser.getUser());
        userToolRepository.save(userTool);
        return "redirect:/user/dashboard";
    }





    @ModelAttribute("tools")
    public List<Tool> tools (){
        return toolRepository.findAll();
    }





    @ModelAttribute("users")
    public List<User> users(){
        return userRepository.findAll();
    }

}
