package pl.uhu87.toolsborrower.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.uhu87.toolsborrower.entity.*;
import pl.uhu87.toolsborrower.repository.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
                                      @RequestParam String start, @RequestParam String end, Model model) {

        updateReservationsStatus(toolId);
        List<Reservation> allReservationsActive = reservationRepository.findAllByUserToolIdAndActiveTrueOrderByStart(toolId);
        if (start.isBlank() || end.isBlank()) {
            model.addAttribute("userTool", userToolRepository.getById(toolId));
            return "reservation/blankData";
        }

        User entityUser = customUser.getUser();
        Reservation reservation = new Reservation();
        reservation.setUserTool(userToolRepository.getById(toolId));
        reservation.setUser(entityUser);
        UserTool userTool = userToolRepository.getById(reservation.getUserTool().getId());
        reservation.setStart(LocalDate.parse(start));
        reservation.setEnd(LocalDate.parse(end));
        for (Reservation r : allReservationsActive) {
            if (isOverlapping(LocalDate.parse(start), LocalDate.parse(end), r.getStart(), r.getEnd())) {
                model.addAttribute("overlappingReservation", r);
                model.addAttribute("userTool", userToolRepository.getById(toolId));
                return "reservation/reservationOverlap";
            }
        }
        // ______________DLACZEGO NIE ZABIERA NA WIDOK ?________________
        if (LocalDate.parse(start).isAfter(LocalDate.parse(end))) {
            model.addAttribute("userTool", userToolRepository.getById(toolId));
            return "reservation/startenderror";
        }

        // ______________DLACZEGO NIE ZABIERA NA WIDOK ?________________

        if (LocalDate.parse(start).isBefore(LocalDate.now())) {
            model.addAttribute("userTool", userToolRepository.getById(toolId));
            return "reservation/paststarterror";
        }


        try {
            LocalDate returnDate = borrowingRepository.findFirstByUserToolIdAndActiveTrue(toolId).getEnd();
            if (returnDate.isAfter(LocalDate.parse(start)) || returnDate.isEqual(LocalDate.parse(start))) {
                model.addAttribute("userTool", userToolRepository.getById(toolId));
                model.addAttribute("returnDate", returnDate);
                return "reservation/borrowingOverlap";
            }
        } catch (NullPointerException e) {
        }
        ;

        reservationRepository.save(reservation);

        return "redirect:/user/dashboard";
    }

    @GetMapping("/reservationList")
    public String showAllReservations(Model model, @RequestParam Long toolId) {

        updateReservationsStatus(toolId);

        List<Borrowing> activeBorrowings = borrowingRepository.findAllByUserToolIdAndActiveTrue(toolId);
        List<Reservation> allReservationsActive = reservationRepository.findAllByUserToolIdAndActiveTrueOrderByStart(toolId);
        model.addAttribute("userTool", userToolRepository.getById(toolId));
        model.addAttribute("reservations", allReservationsActive);
        model.addAttribute("borrowing", activeBorrowings);
        return "reservation/reservationList";
    }

    @GetMapping("/userReservationList")
    public String showAllUserReservations(Model model, @RequestParam Long toolId) {

        updateReservationsStatus(toolId);

        List<Borrowing> activeBorrowings = borrowingRepository.findAllByUserToolIdAndActiveTrue(toolId);
        List<Reservation> allReservationsActive = reservationRepository.findAllByUserToolIdAndActiveTrueOrderByStart(toolId);
        model.addAttribute("userTool", userToolRepository.getById(toolId));
        model.addAttribute("reservations", allReservationsActive);
        model.addAttribute("borrowing", activeBorrowings);
        return "reservation/userReservationList";
    }


    public static boolean isOverlapping(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
        return !start1.isAfter(end2) && !start2.isAfter(end1);
    }

    public void updateReservationsStatus(Long toolId) {
        List<Reservation> allReservations = reservationRepository.findAllByUserToolIdOrderByStart(toolId);
        for (Reservation r : allReservations) {
            if (LocalDate.now().isAfter(r.getStart())) {
                r.setActive(false);
                reservationRepository.save(r);
            }
        }
    }


    @GetMapping("/myReservations")
    public String showMyReservations(Model model, @AuthenticationPrincipal CurrentUser customUser) {


        List<Reservation> myReservations = reservationRepository.findAllByUserAndActiveTrueOrderByStartAsc(customUser.getUser());

        model.addAttribute("reservations", myReservations);
        return "reservation/myReservations";
    }


    @GetMapping("/cancel")
    public String cancelReservation(Model model, @RequestParam("reservationId") Long reservationId) {

        Reservation reservation = reservationRepository.getById(reservationId);
        model.addAttribute("reservation", reservation);
        return "reservation/cancel";
    }

    @PostMapping("/cancel")
    public String cancelReservationPost(@RequestParam String confirmed, @RequestParam Long reservationId) {
        Reservation reservation = reservationRepository.getById(reservationId);
        if (confirmed.equals("yes")) {
            reservation.setActive(false);
            reservationRepository.save(reservation);
            return "redirect:/user/dashboard";
        }
        return "redirect:/user/dashboard";
    }
}
