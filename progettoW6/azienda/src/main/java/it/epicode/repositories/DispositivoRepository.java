package it.epicode.repositories;

import it.epicode.data.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Long>, PagingAndSortingRepository<Dispositivo, Long> {
}
