package Marktplaats.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public abstract class Artikel extends AbstractEntity {
    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    protected List<Categorie> categorie = new ArrayList<>();

    @NotNull
    protected String artikelNaam;
    @NotNull
    protected BigDecimal prijs;

    protected String omschrijving;


    @ManyToOne(cascade = CascadeType.ALL)
    protected Gebruiker verkoper;

    protected boolean bod = false;

    LocalDate tijdVanPlaatsen = LocalDate.now();

    public String getArtikelNaam() {
        return artikelNaam;
    }

    public LocalDate getTijdVanPlaatsen() {
        return tijdVanPlaatsen;
    }

    public void setArtikelNaam(String artikelNaam) {
        this.artikelNaam = artikelNaam;
    }

    public void setPrijs(BigDecimal prijs) {
        this.prijs = prijs;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public void setVerkoper(Gebruiker verkoper) {
        this.verkoper = verkoper;
    }

    public void setBod(boolean bod) {
        this.bod = bod;
    }

    public void setCategorie(List<String> categorie) {
        for (String s : categorie) {
            Categorie nieuweCategorie = new Categorie(s);
            this.categorie.add(nieuweCategorie);
            nieuweCategorie.setArtikel(this);
        }
    }
}
