package Marktplaats.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Gebruiker extends AbstractEntity {

    @Column(unique = true)
    @Email
    protected String email;
    private String wachtwoord;

    private String adres;
    private boolean afhalenThuis = false;
    private boolean afhalenMagazijn = false;
    private boolean verzenden = false;
    private boolean verzendenOnderRembours = false;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "gebruiker")
    private List<Artikel> artikelen = new ArrayList<>();

    public Gebruiker() {
    }

    public Gebruiker(String email, String wachtwoord) {
        this.email = email;
        this.wachtwoord = wachtwoord;
    }

    public Gebruiker(String email, String wachtwoord, String adres, boolean afhalenThuis, boolean afhalenMagazijn, boolean verzenden, boolean verzendenOnderRembours) {
        setEmail(email);
        setWachtwoord(wachtwoord);
        setAdres(adres);
        setAfhalenThuis(afhalenThuis);
        setAfhalenMagazijn(afhalenMagazijn);
        setVerzenden(verzenden);
        setVerzendenOnderRembours(verzendenOnderRembours);
    }

    public void artikelToevoegen(Artikel artikel) {
        this.artikelen.add(artikel);
        artikel.setGebruiker(this);
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }
    public void setAdres(String adres) {
        this.adres = adres;
    }
    public void setVerzenden(boolean verzenden) {
        this.verzenden = verzenden;
    }
    public void setAfhalenThuis(boolean afhalenThuis) {
        this.afhalenThuis = afhalenThuis;
    }
    public void setAfhalenMagazijn(boolean afhalenMagazijn) {
        this.afhalenMagazijn = afhalenMagazijn;
    }
    public void setVerzendenOnderRembours(boolean verzendenOnderRembours) {
        this.verzendenOnderRembours = verzendenOnderRembours;
    }

    @Override
    public String toString() {
        return "Gebruiker{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", wachtwoord='" + wachtwoord + '\'' +
                ", adres='" + adres + '\'' +
                ", artikelen=" + artikelen +
                '}';
    }
}
