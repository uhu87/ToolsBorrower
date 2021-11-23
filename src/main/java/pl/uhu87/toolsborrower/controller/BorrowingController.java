package pl.uhu87.toolsborrower.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.uhu87.toolsborrower.entity.Borrowing;
import pl.uhu87.toolsborrower.entity.CurrentUser;
import pl.uhu87.toolsborrower.entity.User;
import pl.uhu87.toolsborrower.entity.UserTool;
import pl.uhu87.toolsborrower.repository.BorrowingRepository;
import pl.uhu87.toolsborrower.repository.ToolRepository;
import pl.uhu87.toolsborrower.repository.UserRepository;
import pl.uhu87.toolsborrower.repository.UserToolRepository;

@Controller
@RequestMapping("/borrowing")
public class BorrowingController {

    private final BorrowingRepository borrowingRepository;
    private final UserToolRepository userToolRepository;
    private final ToolRepository toolRepository;
    private final UserRepository userRepository;

    public BorrowingController(BorrowingRepository borrowingRepository, UserToolRepository userToolRepository, ToolRepository toolRepository, UserRepository userRepository) {
        this.borrowingRepository = borrowingRepository;
        this.userToolRepository = userToolRepository;
        this.toolRepository = toolRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("/create")
    public String createBorrowing(Model model, @RequestParam("toolId") Long toolId ){

        model.addAttribute("userTool", userToolRepository.getById(toolId));
        //model.addAttribute("borrowing", new Borrowing());
        return "/borrowing/borrowingForm";

    }

    @PostMapping("/create")
    public String createBorrowingPost(@RequestParam Long toolId, @AuthenticationPrincipal CurrentUser customUser){

        // musi byc przed SAVE BORROWING, why?

        User entityUser = customUser.getUser();
        Borrowing borrowing = new Borrowing();
        borrowing.setUserTool(userToolRepository.getById(toolId));
        borrowing.setUser(entityUser);
        borrowing.setActive(true);
        UserTool userTool = userToolRepository.getById(borrowing.getUserTool().getId());
        userTool.setAvailible(false);
        // save
       borrowingRepository.save(borrowing);

        return "redirect:/user/all";
    }



    @GetMapping("/return")
    public String returnTool(Model model, @RequestParam("toReturnId") Long toReturnId){

        model.addAttribute("borrowing", borrowingRepository.getById(toReturnId));
        return "/borrowing/return";

    }
    @PostMapping("/return")
    public String confirmReturn(@RequestParam Long toReturnId){

        Borrowing borrowing = borrowingRepository.getById(toReturnId);
        borrowing.setActive(false);
        borrowing.getUserTool().setAvailible(true);
        borrowingRepository.save(borrowing);            // UPDATE!!!!! :D
        UserTool userTool = userToolRepository.getById(borrowing.getUserTool().getId());
        userToolRepository.save(userTool);
        Long currentID = borrowing.getUser().getId();
     return "redirect:/user/userTools/"+currentID;
    }

}
