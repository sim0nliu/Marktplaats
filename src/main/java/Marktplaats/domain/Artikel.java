package Marktplaats.domain;

import javax.persistence.*;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "typeArtikel")
public class Artikel extends AbstractEntity {

    @ManyToOne
    private Gebruiker gebruiker;

    public void setGebruiker(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
    }
}
