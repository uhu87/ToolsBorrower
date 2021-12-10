package pl.uhu87.toolsborrower.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.uhu87.toolsborrower.entity.*;
import pl.uhu87.toolsborrower.repository.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/borrowing")
public class BorrowingController {

    private final BorrowingRepository borrowingRepository;
    private final UserToolRepository userToolRepository;
    private final ToolRepository toolRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    public BorrowingController(BorrowingRepository borrowingRepository, UserToolRepository userToolRepository, ToolRepository toolRepository, UserRepository userRepository, ReservationRepository reservationRepository) {
        this.borrowingRepository = borrowingRepository;
        this.userToolRepository = userToolRepository;
        this.toolRepository = toolRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
    }


    @GetMapping("/create")
    public String createBorrowing(Model model, @RequestParam("toolId") Long toolId ){

        model.addAttribute("userTool", userToolRepository.getById(toolId));
        //model.addAttribute("borrowing", new Borrowing());
        return "/borrowing/borrowingForm";

    }

    @PostMapping("/create")

    public String createBorrowingPost(@RequestParam Long toolId, @AuthenticationPrincipal CurrentUser customUser,
                                      @RequestParam String end, Model model){

        updateReservationsStatus(toolId);
        try {
            LocalDate returnDate = reservationRepository.findEarliestActiveReservation(toolId).getStart();


            if (LocalDate.parse(end).isAfter(returnDate)) {
                model.addAttribute("returnDate", returnDate);
                model.addAttribute("userTool", userToolRepository.getById(toolId));
                return "reservation/borrowingOverlap";
            }

            if (LocalDate.parse(end).isBefore(LocalDate.now())) {
                model.addAttribute("returnDate", returnDate);
                model.addAttribute("userTool", userToolRepository.getById(toolId));
                return "borrowing/borrowingPast";
            }
        }catch (NullPointerException e){};


        User entityUser = customUser.getUser();
        Borrowing borrowing = new Borrowing();
        borrowing.setUserTool(userToolRepository.getById(toolId));
        borrowing.setUser(entityUser);
        borrowing.setActive(true);
        UserTool userTool = userToolRepository.getById(borrowing.getUserTool().getId());
        userTool.setAvailable(false);
        borrowing.setEnd(LocalDate.parse(end));
        // save
       borrowingRepository.save(borrowing);

       return "redirect:/user/dashboard";
    }



    @GetMapping("/return")
    public String returnTool(Model model, @RequestParam("toReturnId") Long toReturnId){

        model.addAttribute("borrowing", borrowingRepository.getById(toReturnId));
        return "/borrowing/return";

    }
    @PostMapping("/return")
    public String confirmReturn(@RequestParam Long toReturnId, @RequestParam String comment){

        Borrowing borrowing = borrowingRepository.getById(toReturnId);
        borrowing.setActive(false);
        borrowing.getUserTool().setAvailable(true);
        borrowing.setComment(comment);
        borrowingRepository.save(borrowing);            // UPDATE!!!!! :D
        UserTool userTool = userToolRepository.getById(borrowing.getUserTool().getId());
        userToolRepository.save(userTool);
        Long currentID = borrowing.getUser().getId();
     return "redirect:/user/dashboard/";
    }


    @GetMapping("/history")
    public String borrowingHistory(@RequestParam("toolId") Long toolId, Model model){

        UserTool userTool = userToolRepository.getById(toolId);
        List<Borrowing> borrowings = borrowingRepository.findAllByUserToolIdOrderByEndDesc(toolId);
        model.addAttribute("borrowings", borrowings);
        model.addAttribute("userTool", userTool);
        return "borrowing/history";
    }


    @GetMapping("/createFromReservation/{id}")
    public String createFromBorrowing(@PathVariable("id") Long reservationId, Model model){

        Reservation reservation = reservationRepository.getById(reservationId);
        model.addAttribute("reservation", reservation);
        return "borrowing/createFromReservation";
    }

    @PostMapping("/createFromReservation/{id}")
    public String createFromBorrowingPost(@PathVariable("id") Long reservationId, Model model){

        Reservation reservation = reservationRepository.getById(reservationId);
        UserTool userTool = userToolRepository.getById(reservation.getUserTool().getId());
        Borrowing borrowing = new Borrowing();
        borrowing.setEnd(reservation.getEnd());
        borrowing.setUserTool(reservation.getUserTool());
        borrowing.setUser(reservation.getUser());
        reservation.setActive(false);
        userTool.setAvailable(false);
        borrowingRepository.save(borrowing);
        return "redirect:/user/dashboard";
    }


    public void updateReservationsStatus(Long toolId){
        List<Reservation> allReservations = reservationRepository.findAllByUserToolIdOrderByStart(toolId);
        for(Reservation r : allReservations){
            if (LocalDate.now().isAfter(r.getStart())){
                r.setActive(false);
                reservationRepository.save(r);
            }
        }
    }

}
