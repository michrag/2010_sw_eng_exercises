package simulatore;

import applicazione.*;

public class RagFantozzi
{

    public static void run()
    {

        // punto 1
        System.out.println("\n\n--------------------------------------------------Punto 1--------------------------------------------------");
        Manager.stampaTuttiProprietari();

        // punto 2
        Manager.registraSpesaCondominiale("Bencistò", "Potatura alberi", 2000, 2009, 90);
        Manager.registraSpesaDiEdificio("Bencistò", 2, "Rifacimento tetto", 40000, 2009, 270);
        Manager.registraSpesaDiAppartamento("Bencistò", "Bianca", "Stasatura lavandini", 2500, 2009, 300);
        Manager.registraSpesaCondominiale("Al Sole", "Svuotatura pozzo nero", 5000, 2009, 200);
        Manager.registraSpesaCondominiale("Al Sole", "Intonacatura", 30000, 2009, 260);
        Manager.registraSpesaDiAppartamento("Al Sole", "Bruno", "Riparazione scarichi", 1500, 2009, 265);
        System.out.println("\n\n--------------------------------------------------Punto 2--------------------------------------------------");
        Manager.stampaTutteSpeseAnno(2009);

        //punto 3
        System.out.println("\n\n--------------------------------------------------Punto 3--------------------------------------------------");
        Manager.stampaQuotaSpeseProprietario("Bianca", 2009);

        // punto 4
        Manager.registraSpesaCondominiale("Bencistò", "Sfalcio erbe", 2000, 2010, 190);
        Manager.registraSpesaCondominiale("Al Sole", "Riparazioni al tetto", 30000, 2010, 10);
        Manager.registraSpesaDiAppartamento("Al Sole", "Dino", "Sostituzione persiane", 7000, 2010, 180);
        Manager.registraSpesaDiAppartamento("Al Sole", "Bruno", "Imbiancatura bagno", 1500, 2010, 200);
        //Manager.stampaTutteSpeseAnno(2010); // non richiesto
        System.out.println("\n\n--------------------------------------------------Punto 4--------------------------------------------------");
        Manager.stampaQuotaSpeseProprietario("Bruno", 2010);

        //punto 5
        Manager.registraSpesaDiEdificio("Bencistò", 2, "Rifacimento muro di cinta", 20000, 2010, 280);
        Manager.registraSpesaDiAppartamento("Bencistò", "Alessandra", "Piastrelle cucina", 1700, 2010, 290);
        //Manager.stampaTutteSpeseAnno(2010); // non richiesto
        System.out.println("\n\n--------------------------------------------------Punto 5--------------------------------------------------");
        Manager.stampaQuotaSpeseProprietario("Bruno", 2010);

    }

}
