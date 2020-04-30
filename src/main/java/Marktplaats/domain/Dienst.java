package Marktplaats.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Dienst extends Artikel {
    @NotNull
    private String categorie;
    @NotNull
    private String artikelNaam;
    @NotNull
    private BigDecimal prijs;

    private String omschrijving;
    //private ??? bijlagen;
}
