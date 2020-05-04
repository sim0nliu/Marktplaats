package Marktplaats.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Categorie extends AbstractEntity {
    private String categorieNaam;

    @OneToOne
    private Artikel artikel;

    public Categorie() {
    }

    public Categorie(String categorieNaam) {
        this.categorieNaam = categorieNaam;
    }

    public String getCategorieNaam() {
        return categorieNaam;
    }
}
