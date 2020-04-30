package Marktplaats.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "typeGebruiker")
public class Gebruiker extends AbstractEntity {

    @Column(unique = true)
    @Email
    protected String email;

    private String wachtwoord;

    public Gebruiker() {
    }

    public Gebruiker(String email, String wachtwoord) {
        this.email = email;
        this.wachtwoord = wachtwoord;
    }

    @Override
    public String toString() {
        return "Gebruiker{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", wachtwoord='" + wachtwoord + '\'' +
                '}';
    }
}
