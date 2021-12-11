package pl.uhu87.toolsborrower.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.uhu87.toolsborrower.entity.CurrentUser;
import pl.uhu87.toolsborrower.entity.Tool;
import pl.uhu87.toolsborrower.entity.User;
import pl.uhu87.toolsborrower.entity.UserTool;
import pl.uhu87.toolsborrower.repository.ToolRepository;
import pl.uhu87.toolsborrower.repository.UserRepository;
import pl.uhu87.toolsborrower.repository.UserToolRepository;

import javax.validation.Valid;
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

        model.addAttribute("tools", toolRepository.findAllOderByname());
        return "tool/allTools";
    }

    @GetMapping("/toolUsers/{toolId}")

    public String allUsersByTool(Model model, @PathVariable("toolId") Long toolId){


        model.addAttribute("toolUsers", userRepository.uniqueToolUsers(toolId));
        return "tool/toolUsers";
    }

    //__________________________ADD_USERTOOL________________________________________________________________

    @GetMapping("/addUserTool")
    public String addUserTool(Model model) {

        model.addAttribute("userTool", new UserTool());
        return "tool/userToolForm";
    }

    @PostMapping("/addUserTool")
    public String addUserToolPost(@ModelAttribute("userTool") @Valid UserTool userTool,BindingResult result,
                                  @AuthenticationPrincipal CurrentUser currentUser){
        if(result.hasErrors()){
            return "tool/userToolForm";
        }

        userTool.setUser(currentUser.getUser());
        userToolRepository.save(userTool);
        return "redirect:/user/dashboard";
    }

    //__________________________EDIT_________________________________________________________________

    @GetMapping("/editUserTool")
    public String editUserTool(@RequestParam Long idToEdit, Model model){
        model.addAttribute("userTool", userToolRepository.getById(idToEdit));
        return "/tool/userToolForm";
    }

    @PostMapping("/editUserTool")
    public String saveEdit (@ModelAttribute("userTool") UserTool userTool, @AuthenticationPrincipal CurrentUser currentUser){
        userTool.setUser(currentUser.getUser());
        userToolRepository.save(userTool);
        return "redirect:/user/dashboard";
    }

    //__________________________DELETE_________________________________________________________________

    @GetMapping("/delete")
    public String deleteUserTool(@RequestParam Long idToDelete, Model model){
        model.addAttribute("userTool", userToolRepository.getById(idToDelete));
        return "/tool/DeleteConfirmation";
    }

    @PostMapping("/delete")
    public String deleteUserToolPost(@RequestParam String confirmed, @RequestParam Long idToDelete){
        if(confirmed.equals("delete")){
            UserTool userTool = userToolRepository.getById(idToDelete);
            userTool.setPresent(false);
            userToolRepository.save(userTool);
        }
            return "redirect:/user/dashboard";
    }


    //__________________________makeUnavailable_________________________________________________________________

    @GetMapping("/makeUnavailable")
    public String makeUnavailable(@RequestParam Long idToDelete, Model model){
        model.addAttribute("userTool", userToolRepository.getById(idToDelete));
        return "/tool/makeUnavailable";
    }

    @PostMapping("/makeUnavailable")
    public String makeUnavailablePost(@RequestParam String confirmed, @RequestParam Long idToDelete){
        if(confirmed.equals("delete")){
            UserTool userTool = userToolRepository.getById(idToDelete);
            userTool.setPresent(false);
            userToolRepository.save(userTool);
        }
        return "redirect:/user/dashboard";
    }





    @ModelAttribute("tools")
    public List<Tool> tools (){
        return toolRepository.findAllOderByname();
    }


    @ModelAttribute("users")
    public List<User> users(){
        return userRepository.findAll();
    }

}
