package Marktplaats.domain;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "typeArtikel")
public class Artikel extends AbstractEntity {

    @ManyToOne
    private Verkoper verkoper;

    public void setVerkoper(Verkoper verkoper) {
        this.verkoper = verkoper;
    }
}
