package it.epicode.services;

import it.epicode.data.Dispositivo;
import it.epicode.data.StatoDispositivo;
import it.epicode.repositories.DipendenteRepository;
import it.epicode.repositories.DispositivoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@Slf4j
public class DispositivoServiceImpl implements DispositivoService{


    @Autowired
    DispositivoRepository dispositivi;

    @Autowired
    DipendenteRepository dipendenti;


    @Override
    public List<Dispositivo> getDispositivi() {
            return dispositivi.findAll();
    }

    @Override
    public Optional<Dispositivo> getDispositivo(Long id) {
        try {
            return dispositivi.findById(id);
        }catch (Exception ex){
            log.error(String.format("dispotivo con id = non trovato",id),ex);
            return Optional.empty();
        }
    }

    @Override
    public Dispositivo save(Dispositivo dispositivo) {
        try {
            return dispositivi.save(dispositivo);
        }catch (Exception ex){
            log.error(String.format("salvataggio non riuscito di",dispositivo),ex);
            return null;
        }
    }

    @Override
    public Optional<Dispositivo> update(Long dispositivoId, Dispositivo dispositivo) {
        try {
            var dis = dispositivi.findById(dispositivoId).orElseThrow();
            dis.builder()
                    .withDipendente(dis.getDipendente())
                    .withStatoDispositivo(dis.getStatoDispositivo())
                    .withTipoDispositivo(dis.getTipoDispositivo())
                    .build();
            return Optional.of(dispositivi.save(dis));

        } catch (NoSuchElementException ex){
            log.error(String.format("dispositivo con id = %s non trovato", dispositivoId), ex);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Dispositivo> delete(Long dispositivoId) {
        try {
            var dis = dispositivi.findById(dispositivoId).orElseThrow();
            dispositivi.delete(dis);
            return Optional.of(dis);
        }catch (NoSuchElementException ex){
            log.error(String.format("dispositivo non trovato con id = %s",dispositivoId),ex);
            throw new RuntimeException("non trovo il dispositivo...");
        }catch (Exception ex){
            log.error(String.format("errore eliminazione dispositivo con id = %s",dispositivoId),ex);
            throw new RuntimeException();
        }
    }

    @Override
    public Dispositivo assegna(Long dispositivoId, Long dipendenteId) {
        var dis = dispositivi.findById(dispositivoId)
                .orElseThrow(()-> new RuntimeException("dispositivo non trovato"));
        if (dis.getStatoDispositivo() != StatoDispositivo.DISPONIBILE){
            throw new RuntimeException("Il dispositivo non è disponibile per l'assegnazione");
        }
        var dip = dipendenti.findById(dipendenteId)
                .orElseThrow(()-> new RuntimeException("dipendente non trovato"));
        dis.setDipendente(dip);
        dis.setStatoDispositivo(StatoDispositivo.ASSEGNATO);
        return dispositivi.save(dis);
    }

    @Override
    public Dispositivo rimuoviDispositivoAssegnato(Long dispositivoId, Long dipendenteId) {
       var dis = dispositivi.findById(dispositivoId)
               .orElseThrow(()->new RuntimeException("dispotivo non trovato"));
       if (dis.getDipendente() == null || !dis.getDipendente().getId().equals(dipendenteId)){
           throw new RuntimeException("il dispositivo non puo essere assegnato allo specifico dipendente");
       }
       dis.setDipendente(null);
       dis.setStatoDispositivo(StatoDispositivo.DISPONIBILE);
       return dispositivi.save(dis);
    }
}
