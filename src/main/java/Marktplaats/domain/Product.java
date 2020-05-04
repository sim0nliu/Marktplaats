package Marktplaats.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;






@Entity
public class Product extends Artikel {
    //BLOB
    //protected byte bijlagen[];
    @NotNull
    @ElementCollection//(fetch = FetchType.EAGER)
    @CollectionTable(name = "bezorgwijzeProduct")//, joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    protected List<Bezorgwijze> bezorgwijzen;

    public Product() {
    }

    public Product(String categorie, String artikelNaam, String omschrijving, BigDecimal prijs, Bezorgwijze... bezorgwijzen) {
        setCategorie(categorie);
        setArtikelNaam(artikelNaam);
        setOmschrijving(omschrijving);
        setPrijs(prijs);
        setBezorgwijzen(bezorgwijzen);
    }

    public void setBezorgwijzen(Bezorgwijze... bezorgwijzen) {
        this.bezorgwijzen = Arrays.asList(bezorgwijzen);
    }
}
