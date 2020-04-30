package Marktplaats.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Artikel extends AbstractEntity {

    @ManyToOne
    private Verkoper verkoper;

    @NotNull
    private String categorie;
    @NotNull
    private String artikelNaam;
    @NotNull
    private BigDecimal prijs;

    private String omschrijving;
    //private ??? bijlagen;


    public Artikel() {
    }

    public Artikel(String categorie, String artikelNaam, String omschrijving, BigDecimal prijs) {
        this.categorie = categorie;
        this.artikelNaam = artikelNaam;
        this.omschrijving = omschrijving;
        this.prijs = prijs;
    }

    public void setVerkoper(Verkoper verkoper) {
        this.verkoper = verkoper;
    }
}
