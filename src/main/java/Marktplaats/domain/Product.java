package Marktplaats.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    public Product(String categorie, String artikelNaam, String omschrijving, BigDecimal prijs, Bezorgwijze... bezorgwijzenVoorProduct) {
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
