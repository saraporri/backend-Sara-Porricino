package progettoW5.reservationService;

import progettoW5.data.Reservation;
import progettoW5.data.User;
import progettoW5.data.Workstation;
import progettoW5.repository.ReservationRepository;
import progettoW5.repository.UserRepository;
import progettoW5.repository.WorkstationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private WorkstationRepository workstationRepository;

    @Autowired
    private UserRepository userRepository;

    public ReservationRepository createBooking(String username, Long workstationId, LocalDate date){
        User user = userRepository.findById(Integer.valueOf(username))
                .orElseThrow( () -> new RuntimeException("User not found"));
        Workstation workstation = workstationRepository.findById(Math.toIntExact(workstationId))
                .orElseThrow( () -> new RuntimeException("Workstation not found"));

        Reservation reservation = new Reservation();

        return null; }
}
