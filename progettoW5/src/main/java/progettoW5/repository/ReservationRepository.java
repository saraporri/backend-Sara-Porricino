package progettoW5.repository;

import progettoW5.data.Reservation;
import progettoW5.data.User;
import progettoW5.data.Workstation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByUserAndDate (User user, LocalDate date);
    boolean existsByWorkstationAndDate (Workstation workstation, LocalDate date);
}
