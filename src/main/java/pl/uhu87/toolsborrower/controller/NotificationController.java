package pl.uhu87.toolsborrower.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.uhu87.toolsborrower.entity.Borrowing;
import pl.uhu87.toolsborrower.entity.Reservation;
import pl.uhu87.toolsborrower.repository.*;

@Controller
@RequestMapping("/notification")
public class NotificationController {

    private final BorrowingRepository borrowingRepository;
    private final UserToolRepository userToolRepository;
    private final ToolRepository toolRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    public NotificationController(BorrowingRepository borrowingRepository, UserToolRepository userToolRepository, ToolRepository toolRepository, UserRepository userRepository, ReservationRepository reservationRepository) {
        this.borrowingRepository = borrowingRepository;
        this.userToolRepository = userToolRepository;
        this.toolRepository = toolRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
    }


    @GetMapping("/return")

    public String sendReturnNotification(@RequestParam Long toReturnId, Model model){

        return "notification/return";
    }

    @PostMapping("/return")

    public String sendReturnNotificationPost(@RequestParam Long toReturnId, @RequestParam String notification){
        Borrowing borrowing = borrowingRepository.getById(toReturnId);
        borrowing.setNotification(notification);
        borrowingRepository.save(borrowing);

        return "redirect:/user/dashboard";

    }


    //-------------------------------------infromation for reservation---------------------------
    @GetMapping("/ownerInfo")

    public String sendInformation(@RequestParam Long reservationId, Model model){

        return "notification/ownerInfo";
    }

    @PostMapping("/ownerInfo")

    public String sendInformationPost(@RequestParam Long reservationId, @RequestParam String ownerInfo){
        Reservation reservation = reservationRepository.getById(reservationId);
        reservation.setOwnerInfo(ownerInfo);
        reservationRepository.save(reservation);

        return "redirect:/user/dashboard";

    }
}
