package Marktplaats.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Verkoper extends Gebruiker {

    private String adres;

    public Verkoper() {
    }

    public Verkoper(String email, String wachtwoord, String adres) {
        super(email, wachtwoord, true);
        this.adres = adres;
    }

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "verkoper")
    private List<Artikel> artikelen = new ArrayList<>();

    public void addArtikel(Artikel artikel) {
        this.artikelen.add(artikel);
        artikel.setVerkoper(this);
    }
}
