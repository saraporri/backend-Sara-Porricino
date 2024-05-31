package it.epicode.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class RegisteredUserDto {
    private long id;
    private String nomeUtente;
    private String soprannome;
    private final List<String> ruolo;

    @Builder(setterPrefix = "with")

    public RegisteredUserDto(long id, String nomeUtente, String soprannome, List<String> ruolo) {
        this.id = id;
        this.nomeUtente = nomeUtente;
        this.soprannome = soprannome;
        this.ruolo = ruolo;
    }
}
