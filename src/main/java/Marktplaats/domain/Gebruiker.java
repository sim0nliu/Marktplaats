package Marktplaats.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

@Entity
@Inheritance(strategy = TABLE_PER_CLASS)
public class Gebruiker extends AbstractEntity {

    @Column(unique = true)
    @Email
    protected String email;
    protected String wachtwoord;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "verkoper")
    protected List<Artikel> lijstVanTeVerkopenArtikelen;

    public Gebruiker() {
    }

    public Gebruiker(String email, String wachtwoord) {
        this.email = email;
        this.wachtwoord = wachtwoord;
    }

    public void verkoopArtikel(Artikel artikel) {
        this.lijstVanTeVerkopenArtikelen.add(artikel);
        artikel.setVerkoper(this);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    @Override
    public String toString() {
        return "Gebruiker{" +
                "email='" + email + '\'' +
                ", wachtwoord='" + wachtwoord + '\'' +
                ", lijstVanTeVerkopenArtikelen=" + lijstVanTeVerkopenArtikelen +
                '}';
    }
}
