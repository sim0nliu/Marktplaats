package Marktplaats.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Verkoper extends Gebruiker {

    private String adres;
    private boolean afhalenThuis = false;
    private boolean afhalenMagazijn = false;
    private boolean verzenden = false;
    private boolean verzendenOnderRembours = false;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "verkoper")
    private List<Artikel> artikelen = new ArrayList<>();

    public Verkoper() {
    }

    public Verkoper(String email, String wachtwoord, String adres, boolean afhalenThuis, boolean afhalenMagazijn, boolean verzenden, boolean verzendenOnderRembours) {
        super(email, wachtwoord);
        setAdres(adres);
        setAfhalenThuis(afhalenThuis);
        setAfhalenMagazijn(afhalenMagazijn);
        setVerzenden(verzenden);
        setVerzendenOnderRembours(verzendenOnderRembours);
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

    public void addArtikel(Artikel artikel) {
        this.artikelen.add(artikel);
        artikel.setVerkoper(this);
    }

    @Override
    public String toString() {
        return "Verkoper{" +
                "id=" + id +
                ", email=" + email +
                ", adres='" + adres + '\'' +
                ", artikelen=" + artikelen +
                '}';
    }
}
