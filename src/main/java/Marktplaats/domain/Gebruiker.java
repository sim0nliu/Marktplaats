package Marktplaats.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;

@Entity
public class Gebruiker extends AbstractEntity {

    @Column(unique = true)
    @Email
    private String email;

    private String wachtwoord;
    private boolean gebruikerIsVerkoper;

    public Gebruiker() {
    }

    public Gebruiker(String email, String wachtwoord) {
        this.email = email;
        this.wachtwoord = wachtwoord;
        this.gebruikerIsVerkoper = false;
    }

    protected Gebruiker(String email, String wachtwoord, boolean gebruikerIsVerkoper) {
        this.email = email;
        this.wachtwoord = wachtwoord;
        this.gebruikerIsVerkoper = gebruikerIsVerkoper;
    }
}
