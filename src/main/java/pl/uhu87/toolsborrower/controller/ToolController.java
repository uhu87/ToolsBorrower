package pl.uhu87.toolsborrower.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.uhu87.toolsborrower.entity.User;
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



    @ModelAttribute("users")
    public List<User> users(){
        return userRepository.findAll();
    }

}
