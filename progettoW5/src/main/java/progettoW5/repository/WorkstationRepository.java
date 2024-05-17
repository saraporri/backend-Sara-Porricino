package progettoW5.repository;

import progettoW5.data.Building;
import progettoW5.data.Workstation;
import progettoW5.data.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkstationRepository extends JpaRepository<Workstation, Integer> {
    List<WorkstationRepository> findByWorkstationTypeAndBuilding (Type type, Building building);
}
