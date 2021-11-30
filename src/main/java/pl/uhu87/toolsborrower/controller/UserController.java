package pl.uhu87.toolsborrower.controller;


import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.uhu87.toolsborrower.UserService;
import pl.uhu87.toolsborrower.entity.*;
import pl.uhu87.toolsborrower.repository.BorrowingRepository;
import pl.uhu87.toolsborrower.repository.ToolRepository;
import pl.uhu87.toolsborrower.repository.UserRepository;
import pl.uhu87.toolsborrower.repository.UserToolRepository;


import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

        private final UserRepository userRepository;
        private final UserToolRepository userToolRepository;
        private final ToolRepository toolRepository;
        private final BorrowingRepository borrowingRepository;
        private final UserService userService;

    public UserController(UserRepository userRepository, UserToolRepository userToolRepository, ToolRepository toolRepository, BorrowingRepository borrowingRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userToolRepository = userToolRepository;
        this.toolRepository = toolRepository;
        this.borrowingRepository = borrowingRepository;
        this.userService = userService;
    }

    @GetMapping("/all")
    public String allUsers(Model model, @AuthenticationPrincipal CurrentUser currentUser){

        model.addAttribute("users", userRepository.findAll());
        return "user/allUsers";
    }
    @GetMapping("/allButLogged")
    public String allUsersButLogged(Model model, @AuthenticationPrincipal CurrentUser currentUser){

        model.addAttribute("users", userRepository.findAllbutLogged(currentUser.getUser().getId()));
        return "user/allUsersButLogged";
    }

    @GetMapping("/userTools/{userId}")
    public String allToolsByUser(Model model, @PathVariable("userId") Long userId){


        User user = userRepository.getById(userId);
        model.addAttribute("user", user);
        model.addAttribute("userTools", userToolRepository.findAllByUser(user));
        model.addAttribute("userToolsAvailable", userToolRepository.findAllByUserAndAvailibleTrue(user));
        model.addAttribute("userToolsLent", userToolRepository.findAllByUserAndAvailibleFalse(user));
        List<Borrowing> borrowings = borrowingRepository.findAllByUserIdAndActiveTrue(userId);        // miejsce na streama
        model.addAttribute("borrowings", borrowings);

        return "user/userTools";
    }


    @ModelAttribute("userTools")
    public List<UserTool> userTools(){
        return userToolRepository.findAll();
    }

  /*  @GetMapping("/user")
    @ResponseBody
    public String admin(@AuthenticationPrincipal CurrentUser customUser) {
        User entityUser = customUser.getUser();
        return "Hello " + entityUser.getUsername() + "\n" +
                entityUser.getFirstName() + " " + entityUser.getLastName() + entityUser.getId();
    }*/


    @GetMapping("/create-user")
    @ResponseBody
    public String createUser() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("user");
        userService.saveUser(user);
        return "user";
    }


    @GetMapping("/dashboard")
    public String dashboard(Model model, @AuthenticationPrincipal CurrentUser customUser) {
        User entityUser = customUser.getUser();
        User user = userRepository.getById(entityUser.getId());
        model.addAttribute("user", user);
        model.addAttribute("userTools", userToolRepository.findAllByUser(user));
        model.addAttribute("userToolsAvailable", userToolRepository.findAllByUserAndAvailibleTrue(user));

        // ok, ale zeby pokazac komu, musimy skorzystac z borrowingRepo
        //model.addAttribute("userToolsLent", userToolRepository.findAllByUserAndAvailibleFalse(user));

        List<Borrowing> borrowings = borrowingRepository.findAllByUserIdAndActiveTrue(entityUser.getId());        // miejsce na streama
        model.addAttribute("borrowings", borrowings);

        List<Borrowing> lendings = borrowingRepository.findAllLentbyLenderId(entityUser.getId());
        model.addAttribute("lendings", lendings);

        return "user/dashboard";

    }


    @ModelAttribute("currentUser")
    public String userInfo(@AuthenticationPrincipal UserDetails customUser) {

        if (customUser == null){
            return "Widok dostepny dla wszystkich";
        }
        // log.info("customUser class {} " , customUser.getClass());
        return customUser.getUsername();
    }

}
