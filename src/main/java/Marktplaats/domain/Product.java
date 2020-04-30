package Marktplaats.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Product extends Artikel {

    @NotNull
    private String categorie;
    @NotNull
    private String artikelNaam;
    @NotNull
    private BigDecimal prijs;

    private String omschrijving;
    //private ??? bijlagen;

    public Product() {
    }

    public Product(String categorie, String artikelNaam, String omschrijving, BigDecimal prijs) {
        this.categorie = categorie;
        this.artikelNaam = artikelNaam;
        this.omschrijving = omschrijving;
        this.prijs = prijs;
    }

    @Override
    public String toString() {
        return "\n" + "Product{" +
                "categorie='" + categorie + '\'' +
                ", artikelNaam='" + artikelNaam + '\'' +
                ", prijs=" + prijs +
                ", omschrijving='" + omschrijving + '\'' +
                '}';
    }
}
