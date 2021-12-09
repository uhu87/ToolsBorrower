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
    @ResponseBody
    public String makeReservationPost(@RequestParam Long toolId, @AuthenticationPrincipal CurrentUser customUser,
                                      @RequestParam String start, @RequestParam String end){

        updateReservationsStatus(toolId);
        List<Reservation> allReservationsActive = reservationRepository.findAllByUserToolIdAndActiveTrueOrderByStart(toolId);


        User entityUser = customUser.getUser();
        Reservation reservation = new Reservation();
        reservation.setUserTool(userToolRepository.getById(toolId));
        reservation.setUser(entityUser);
        UserTool userTool = userToolRepository.getById(reservation.getUserTool().getId());
        reservation.setStart(LocalDate.parse(start));
        reservation.setEnd(LocalDate.parse(end));
        for(Reservation r : allReservationsActive){
            if (isOverlapping(LocalDate.parse(start), LocalDate.parse(end), r.getStart(), r.getEnd())){
                return "overlpas";
            }
            if(LocalDate.parse(start).isAfter(LocalDate.parse(end))){
                return "startDate nie moze byc po endDate";
            }
            if(LocalDate.parse(start).isBefore(LocalDate.now())){
                return "nie mozna rezerwowac w przeszlosci :D";
            }

        }
        try {
            LocalDate returnDate = borrowingRepository.findFirstByUserToolIdAndActiveTrue(toolId).getEnd();
            if(returnDate.isAfter(LocalDate.parse(start)) | returnDate.isEqual(LocalDate.parse(start)) ){
                return "nardzenia chwilowo pozyczone, zostanie oddane "+ returnDate.toString() + "wybierz inna date rezerwacji";
            }
            if(returnDate.isEqual(LocalDate.parse(start))| returnDate.isEqual(LocalDate.parse(start)) ){
                return "Dnia"+ returnDate.toString() +" inny uzytkownik oddaje narzedzie, skontaktuje sie z wlasciwielem zeby ustalic szczegoly";
            }
        } catch (NullPointerException e){};

        reservationRepository.save(reservation);

        return "redirect:/reservation/reservationList?toolId="+toolId;
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


    public static boolean isOverlapping(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {

        return !start1.isAfter(end2) && !start2.isAfter(end1);

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


    @GetMapping("/myReservations")
    public String showMyReservations(Model model, @AuthenticationPrincipal CurrentUser customUser) {


        List<Reservation> myReservations = reservationRepository.findAllByUserAndActiveTrue(customUser.getUser());

        model.addAttribute("reservations", myReservations);
        return "reservation/myReservations";
    }


    @GetMapping("/cancel")
    public String cancelReservation(Model model, @RequestParam("reservationId") Long reservationId){

        Reservation reservation = reservationRepository.getById(reservationId);
        model.addAttribute("reservation", reservation);
        return "reservation/cancel";
    }

    @PostMapping("/cancel")
    public String cancelReservationPost(@RequestParam String confirmed, @RequestParam Long reservationId){
        Reservation reservation = reservationRepository.getById(reservationId);
        if(confirmed.equals("yes")){
            reservation.setActive(false);
            reservationRepository.save(reservation);
            return "redirect:/reservation/myReservations";
        }
        return "redirect:/reservation/myReservations";
    }
}