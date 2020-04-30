package Marktplaats;

import Marktplaats.service.GebruikerService;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        new App().start();

        //new GebruikerService().start();
    }

    private static void start() {
        EntityManager em = Persistence.createEntityManagerFactory("MySQL").createEntityManager();
        new GebruikerService().start();

        String keuze = JOptionPane.showInputDialog("Om gebruik te kunnen maken moet je ingelogd zijn.\n " +
                "| 1. Registreren | 2. Inloggen |");

        switch (keuze) {
            case "1":
                System.out.println("keuze was Registreren");
                break;
            case "2":
                new GebruikerService().logIn(em);
                break;
            default:
                App.start();
                break;
        }
    }
}
