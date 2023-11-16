package simulatore;

import applicazione.Gestore;

public class Segretario
{

    public static void run()
    {

        // punto 1
        System.out.println("--------------------------------------------------Punto 1--------------------------------------------------");
        Gestore.occupaOmbrellone("Aldo", "(1,1)", 18, 24);
        Gestore.occupaCabina("Aldo", 1, 18, 21);

        Gestore.occupaOmbrellone("Amelia", "(1,2)", 18, 27);
        Gestore.affittaLettini("Amelia", 1, 18, 27);

        Gestore.occupaOmbrellone("Bruno", "(2,1)", 18, 21);
        Gestore.occupaCabina("Bruno", 2, 18, 21);

        Gestore.occupaOmbrellone("Bianca", "(2,2)", 18, 21);
        Gestore.affittaLettini("Bianca", 2, 18, 21);

        System.out.println("\nStato delle assegnazioni al giorno 18:");
        Gestore.stampaOccupazioni();

        // punto 2
        System.out.println("\n\n--------------------------------------------------Punto 2--------------------------------------------------");
        System.out.println("Giorno 19: succedono i seguenti avvenimenti:");
        System.out.println("Arriva Carlo...");
        Gestore.stampaOmbrelloniLiberi(24);
        Gestore.occupaOmbrellone("Carlo", "(3,1)", 19, 24);
        Gestore.stampaCabinaLibera();
        Gestore.occupaCabina("Carlo", 3, 19, 24);

        System.out.println("\nArriva Dino...");
        Gestore.stampaOmbrelloniLiberi(19);
        Gestore.occupaOmbrellone("Dino", "(3,2)", 19, 19);
        Gestore.stampaCabinaLibera();
        Gestore.occupaCabina("Dino", 4, 19, 19);
        Gestore.affittaLettini("Dino", 1, 19, 19);

        System.out.println("\nStato delle assegnazioni al giorno 19:");
        Gestore.stampaOccupazioni();
        Gestore.stampaConto("Dino");

        // punto 3
        System.out.println("\n\n--------------------------------------------------Punto 3--------------------------------------------------");

        // giorno 20
        System.out.println("Giorno 20: succedono i seguenti avvenimenti:");
        Gestore.rimuoviAssegnazione("Dino", false);
        Gestore.stampaOccupazioni();
        System.out.println("Arriva Carolina...");
        Gestore.stampaOmbrelloniLiberi(26);
        Gestore.occupaOmbrellone("Carolina", "(3,2)", 20, 21);
        Gestore.opzionaOmbrellone("Carolina", "(2,1)", 22, 26);
        System.out.println("\n(Non richiesto): controllo che l'ombrellone opzionato NON sia considerato libero:");
        Gestore.stampaOmbrelloniLiberi(28);

        // giorno 22
        System.out.println("\nGiorno 22: succedono i seguenti avvenimenti:");
        Gestore.lasciaCabina("Aldo");
        Gestore.rimuoviAssegnazione("Bruno", false);
        Gestore.rimuoviAssegnazione("Bianca", false);
        Gestore.cambiaOmbrellone("Carolina");

        System.out.println("\nRitorna Dino...");
        Gestore.stampaOmbrelloniLiberi(22);
        Gestore.occupaOmbrellone("Dino", "(2,2)", 22, 22);
        Gestore.stampaCabinaLibera();
        Gestore.occupaCabina("Dino", 1, 22, 22);
        Gestore.affittaLettini("Dino", 1, 22, 22);

        System.out.println("\nStato delle assegnazioni al giorno 22:");
        Gestore.stampaOccupazioni();
        System.out.println("\n(Non richiesto): controllo che occupazioni e disponibilità siano coerenti:");
        Gestore.stampaOmbrelloniLiberi(28);

    }

}
