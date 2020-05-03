package Marktplaats.domain;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Verkoper extends Gebruiker {
    private String adres;

    private boolean afhalenThuis = false;
    private boolean afhalenMagazijn = false;
    private boolean versturen = false;
    private boolean versturenOnderRemours = false;

    public Verkoper(){}

    public Verkoper(String email, String wachtwoord, String adres,
                     boolean afhalenThuis, boolean afhalenMagazijn, boolean versturen, boolean versturenOnderRembours) {
        this.email = email;
        this.wachtwoord = wachtwoord;
        this.adres = adres;
        this.afhalenThuis = afhalenThuis;
        this.afhalenMagazijn = afhalenMagazijn;
        this.versturen = versturen;
        this.versturenOnderRemours = versturenOnderRembours;

    }

    public boolean getAfhalenThuis() {
        return afhalenThuis;
    }

    public boolean getAfhalenMagazijn() {
        return afhalenMagazijn;
    }

    public boolean getVersturen() {
        return versturen;
    }

    public boolean getVersturenOnderRemours() {
        return versturenOnderRemours;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    @Override
    public String toString() {
        return "Verkoper{" +
                "adres='" + adres + '\'' +
                ", afhalenThuis=" + afhalenThuis +
                ", afhalenMagazijn=" + afhalenMagazijn +
                ", versturen=" + versturen +
                ", versturenOnderRemours=" + versturenOnderRemours +
                ", email='" + email + '\'' +
                ", wachtwoord='" + wachtwoord + '\'' +
                ", lijstVanTeVerkopenArtikelen=" + lijstVanTeVerkopenArtikelen +
                '}';
    }
}
