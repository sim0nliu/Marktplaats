package Marktplaats.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

public enum Bezorgwijze {
    AfhalenThuis, AfhalenMagazijn, Versturen, VersturenOnderRembours;
}
