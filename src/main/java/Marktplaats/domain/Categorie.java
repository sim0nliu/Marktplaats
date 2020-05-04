package Marktplaats.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Categorie extends AbstractEntity {
    private String categorieNaam;

    @ManyToOne(cascade = CascadeType.ALL)
    private Artikel artikel;

    public Categorie() {
    }

    public Categorie(String categorieNaam) {
        this.categorieNaam = categorieNaam;
    }

    public String getCategorieNaam() {
        return categorieNaam;
    }

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }
}
