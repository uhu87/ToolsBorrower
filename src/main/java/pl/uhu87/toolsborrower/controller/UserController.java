package pl.uhu87.toolsborrower.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.uhu87.toolsborrower.UserService;
import pl.uhu87.toolsborrower.entity.*;
import pl.uhu87.toolsborrower.repository.*;


import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserToolRepository userToolRepository;
    private final ToolRepository toolRepository;
    private final BorrowingRepository borrowingRepository;
    private final UserService userService;
    private final ReservationRepository reservationRepository;

    public UserController(UserRepository userRepository, UserToolRepository userToolRepository, ToolRepository toolRepository, BorrowingRepository borrowingRepository, UserService userService, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.userToolRepository = userToolRepository;
        this.toolRepository = toolRepository;
        this.borrowingRepository = borrowingRepository;
        this.userService = userService;
        this.reservationRepository = reservationRepository;
    }

   /* @GetMapping("/all")
    public String allUsers(Model model, @AuthenticationPrincipal CurrentUser currentUser) {

        model.addAttribute("users", userRepository.findAll());
        return "user/allUsers";
    }*/

    @GetMapping("/allButLogged")
    public String allUsersButLogged(Model model, @AuthenticationPrincipal CurrentUser currentUser) {

        model.addAttribute("users", userRepository.findAllActiveButLogged(currentUser.getUser().getId()));
        return "user/allUsersButLogged";
    }

    @GetMapping("/userTools/{userId}")
    public String allToolsByUser(Model model, @PathVariable("userId") Long userId) {


        User user = userRepository.getById(userId);
        model.addAttribute("user", user);
        model.addAttribute("userTools", userToolRepository.findAllByUserAndPresentTrue(user));
        model.addAttribute("userToolsAvailable", userToolRepository.findAllByUserAndAvailableTrueAndPresentTrue(user));
        model.addAttribute("userToolsLent", userToolRepository.findAllByUserAndAvailableFalseAndPresentTrue(user));
        List<Borrowing> borrowings = borrowingRepository.findAllByUserIdAndActiveTrue(userId);        // miejsce na streama
        model.addAttribute("borrowings", borrowings);

        return "user/userTools";
    }


    @ModelAttribute("userTools")
    public List<UserTool> userTools() {
        return userToolRepository.findAll();
    }


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
        model.addAttribute("userTools", userToolRepository.findAllByUserAndPresentTrue(user));
        model.addAttribute("userToolsAvailable", userToolRepository.findAllByUserAndAvailableTrueAndPresentTrue(user));


        List<Reservation> myReservations = reservationRepository.findAllActiveAndToolsPresent(customUser.getUser().getId());
        //List <Reservation> updatedReservations = new ArrayList<>();
        for (Reservation r : myReservations) {
            if (LocalDate.now().isAfter(r.getStart())) {
                r.setActive(false);
                reservationRepository.save(r);
            } if(LocalDate.now().isEqual(r.getStart())){
                r.setNotification("Masz dziś rezerwację");
                r.setReservationDayOn(true);
                reservationRepository.save(r);
            }
            //updatedReservations.add(r);
        }
        List<Reservation> updatedReservations = reservationRepository.findAllActiveAndToolsPresent(customUser.getUser().getId());
        model.addAttribute("reservations", updatedReservations);

        List<Reservation> deletedToolsReservations = reservationRepository.findReservationsWithToolsNotPresent(entityUser.getId());
        model.addAttribute("deletedToolsReservations", deletedToolsReservations);

        List<Borrowing> borrowings = borrowingRepository.findAllByUserIdAndActiveTrue(entityUser.getId());        // miejsce na streama
        model.addAttribute("borrowings", borrowings);

        List<Borrowing> lendings = borrowingRepository.findAllLentbyLenderId(entityUser.getId());
        model.addAttribute("lendings", lendings);


        return "user/dashboard";

    }

    @GetMapping("/edit")
    public String editData(@AuthenticationPrincipal CurrentUser customUser, Model model){
        User entityUser = customUser.getUser();
        User user = userRepository.getById(entityUser.getId());
        model.addAttribute("user", user);

        return "user/edit";
    }
    @PostMapping("/edit")
    public String editDataPost(@ModelAttribute("user") @Valid User user, BindingResult result){
        if(result.hasErrors()){
            return "registration/registration";
        }
        userService.saveUser(user);

        return "user/userTools";
    }


    @GetMapping("/userTools")
    public String enterUserToolsFromDifferentView(@RequestParam("username") String username, Model model){

        Long userId = userRepository.findByUsername(username).getId();
        User user = userRepository.getById(userId);
        return "redirect:/user/userTools/"+userId;
    }



    @ModelAttribute("currentUser")
    public String userInfo(@AuthenticationPrincipal UserDetails customUser) {

        if (customUser == null) {
            return "Widok dostepny dla wszystkich";
        }
        // log.info("customUser class {} " , customUser.getClass());
        return customUser.getUsername();
    }


/*    public List<Reservation> updateMyReservations(List<Reservation> reservations) {
         List <Reservation> updatedReservations = new ArrayList<>();
        for (Reservation r : reservations) {
            if (LocalDate.now().isAfter(r.getStart())) {
                r.setActive(false);
                reservationRepository.save(r);
            } updatedReservations.add(r);

        }return updatedReservations;

    }*/
}