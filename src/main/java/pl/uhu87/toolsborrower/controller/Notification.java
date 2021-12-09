package pl.uhu87.toolsborrower.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.uhu87.toolsborrower.entity.Borrowing;
import pl.uhu87.toolsborrower.repository.*;

@Controller
@RequestMapping("/notification")
public class Notification {

    private final BorrowingRepository borrowingRepository;
    private final UserToolRepository userToolRepository;
    private final ToolRepository toolRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    public Notification(BorrowingRepository borrowingRepository, UserToolRepository userToolRepository, ToolRepository toolRepository, UserRepository userRepository, ReservationRepository reservationRepository) {
        this.borrowingRepository = borrowingRepository;
        this.userToolRepository = userToolRepository;
        this.toolRepository = toolRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
    }


    @GetMapping("/return")
    @ResponseBody
    public String sendReturnNotification(@RequestParam Long toReturnId, Model model){

        Borrowing borrowing = borrowingRepository.getById(toReturnId);
        model.addAttribute("borrowing", borrowing);
        return "notification/return";
    }

}
