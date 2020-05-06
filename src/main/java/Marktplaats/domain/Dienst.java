package Marktplaats.domain;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Dienst extends Artikel {

    public Dienst() {

    }

    public Dienst(List<String> categorie, String artikelNaam, String omschrijving, BigDecimal prijs) {
        setCategorie(categorie);
        setArtikelNaam(artikelNaam);
        setOmschrijving(omschrijving);
        setPrijs(prijs);
    }
}
