package simulatore;

import java.util.*;

import dominio.Albergo;
import applicazione.Manager;

public class Popolatore
{
    private static Albergo albergo = Albergo.getInstance();

    public static void popola()
    {

        // popolazione dell'albergo (punto 1)
        Manager.creaCamera("C1.1", 1, 80);
        Manager.creaCamera("C1.2", 1, 80);
        Manager.creaCamera("C2.1", 2, 110);
        Manager.creaCamera("C2.2", 2, 100);
        Manager.creaCamera("C2.3", 2, 110);
        Manager.creaCamera("C2.4", 2, 96);
        Manager.creaCamera("C3.1", 3, 150);
        Manager.creaCamera("C3.2", 3, 140);

        // prenotazioni attive al giorno 0 (punto 1)
        Manager.loadData(0);
        Manager.effettuaPrenotazione("Aldo", "C1.1", 2, 5, 0, 11110000);
        Manager.effettuaPrenotazione("Bruno", "C2.1", 2, 7, 0, 22220000);
        Manager.effettuaPrenotazione("Carlo", "C2.2", 1, 2, 0, 33339999);
        Manager.effettuaPrenotazione("Dino", "C3.2", 6, 10, 0, 44449999);


    }

}
