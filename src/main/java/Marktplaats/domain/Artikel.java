package Marktplaats.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public abstract class Artikel extends AbstractEntity {
    //TODO: Hangt af van de bestaande categorieën.
    @NotNull
    protected String categorie;
    @NotNull
    protected String artikelNaam;
    @NotNull
    protected BigDecimal prijs;

    protected String omschrijving;

    @ManyToOne(cascade = CascadeType.ALL)
    Gebruiker verkoper;

    protected boolean bod = false;

    LocalDate tijdVanPlaatsen = LocalDate.now();

    public LocalDate getTijdVanPlaatsen() {
        return tijdVanPlaatsen;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
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
}
