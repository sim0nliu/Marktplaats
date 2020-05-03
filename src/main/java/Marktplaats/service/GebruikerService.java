package Marktplaats.service;

import Marktplaats.dao.ArtikelDao;
import Marktplaats.dao.GebruikerDao;
import Marktplaats.domain.Dienst;
import Marktplaats.domain.Gebruiker;
import Marktplaats.domain.Product;
import Marktplaats.domain.Verkoper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class GebruikerService {

    @Inject
    private EntityManager em;
    @Inject
    GebruikerDao gebruikerDao;
    @Inject
    ArtikelDao artikelDao;

    private Gebruiker gebruiker;
    private Verkoper verkoper;

    public GebruikerService() {

    }

    public void start() {
        //TODO: remove after debug
        maakDatabase();
        logIn();
        /////

        String keuze = JOptionPane.showInputDialog("Om gebruik te kunnen maken moet je ingelogd zijn.\n " +
                "| 1. Registreren | 2. Inloggen |");
        switch (keuze) {
            case "1":
                System.out.println("keuze was Registreren");
                break;
            case "2":
                logIn();
                break;
            case "3":
                maakDatabase();
            default:
                start();
                break;
        }
    }

    private void maakDatabase() {
        System.out.println("Database aan het maken");
        gebruikerDao.verkoperToevoegen(new Verkoper("s", "ww", "Het kasteel 115, Apeldoorn",
                true, true, true, true));
        gebruikerDao.verkoperToevoegen(new Verkoper("b", "ww", "Ergens 1, Leusden",
                true, false, true, false));
        gebruikerDao.gebruikerToevoegen(new Gebruiker("chantal@marktplaats.nl", "haarWachtwoord"));

        Verkoper simon = gebruikerDao.getVerkoperEmailEnWachtwoord("s", "ww");
        simon.verkoopArtikel(new Product("Schoenen", "Adidas Ultraboost", "Heren sportschoen", new BigDecimal("119.90"),
                true, true, true, true));
        simon.verkoopArtikel(new Product("Schoenen", "Nike Flyknit", "Heren sportschoen", new BigDecimal("98.97"),
                true, true, false, false));
        simon.verkoopArtikel(new Dienst("Klussen", "Tegels leggen", "Voortuin tegels leggen", new BigDecimal("500")));
        gebruikerDao.updateVerkoper(simon);

        Verkoper bram = (Verkoper) gebruikerDao.getGebruikerEmailEnWachtwoord("b", "ww");
        bram.verkoopArtikel(new Product("Consoles", "PS4", "Gloednieuwe PS4", new BigDecimal("289.00"),
                false, false, false, true));
        bram.verkoopArtikel(new Dienst("Klussen", "Behangen", "Zeer kundig", new BigDecimal("500")));
        bram.verkoopArtikel(new Dienst("Verzorging", "Knippen", "Alleen mannenkapsels", new BigDecimal("30")));
        gebruikerDao.updateVerkoper(bram);

        //start();
    }

    //TODO: Registreren
    public void registreerAccount() {
    }

    public void logIn() {
        System.out.println("Logging in");
        //TODO: reset to normal
        String email = "s";
        String wachtwoord = "ww";
//        String email = JOptionPane.showInputDialog("Voer je e-mailadres in.");
//        String wachtwoord = JOptionPane.showInputDialog("Voer je wachtwoord in.");

        System.out.println("Controleren of de invoer matcht met de database");
        if (gebruikerDao.getVerkoperEmailEnWachtwoord(email, wachtwoord) != null) {
            this.verkoper = gebruikerDao.getVerkoperEmailEnWachtwoord(email, wachtwoord);

            //TODO: reset to normal
            productAanbieden();
            //////////

            //homepage();
        } else if (gebruikerDao.getGebruikerEmailEnWachtwoord(email, wachtwoord) != null) {
            this.gebruiker = gebruikerDao.getGebruikerEmailEnWachtwoord(email, wachtwoord);

        } else {
            String antwoord = JOptionPane.showInputDialog("Inloggen mislukt. Opnieuw proberen?\n" +
                    "| 1. Ja | 2. Nee |");
            if (antwoord.equals("1")) {
                logIn();
            } else {
                start();
            }
        }

    }

    public void artikelAanbieden() {
        String typeArtikel = JOptionPane.showInputDialog("Wat wilt u toevoegen?\n" +
                "| 1. Dienst | 2. Product |");

        switch (typeArtikel) {
            case "1":
                dienstAanbieden();
                break;
            case "2":
                productAanbieden();
                break;
            default:
                System.out.println("Invoer is onjuist. Probeer opnieuw");
                artikelAanbieden();
        }
    }

    //TODO: dienstAanbieden afmaken
    private void dienstAanbieden() {

    }

    //TODO productAanbieden afmaken
    private void productAanbieden() {
        System.out.println("Product aanbieden. Wachten op invoer.");
        //TODO: baseer categorie op mogelijheden van systeem
        String categorie = "testcat";


        //TODO: reset to normal:
        String artikelNaam = "naam";
        String omschrijving = "oms";
//        String artikelNaam = JOptionPane.showInputDialog("Product aanbieden\n" +
//                "Artikelnaam:");
//        String omschrijving = JOptionPane.showInputDialog("Product aanbieden\n" +
//                "Omschrijving:");
        //TODO: Alleen getallen
//        String prijs = JOptionPane.showInputDialog("Product aanbieden\n" +
//                "Prijs:");

        //TODO: reset to normal
        String prijs = "123";
/*        String bijlagen = JOptionPane.showInputDialog("Product aanbieden\n" +
                "Bijlagen:");*/

        //TODO: baseer bezorgwijze op mogelijkheden van accountinvoer
        List<String> mogelijkeBezorgwijzen = new ArrayList<>();
        setMogelijkeBezorgwijzen(mogelijkeBezorgwijzen);

        String antwoord1 = "", antwoord2 = "", antwoord3 = "", antwoord4 = "";

        for (int i = 0; i < mogelijkeBezorgwijzen.size(); i++) {
            String bezorgwijze = mogelijkeBezorgwijzen.get(i);
            switch (bezorgwijze) {
                case "afhalenThuis":
                    antwoord1 = "1";
                    antwoord1 = JOptionPane.showInputDialog("Wil je gebruik maken van " + mogelijkeBezorgwijzen.get(i) + "?\n"
                            + "| 1. Ja | 2. Nee |");
                    break;
                case "afhalenMagazijn":
                    antwoord2 = "1";
                    antwoord2 = JOptionPane.showInputDialog("Wil je gebruik maken van " + mogelijkeBezorgwijzen.get(i) + "?\n"
                            + "| 1. Ja | 2. Nee |");
                    break;
                case "versturen":
                    antwoord3 = "1";
                    antwoord3 = JOptionPane.showInputDialog("Wil je gebruik maken van " + mogelijkeBezorgwijzen.get(i) + "?\n"
                            + "| 1. Ja | 2. Nee |");
                    break;
                case "versturenOnderRembours":
                    antwoord4 = "1";
                    antwoord4 = JOptionPane.showInputDialog("Wil je gebruik maken van " + mogelijkeBezorgwijzen.get(i) + "?\n"
                            + "| 1. Ja | 2. Nee |");
                    break;
            }
        }

        boolean thuisAfhalen = false;
        boolean afhalenMagazijn = false;
        boolean verzenden = false;
        boolean verzendenOnderRembours = false;

        if (antwoord1.equals("1")) {
            thuisAfhalen = true;
        }
        if (antwoord2.equals("1")) {
            afhalenMagazijn = true;
        }
        if (antwoord3.equals("1")) {
            verzenden = true;
        }
        if (antwoord4.equals("1")) {
            verzendenOnderRembours = true;
        }

        verkoper.verkoopArtikel(new Product(categorie, artikelNaam, omschrijving, new BigDecimal(String.valueOf(prijs)),
                thuisAfhalen, afhalenMagazijn, verzenden, verzendenOnderRembours));
        gebruikerDao.updateVerkoper(verkoper);
    }

    private void setMogelijkeBezorgwijzen(List<String> mogelijkeBezorgwijzen) {
        if (verkoper.getAfhalenThuis()) {
            mogelijkeBezorgwijzen.add("afhalenThuis");
        }
        if (verkoper.getAfhalenMagazijn()) {
            mogelijkeBezorgwijzen.add("afhalenMagazijn");
        }
        if (verkoper.getVersturen()) {
            mogelijkeBezorgwijzen.add("versturen");
        }
        if (verkoper.getVersturenOnderRemours()) {
            mogelijkeBezorgwijzen.add("versturenOnderRembours");
        }
    }

    //TODO: Nakijken
//    private String vraagCategorieProduct() {
//        int optienummer = 1;
//        StringJoiner opties = new StringJoiner(" | ", "| ", " |");
//        List<Bezorgwijze> mogelijkeBezorgwijze = gebruiker.getBezorgwijzenVanGebruiker();
//        for (Bezorgwijze b : mogelijkeBezorgwijze) {
//            opties.add(optienummer + ". " + b.toString());
//            optienummer++;
//        }
//        String categorie = JOptionPane.showInputDialog("Product aanbieden\n" +
//                opties + "\n" +
//                "Categorie:");
//        switch (categorie) {
//            case "1":
//                return "afhalenThuis";
//            case "2":
//                return "afhalenMagazijn";
//            case "3":
//                return "verzenden";
//            case "4":
//                return "verzendenOnderRembours";
//            default:
//                vraagCategorieProduct();
//                break;
//        }
//        return categorie;
//    }

    private void homepage() {
        String keuze = JOptionPane.showInputDialog("Homepage\n" +
                "| 1. Contactpagina | 2. Artikel zoeken | 3. Artikel aanbieden | 4. Artikel terugtrekken |");

        switch (keuze) {
            case "1":
                break;
            case "2":
                break;
            case "3":
                artikelAanbieden();
                break;
            case "4":
                break;
        }
    }
}
