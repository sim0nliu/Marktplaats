package Marktplaats.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Bezorgwijze extends AbstractEntity {

    @ManyToMany(mappedBy = "bezorgwijzenVanGebruiker")
    List<Gebruiker> gebruikers = new ArrayList<>();

    boolean afhalenThuis = false;
    boolean afhalenMagazijn = false;
    boolean versturen = false;
    boolean versturenOnderRembours = false;

    public Bezorgwijze() {
    }

    public Bezorgwijze(String... bezorgwijze) {
        for (String s : bezorgwijze) {
            switch (s) {
                case "afhalenThuis":
                    this.afhalenThuis = true;
                    break;
                case "afhalenMagazijn":
                    this.afhalenMagazijn = true;
                    break;
                case "versturen":
                    this.versturen = true;
                    break;
                case "versturenOnderRembours":
                    this.versturenOnderRembours = true;
                    break;
                default:
                    break;
            }
        }
    }

    public Bezorgwijze(boolean afhalenThuis, boolean afhalenMagazijn, boolean versturen, boolean versturenOnderRembours) {
        this.afhalenThuis = afhalenThuis;
        this.afhalenMagazijn = afhalenMagazijn;
        this.versturen = versturen;
        this.versturenOnderRembours = versturenOnderRembours;
    }

    public void gebruikerToevoegen(Gebruiker gebruiker) {
        gebruikers.add(gebruiker);
    }

    @Override
    public String toString() {
        if (afhalenThuis) {
            return "Thuis afhalen";
        }
        if (afhalenMagazijn) {
            return "Afhalen in magazijn";
        }
        if (versturen) {
            return "versturen";
        }
        if (versturenOnderRembours) {
            return "Versturen onder rembours";
        }
        return "";
    }
}
