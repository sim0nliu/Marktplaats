package Marktplaats;

import Marktplaats.dao.GebruikerDao;
import Marktplaats.dao.VerkoperDao;
import Marktplaats.domain.Artikel;
import Marktplaats.domain.Gebruiker;
import Marktplaats.domain.Verkoper;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {
        new App().start();
    }

    private void start() {
        EntityManager em = Persistence.createEntityManagerFactory("MySQL").createEntityManager();

        GebruikerDao gebruikerDao = new GebruikerDao(em);
        VerkoperDao verkoperDao = new VerkoperDao(em);

        verkoperDao.addGebruiker(new Verkoper(
                "simon@marktplaats.nl",
                "wachtwoord",
                "Het kasteel 115, Apeldoorn"
        ));

        gebruikerDao.addGebruiker(new Gebruiker("bram@marktplaats.nl", "wachtwoord"));

        Verkoper simon = (Verkoper) verkoperDao.getGebruiker(1);
        Gebruiker bram = gebruikerDao.getGebruiker(2);

        simon.addArtikel(new Artikel(
                "Schoenen",
                "Adidas Ultraboost",
                "Heren sportschoen",
                new BigDecimal("119.90")
        ));

        verkoperDao.update(simon);
        gebruikerDao.update(bram);
//

        em.close();
    }
}
