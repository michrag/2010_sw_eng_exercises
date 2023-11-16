package simulatore;

import applicazione.*;

// Receptionist è la classe "Simulatore" (effettua quanto richiesto dal testo d'esame)

public class Receptionist
{
    public static void run()
    {

        Manager.loadData(1); // il giorno 1 (punto 2)
        // (a)
        Manager.checkInPrenot("Carlo");
        Manager.creaAccompagnatore("Carlo", "Carla");
        Manager.eseguiSpesa("Carlo", 6);
        // (b)
        Manager.effettuaPrenotazione("Enzo", Manager.getCameraLibera(2).getNome(), 3, 6, 1, 55550000);
        // (c)
        Manager.checkIn("Franco", 2, 1, 4);
        Manager.creaAccompagnatore("Franco", "Franca");
        //(d)
        Manager.cancellaPrenot("Aldo", 1);

        System.out.println("Fine giorno 1:");
        Manager.stampaCamOccupate();


        Manager.loadData(2); // il giorno 2 (punto 3)
        // (a)
        Manager.eseguiSpesa("Carlo", 15);
        Manager.calcolaConto("Carlo", "cdc");
        Manager.checkOut("Carlo");
        // (b)
        Manager.checkInPrenot("Bruno");
        Manager.creaAccompagnatore("Bruno", "Bruna");
        // (c)
        Manager.effettuaPrenotazione("Gino", Manager.getCameraLibera(1).getNome(), 3, 4, 2, 66662222);
        // (d)
        Manager.effettuaPrenotazione("Ivo", Manager.getCameraLibera(1).getNome(), 3, 7, 2, 77772222);
        // (f) (il punto (e) non c'è nel testo...!)
        Manager.checkIn("Luciano", 2, 2, 4);
        Manager.creaAccompagnatore("Luciano", "Luciana");

        System.out.println("\n\nFine giorno 2:");
        Manager.stampaCamOccupate();

        Manager.loadData(3); // il giorno 3 (punto 4)

        // (a)
        if(!Manager.verificaDisp(1) && !Manager.verificaDisp(2))
        {
            Manager.checkIn("Carla", 3, 3, 4);
        }

        // (b)
        Manager.checkInPrenot("Ivo");
        // (c)
        Manager.checkInPrenot("Enzo");
        Manager.creaAccompagnatore("Enzo", "Enza");
        // (d)
        Manager.cancellaPrenot("Gino", 3);

        System.out.println("\n\nFine giorno 3:");
        Manager.stampaCamOccupate();

        System.out.println("\n\nPrenotazioni attive al giorno 3:");
        Manager.stampaPrenot(3);

    }
}
