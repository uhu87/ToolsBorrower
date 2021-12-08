package pl.uhu87.toolsborrower.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.uhu87.toolsborrower.entity.*;
import pl.uhu87.toolsborrower.repository.*;

import java.time.LocalDate;
import java.util.List;

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
        LocalDate returnDate = reservationRepository.findEarliestActiveReservation(toolId).getStart();

        if(LocalDate.parse(end).isAfter(returnDate)){
            model.addAttribute("returnDate", returnDate);
            model.addAttribute("userTool", userToolRepository.getById(toolId));
            return "reservation/borrowingOverlap";
        }

        if(LocalDate.parse(end).isBefore(LocalDate.now())){
            model.addAttribute("returnDate", returnDate);
            model.addAttribute("userTool", userToolRepository.getById(toolId));
            return "borrowing/borrowingPast";
        }
      /*  if(LocalDate.parse(end).isEqual(reservationRepository.findEarliestActiveReservation(toolId).getStart())){
            String returnDate = reservationRepository.findEarliestActiveReservation(toolId).getStart().toString();
            return "redirect:/borrowing/borrowingOverlapSameDay?returnDate="+returnDate+"toolId";
        }*/

        //return "mozna brac";

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
    public String confirmReturn(@RequestParam Long toReturnId){

        Borrowing borrowing = borrowingRepository.getById(toReturnId);
        borrowing.setActive(false);
        borrowing.getUserTool().setAvailable(true);
        borrowingRepository.save(borrowing);            // UPDATE!!!!! :D
        UserTool userTool = userToolRepository.getById(borrowing.getUserTool().getId());
        userToolRepository.save(userTool);
        Long currentID = borrowing.getUser().getId();
     return "redirect:/user/dashboard/";
    }


  /*  @GetMapping("/borrowingOverlap")

    public  String  borrowingOverlap(@RequestParam("returnDate") String returnDate, Model model){
        model.addAttribute("returnDate", returnDate);

      return "reservation/borrowingOverlap";
    }*/


    @GetMapping("/history")
    public String borrowingHistory(@RequestParam("toolId") Long toolId, Model model){

        List<Borrowing> allBorrowings = borrowingRepository.findAllByUserToolId(toolId);
        model.addAttribute("allBorrowings", allBorrowings);
        return "borrowing/history";
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
