package pl.uhu87.toolsborrower.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.uhu87.toolsborrower.entity.*;
import pl.uhu87.toolsborrower.repository.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    private final BorrowingRepository borrowingRepository;
    private final UserToolRepository userToolRepository;
    private final ToolRepository toolRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    public ReservationController(BorrowingRepository borrowingRepository, UserToolRepository userToolRepository, ToolRepository toolRepository, UserRepository userRepository, ReservationRepository reservationRepository) {
        this.borrowingRepository = borrowingRepository;
        this.userToolRepository = userToolRepository;
        this.toolRepository = toolRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
    }

    @GetMapping("/make")
    public String makeReservation(Model model, @RequestParam("toolId") Long toolId) {
        model.addAttribute("userTool", userToolRepository.getById(toolId));

        return "/reservation/reservationForm";
    }


    @PostMapping("/make")

    public String makeReservationPost(@RequestParam Long toolId, @AuthenticationPrincipal CurrentUser customUser,
                                      @RequestParam String start, @RequestParam String end){

        User entityUser = customUser.getUser();
        Reservation reservation = new Reservation();
        reservation.setUserTool(userToolRepository.getById(toolId));
        reservation.setUser(entityUser);
        UserTool userTool = userToolRepository.getById(reservation.getUserTool().getId());
        reservation.setStart(LocalDate.parse(start));
        reservation.setEnd(LocalDate.parse(end));
        // save
        reservationRepository.save(reservation);

        return "redirect:/reservation/reservationList?toolId="+toolId;
    }

    @GetMapping("/reservationList")
    public String showToolReservations(Model model, @RequestParam Long toolId) {

        model.addAttribute("userTool", userToolRepository.getById(toolId));
        model.addAttribute("reservations", reservationRepository.findAllByUserToolId(toolId));
        return "reservation/reservationList";
    }
}
