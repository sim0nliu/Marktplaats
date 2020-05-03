package Marktplaats.domain;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Product extends Artikel {

    private boolean afhalenThuis = false;
    private boolean afhalenMagazijn = false;
    private boolean versturen = false;
    private boolean versturenOnderRembours = false;

    //BLOB
    //protected byte bijlagen[];

    public Product() {
    }

    public Product(String categorie, String artikelNaam, String omschrijving, BigDecimal prijs,
                   boolean afhalenThuis, boolean afhalenMagazijn, boolean versturen, boolean versturenOnderRembours) {
        setCategorie(categorie);
        setArtikelNaam(artikelNaam);
        setOmschrijving(omschrijving);
        setPrijs(prijs);
        setAfhalenThuis(afhalenThuis);
        setAfhalenMagazijn(afhalenMagazijn);
        setVersturen(versturen);
        setVersturenOnderRembours(versturenOnderRembours);
    }

    public void setAfhalenThuis(boolean afhalenThuis) {
        this.afhalenThuis = afhalenThuis;
    }

    public void setAfhalenMagazijn(boolean afhalenMagazijn) {
        this.afhalenMagazijn = afhalenMagazijn;
    }

    public void setVersturen(boolean versturen) {
        this.versturen = versturen;
    }

    public void setVersturenOnderRembours(boolean versturenOnderRembours) {
        this.versturenOnderRembours = versturenOnderRembours;
    }

}
