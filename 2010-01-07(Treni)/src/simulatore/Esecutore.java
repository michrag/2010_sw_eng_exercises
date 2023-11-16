package simulatore;

import applicazione.*;

public class Esecutore
{

    public static void run()
    {

        /*
         * stampe per verificare di aver popolato correttamente il modello
         *
        Manager.stampaTratta("65a");
        Manager.stampaTratta("90b");

        Manager.stampaTreni("65a");
        Manager.stampaTreni("90b");

        Manager.stampaFermateTuttiTreni();
        */

        Manager.stampaPrenotazioni("ES9507");
        Manager.stampaPrenotazioni("ES9515");
        Manager.rispondiPunto2();
        Manager.rispondiPunto3a();
        Manager.rispondiPunto3b();
        Manager.rispondiPunto3c();

    }
}
