package pl.uhu87.toolsborrower.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.uhu87.toolsborrower.entity.Borrowing;
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
    public String createBorrowing(Model model){


        model.addAttribute("borrowing", new Borrowing());
        return "/borrowing/borrowingForm";

    }

    @PostMapping("/create")
    public String createBorrowingPost(@ModelAttribute("borrowing") Borrowing borrowing){

        // musi byc przed SAVE BORROWING, why?
        UserTool userTool = userToolRepository.getById(borrowing.getUserTool().getId());
        userTool.setAvailible(false);

        // save
        borrowingRepository.save(borrowing);

        return "redirect:/user/all";
    }





}
