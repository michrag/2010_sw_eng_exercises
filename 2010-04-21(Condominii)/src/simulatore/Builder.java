package simulatore;

import applicazione.Manager;

public class Builder
{

    public static void build()
    {

        // punto 1
        Manager.creaCondominio("Bencistò", "Firenze");
        Manager.addEdificio2Condominio("Bencistò", 1, "via Stretta, 12");
        Manager.addEdificio2Condominio("Bencistò", 2, "via Larga, 1");
        Manager.addPiano2Edificio("Bencistò", 1, 0); // piano terra edificio 1
        Manager.addPiano2Edificio("Bencistò", 1, 1); // primo piano edificio 1
        Manager.addPiano2Edificio("Bencistò", 2, 0); // piano terra edificio 2
        Manager.addAppartamento2Piano("Bencistò", 1, 0, 1, 250, "Aldo");
        Manager.addAppartamento2Piano("Bencistò", 1, 0, 2, 145, "Agenore");
        Manager.addAppartamento2Piano("Bencistò", 1, 1, 1, 260, "Alessandra");
        Manager.addAppartamento2Piano("Bencistò", 2, 0, 1, 130, "Bruno");
        Manager.addAppartamento2Piano("Bencistò", 2, 0, 2, 215, "Bianca");

        Manager.creaCondominio("Al Sole", "Scandicci");
        Manager.addEdificio2Condominio("Al Sole", 1, "via dei Platani, 7");
        Manager.addPiano2Edificio("Al Sole", 1, 0); // piano terra
        Manager.addPiano2Edificio("Al Sole", 1, 1); // primo piano
        Manager.addAppartamento2Piano("Al Sole", 1, 0, 1, 259, "Dino");
        Manager.addAppartamento2Piano("Al Sole", 1, 0, 2, 334, "Desdemona");
        Manager.addAppartamento2Piano("Al Sole", 1, 1, 1, 407, "Bruno");

    }

}
