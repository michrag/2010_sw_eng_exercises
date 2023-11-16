package simulatore;

import applicazione.Manager;

public class Builder
{

    public static void build()
    {

        Manager.creaTratta("65a");
        Manager.addStazione("65a", "Milano");
        Manager.addStazione("65a", "Bologna");
        Manager.addStazione("65a", "Firenze");
        Manager.addStazione("65a", "Roma");

        Manager.creaTratta("90b");
        Manager.addStazione("90b", "Ancona");
        Manager.addStazione("90b", "Bologna");
        Manager.addStazione("90b", "Venezia");

        Manager.creaTreno("ES9507", true, "65a"); // true <=> eurostar
        Manager.creaTreno("IC6011", false, "65a");
        Manager.creaTreno("ES9515", true, "65a");
        Manager.creaTreno("ES9517", true, "65a");

        Manager.creaTreno("ES7708", true, "90b");
        Manager.creaTreno("IC5012", false, "90b");
        Manager.creaTreno("IC5014", false, "90b");

        Manager.addPartenza("ES9507", "Milano", 800);
        Manager.addFermataIntermedia("ES9507", "Bologna", 915, 922);
        Manager.addFermataIntermedia("ES9507", "Firenze", 1000, 1015);
        Manager.addArrivo("ES9507", "Roma", 1145);

        Manager.addPartenza("IC6011", "Milano", 840);
        Manager.addFermataIntermedia("IC6011", "Bologna", 1020, 1028);
        Manager.addFermataIntermedia("IC6011", "Firenze", 1145, 1155);
        Manager.addArrivo("IC6011", "Roma", 1500);

        Manager.addPartenza("ES9515", "Milano", 900);
        Manager.addFermataIntermedia("ES9515", "Bologna", 1015, 1022);
        Manager.addFermataIntermedia("ES9515", "Firenze", 1100, 1115);
        Manager.addArrivo("ES9515", "Roma", 1245);

        Manager.addPartenza("ES9517", "Milano", 950);
        Manager.addFermataIntermedia("ES9517", "Bologna", 1105, 1112);
        Manager.addFermataIntermedia("ES9517", "Firenze", 1120, 1305);
        Manager.addArrivo("ES9517", "Roma", 1335);

        Manager.addPartenza("ES7708", "Ancona", 800);
        Manager.addFermataIntermedia("ES7708", "Bologna", 940, 947);
        Manager.addArrivo("Es7708", "Venezia", 1050);

        Manager.addPartenza("IC5012", "Ancona", 840);
        Manager.addFermataIntermedia("IC5012", "Bologna", 1020, 1030);
        Manager.addArrivo("IC5012", "Venezia", 1210);

        Manager.addPartenza("IC5014", "Ancona", 1140);
        Manager.addFermataIntermedia("IC5014", "Bologna", 1220, 1230);
        Manager.addArrivo("IC5014", "Venezia", 1415);

        Manager.creaCarrozze(); // crea le carrozze come descritto in (punto 1)
        Manager.setPrenotazioni(); // crea tutte le prenotazioni del giorno 4 (punto 1)

    }

}
