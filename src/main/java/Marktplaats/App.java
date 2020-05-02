package Marktplaats;

import Marktplaats.domain.Gebruiker;
import Marktplaats.service.GebruikerService;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        GebruikerService gebruikerService = container.select(GebruikerService.class).get();
        gebruikerService.start();
    }


}
