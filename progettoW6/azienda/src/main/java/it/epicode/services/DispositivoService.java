package it.epicode.services;

import it.epicode.data.Dispositivo;

import java.util.List;
import java.util.Optional;

public interface DispositivoService {

    List<Dispositivo> getDispositivi();

    Optional<Dispositivo> getDispositivo(Long id);

    Dispositivo save(Dispositivo dispositivo);

    Optional<Dispositivo> update(Long dispositivoId, Dispositivo dispositivo);

    Optional<Dispositivo> delete(Long dispositivoId);

    Dispositivo assegna(Long dispositivoId,Long dipendenteId);

    Dispositivo rimuoviDispositivoAssegnato(Long dispositivoId, Long dipendenteId);


}
