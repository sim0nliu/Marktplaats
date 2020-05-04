package Marktplaats.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

@Entity
@Table
public class Verkoper extends Gebruiker {
    @NotNull
    private String adres;



    @NotNull
    @ElementCollection//(fetch = FetchType.EAGER)
    @CollectionTable(name = "bezorgwijzeVerkoper")//, joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    protected List<Bezorgwijze> bezorgwijzen;

    public Verkoper(){}

    public Verkoper(String email, String wachtwoord, String adres, Bezorgwijze... bezorgwijzen) {
        this.email = email;
        this.wachtwoord = wachtwoord;
        this.adres = adres;
        setBezorgwijzen(bezorgwijzen);
    }

    public void setBezorgwijzen(Bezorgwijze... bezorgwijzen) {
        this.bezorgwijzen = Arrays.asList(bezorgwijzen);
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public List<Bezorgwijze> getMogelijkeBezorgwijzen() {
        return bezorgwijzen;
    }
}
