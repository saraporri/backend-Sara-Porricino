package it.epicode.services;


import it.epicode.data.Dipendente;
import it.epicode.repositories.DipendenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@Slf4j
public class DipendenteServiceImpl implements DipendenteService {

    @Autowired
    DipendenteRepository dipendenti;


    @Override
    public List<Dipendente> getDipendenti() {
        return dipendenti.findAll();
    }

    @Override
    public Optional<Dipendente> getDipendente(Long id) {
        try {
            return dipendenti.findById(id);
        }catch (Exception ex){
            log.error(String.format("dipendente con id = non trovato",id),ex);
            return Optional.empty();
        }

    }

    @Override
    public Dipendente save(Dipendente dipendente) {
        try {
            return dipendenti.save(dipendente);
        }catch (Exception ex){
            log.error(String.format("salvataggio non riuscito di",dipendente),ex);
            return null;
        }
    }

    @Override
    public Optional<Dipendente> update(Long dipendenteId, Dipendente dipendente) {
        try{
            var dip = dipendenti.findById(dipendenteId).orElseThrow();
            dip.builder()
                    .withNome(dip.getNome())
                    .withCognome(dip.getCognome())
                    .withNomeUtente(dip.getNomeUtente())
                    .withEmail(dip.getEmail())
                    .withDispositivi(dip.getDispositivi())
                    .build();
            return Optional.of(dipendenti.save(dip));
        }
        catch (NoSuchElementException ex){
            log.error(String.format("dipendente con id = %s non trovato", dipendenteId), ex);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Dipendente> delete(Long dipendenteId) {
        try{
            var dip = dipendenti.findById(dipendenteId).orElseThrow();
            dipendenti.delete(dip);
            return Optional.of(dip);
        }catch (NoSuchElementException ex){
            log.error(String.format("dipendente non trovato con id = %s",dipendenteId),ex);
            throw new RuntimeException("non trovo il dipendente...");
        }catch (Exception ex){
           log.error(String.format("errore eliminazione dipendente con id = %s",dipendenteId),ex);
            throw new RuntimeException();
        }
    }
}
