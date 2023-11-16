package simulatore;

import applicazione.Gestore;

public class Popolatore
{

    public static void popola()
    {

        Gestore.creaOmbrellone("(1,1)");
        Gestore.creaOmbrellone("(1,2)");
        Gestore.creaOmbrellone("(2,1)");
        Gestore.creaOmbrellone("(2,2)");
        Gestore.creaOmbrellone("(3,1)");
        Gestore.creaOmbrellone("(3,2)");

        Gestore.creaCabina(1);
        Gestore.creaCabina(2);
        Gestore.creaCabina(3);
        Gestore.creaCabina(4);

    }

}
