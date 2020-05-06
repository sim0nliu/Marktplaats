package Marktplaats.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Product extends Artikel {
    //BLOB
    //protected byte bijlagen[];
    @NotNull
    @ElementCollection
    @CollectionTable(name = "bezorgwijzeProduct")
    @Enumerated(EnumType.STRING)
    protected List<Bezorgwijze> bezorgwijzen;

    public Product() {
    }

    public Product(List<String> categorie, String artikelNaam, String omschrijving, BigDecimal prijs, List<Bezorgwijze> bezorgwijzen) {
        setCategorie(categorie);
        setArtikelNaam(artikelNaam);
        setOmschrijving(omschrijving);
        setPrijs(prijs);
        setBezorgwijzen(bezorgwijzen);
    }

    public void setBezorgwijzen(List<Bezorgwijze> bezorgwijzen) {
        this.bezorgwijzen = bezorgwijzen;
    }
}
