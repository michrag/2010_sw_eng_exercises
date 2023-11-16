package applicazione;

import java.util.*;

import dominio.*;



public class Manager
{

    private static FS ferrovie = FS.getInstance();


    public static void creaTratta(String codice)
    {
        Tratta tratta = new Tratta(codice);
        ferrovie.addTratta(tratta);
    }

    public static void addStazione(String codiceTratta, String nomeStazione)
    {

        Tratta tratta = ferrovie.getTratta(codiceTratta);

        Stazione stazione;

        try
        {
            stazione = ferrovie.getStazione(nomeStazione);
        }
        catch(Exception e)
        {
            stazione = new Stazione(nomeStazione);
            ferrovie.addStazione(stazione);
        }

        tratta.addStazione(stazione);
    }

    public static void stampaTratta(String codiceTratta)
    {
        Tratta tratta = ferrovie.getTratta(codiceTratta);
        System.out.println(tratta.getNumVerso());

        List<Stazione> stazioni = tratta.getStazioni();

        for(Iterator<Stazione> i = stazioni.iterator(); i.hasNext();)
        {
            Stazione stazione = (Stazione)i.next();
            System.out.println(stazione.getNome());
        }
    }

    public static void creaTreno(String id, boolean eurostar, String codTratta)
    {
        Tratta tratta = ferrovie.getTratta(codTratta);

        Treno treno;
        treno = new Treno(id, eurostar);
        treno.setTratta(tratta);
        tratta.addTreno(treno);
        ferrovie.addTreno(treno);

    }

    public static void stampaTreni(String codTratta)
    {
        Tratta tratta = ferrovie.getTratta(codTratta);
        System.out.println(tratta.getNumVerso());

        List<Treno> treni = tratta.getTreni();

        for(Iterator<Treno> i = treni.iterator(); i.hasNext();)
        {
            Treno treno = (Treno)i.next();
            System.out.println(treno.getId());
        }
    }

    public static void addPartenza(String idTreno, String nomeStazione, int ora)
    {
        Treno treno = ferrovie.getTreno(idTreno);
        Stazione stazione = ferrovie.getStazione(nomeStazione);

        Fermata fermata = new Fermata(ora, true, stazione);
        treno.addFermata(fermata);
        stazione.addFermata(fermata);
    }

    public static void addFermataIntermedia(String idTreno, String nomeStazione,
                                            int oraArrivo, int oraPartenza)
    {
        Treno treno = ferrovie.getTreno(idTreno);
        Stazione stazione = ferrovie.getStazione(nomeStazione);

        Fermata fermata = new Fermata(oraArrivo, oraPartenza, stazione);
        treno.addFermata(fermata);
        stazione.addFermata(fermata);
    }

    public static void addArrivo(String idTreno, String nomeStazione, int ora)
    {
        Treno treno = ferrovie.getTreno(idTreno);
        Stazione stazione = ferrovie.getStazione(nomeStazione);

        Fermata fermata = new Fermata(ora, false, stazione);
        treno.addFermata(fermata);
        stazione.addFermata(fermata);
    }

    public static void stampaFermateTuttiTreni()
    {

        List<Treno> treni = ferrovie.getTreni();

        for(Iterator<Treno> i = treni.iterator(); i.hasNext();)
        {
            Treno treno = (Treno)i.next();
            System.out.println();
            System.out.println(treno.getId());
            List<Fermata> fermate = treno.getFermate();

            for(Iterator<Fermata> j = fermate.iterator(); j.hasNext();)
            {
                Fermata fermata = (Fermata)j.next();
                System.out.print(fermata.getStazione().getNome());

                if(fermata.isPartenza())
                {
                    System.out.print(" - " + fermata.getOraPartenza());
                }

                if(fermata.isIntermedia())
                {
                    System.out.print(" - " + fermata.getOraArrivo() + " " + fermata.getOraPartenza());
                }

                if(fermata.isArrivo())
                {
                    System.out.print(" - " + fermata.getOraArrivo());
                }

                System.out.println();
            }

        }
    }

    public static void creaCarrozze()
    {
        List<Treno> treni = ferrovie.getTreni();

        for(Iterator<Treno> i = treni.iterator(); i.hasNext();)
        {
            Treno treno = (Treno)i.next();

            Carrozza car1 = new Carrozza(treno, true, true, 1);
            treno.addCarrozza(car1);

            if(treno.isEurostar())
            {
                Carrozza car2 = new Carrozza(treno, false, true, 2);
                treno.addCarrozza(car2);
                Carrozza car3 = new Carrozza(treno, false, true, 3);
                treno.addCarrozza(car3);
            }
            else
            {
                Carrozza car2 = new Carrozza(treno, false, false, 2);
                treno.addCarrozza(car2);
                Carrozza car3 = new Carrozza(treno, false, false, 3);
                treno.addCarrozza(car3);
            }

            List<Carrozza> carrozze = treno.getCarrozze();

            for(Iterator<Carrozza> j = carrozze.iterator(); j.hasNext();)
            {
                Carrozza carrozza = (Carrozza)j.next();
                Posto p1 = new Posto(1);
                carrozza.addPosto(p1);
                Posto p2 = new Posto(2);
                carrozza.addPosto(p2);
                Posto p3 = new Posto(3);
                carrozza.addPosto(p3);

                if(!carrozza.isPrimaClasse())
                {
                    Posto p4 = new Posto(4);
                    carrozza.addPosto(p4);
                }

            }
        }
    }

    public static void setPrenotazioni()
    {
        Treno treno = ferrovie.getTreno("ES9507");
        List<Fermata> fermate = treno.getFermate();
        List<Carrozza> carrozze = treno.getCarrozze();

        for(Iterator<Carrozza> j = carrozze.iterator(); j.hasNext();)
        {
            Carrozza carrozza = (Carrozza)j.next();

            if(carrozza.isPrimaClasse())
            {
                List<Posto> posti = carrozza.getPosti();

                for(Iterator<Posto> i = posti.iterator(); i.hasNext();)
                {
                    Posto posto = (Posto)i.next();
                    Occupazione occ = new Occupazione(posto, 4, true);
                    occ.setFermate(fermate);
                    posto.addOccupazione(occ);
                }
            }

            if(carrozza.getNumero() == 2)
            {
                List<Posto> posti2 = carrozza.getPosti();

                for(Iterator<Posto> i = posti2.iterator(); i.hasNext();)
                {
                    Posto posto = (Posto)i.next();

                    if((posto.getNumero() == 1) || (posto.getNumero() == 2))
                    {
                        Occupazione occ = new Occupazione(posto, 4, true);
                        occ.setFermate(fermate);
                        posto.addOccupazione(occ);
                    }
                }
            }
        }

        treno = ferrovie.getTreno("ES9515");
        fermate = treno.getFermate();
        carrozze = treno.getCarrozze();

        for(Iterator<Carrozza> j = carrozze.iterator(); j.hasNext();)
        {
            Carrozza carrozza = (Carrozza)j.next();

            if(carrozza.isPrimaClasse())
            {
                List<Posto> posti = carrozza.getPosti();

                for(Iterator<Posto> i = posti.iterator(); i.hasNext();)
                {
                    Posto posto = (Posto)i.next();

                    if(posto.getNumero() == 1)
                    {
                        Occupazione occ = new Occupazione(posto, 4, true);

                        for(Iterator<Fermata> l = fermate.iterator(); l.hasNext();)
                        {
                            Fermata fermata = (Fermata)l.next();

                            if((fermata.getStazione().getNome().equalsIgnoreCase("Bologna")) ||
                                    (fermata.getStazione().getNome().equalsIgnoreCase("Firenze")))
                            {
                                occ.addFermata(fermata);
                            }
                        }

                        posto.addOccupazione(occ);
                    }
                }
            }
        }
    }

    public static void stampaPrenotazioni(String idTreno)
    {
        Treno treno = ferrovie.getTreno(idTreno);

        System.out.println();
        System.out.println(treno.getId());

        List<Carrozza> carrozze = treno.getCarrozze();

        for(Iterator<Carrozza> j = carrozze.iterator(); j.hasNext();)
        {
            Carrozza carrozza = (Carrozza)j.next();
            System.out.println("Carrozza " + carrozza.getNumero());
            List<Posto> posti = carrozza.getPosti();

            for(Iterator<Posto> i = posti.iterator(); i.hasNext();)
            {
                Posto posto = (Posto)i.next();
                System.out.print("Posto " + posto.getNumero());
                List<Occupazione> occupazioni = posto.getOccupazioni();

                for(Iterator<Occupazione> k = occupazioni.iterator(); k.hasNext();)
                {
                    Occupazione occupazione = (Occupazione)k.next();

                    if(occupazione.isConPrenotazione())
                    {
                        System.out.print(" prenotato");
                    }
                }

                System.out.println();
            }

            System.out.println();
        }
    }

    public static void rispondiPunto2()
    {
        Tratta tratta = ferrovie.getTratta("65a");
        List<Treno> treni = tratta.getTreni();

        System.out.println("Treno - orario di partenza da Bologna");

        for(Iterator<Treno> i = treni.iterator(); i.hasNext();)
        {
            Treno treno = (Treno)i.next();
            List<Fermata> fermate = treno.getFermate();

            for(Iterator<Fermata> j = fermate.iterator(); j.hasNext();)
            {
                Fermata fermata = (Fermata)j.next();

                if(fermata.getStazione().getNome().equalsIgnoreCase("Bologna"))
                {
                    if((fermata.getOraPartenza() >= 900) && (fermata.getOraPartenza() <= 1100))
                    {
                        System.out.println(treno.getId() + " - " + fermata.getOraPartenza());
                    }
                }
            }
        }

    }

    public static void rispondiPunto3a()
    {
        Biglietto biglietto = null;
        int oraP = 0;
        int oraA = 0;
        Treno treno = ferrovie.getTreno("IC5012");
        List<Fermata> fermate = treno.getFermate();
        List<Carrozza> carrozze = treno.getCarrozze();
        fermate = treno.getFermate();
        carrozze = treno.getCarrozze();

        for(Iterator<Carrozza> j = carrozze.iterator(); j.hasNext();)
        {
            Carrozza carrozza = (Carrozza)j.next();

            if(carrozza.isPrimaClasse())
            {
                List<Posto> posti = carrozza.getPosti();

                for(Iterator<Posto> i = posti.iterator(); i.hasNext();)
                {
                    Posto posto = (Posto)i.next();

                    if(posto.getNumero() == 1)
                    {
                        Occupazione occ = new Occupazione(posto, 5, true);

                        for(Iterator<Fermata> l = fermate.iterator(); l.hasNext();)
                        {
                            Fermata fermata = (Fermata)l.next();

                            if((fermata.getStazione().getNome().equalsIgnoreCase("Bologna")))
                            {
                                oraP = fermata.getOraPartenza();
                                occ.addFermata(fermata);
                            }

                            if((fermata.getStazione().getNome().equalsIgnoreCase("Venezia")))
                            {
                                oraA = fermata.getOraArrivo();
                                occ.addFermata(fermata);
                            }

                            biglietto = new Biglietto(occ, true, 5, "IC5012", "Bologna", oraP, "Venezia", oraA, true, 1, 1);

                            occ.setBiglietto(biglietto);
                        }

                        posto.addOccupazione(occ);
                    }
                }
            }
        }

        System.out.println("\n\n" + biglietto.getDataViaggio() + " - " + biglietto.getIdTreno() + " - " + biglietto.getStazionePartenza() + " - " + biglietto.getOraPartenza() + " - " + biglietto.getStazioneArrivo() + " - " + biglietto.getOraArrivo() + " - " + biglietto.getCarrozza() + " - " + biglietto.getPosto());
    }

    public static void rispondiPunto3b()
    {
        Occupazione occ = null;
        Treno treno = ferrovie.getTreno("ES9507");
        List<Carrozza> carrozze = treno.getCarrozze();

        for(Iterator<Carrozza> j = carrozze.iterator(); j.hasNext();)
        {
            Carrozza carrozza = (Carrozza)j.next();

            if(carrozza.isPrimaClasse())
            {
                List<Posto> posti = carrozza.getPosti();

                for(Iterator<Posto> i = posti.iterator(); i.hasNext();)
                {
                    Posto posto = (Posto)i.next();

                    if(posto.getNumero() == 2)
                    {
                        occ = posto.getPrimaOccupazione();
                        posto.rimuoviPrimaOccupazione();
                    }
                }
            }
        }

        treno = ferrovie.getTreno("ES9515");
        carrozze = treno.getCarrozze();

        for(Iterator<Carrozza> j = carrozze.iterator(); j.hasNext();)
        {
            Carrozza carrozza = (Carrozza)j.next();

            if(carrozza.isPrimaClasse())
            {
                List<Posto> posti = carrozza.getPosti();

                for(Iterator<Posto> i = posti.iterator(); i.hasNext();)
                {
                    Posto posto = (Posto)i.next();

                    if(posto.getNumero() == 2)
                    {
                        posto.addOccupazione(occ);
                    }
                }
            }
        }
    }

    public static void rispondiPunto3c()
    {

        List<Treno> treni = ferrovie.getTreni();

        boolean prenotato;

        for(Iterator<Treno> l = treni.iterator(); l.hasNext();)
        {
            Treno treno = (Treno)l.next();
            prenotato = false;
            List<Carrozza> carrozze = treno.getCarrozze();

            for(Iterator<Carrozza> j = carrozze.iterator(); j.hasNext();)
            {
                Carrozza carrozza = (Carrozza)j.next();
                List<Posto> posti = carrozza.getPosti();

                for(Iterator<Posto> i = posti.iterator(); i.hasNext();)
                {
                    Posto posto = (Posto)i.next();
                    List<Occupazione> occupazioni = posto.getOccupazioni();

                    if(!(occupazioni.isEmpty()))
                    {
                        prenotato = true;
                    }
                }
            }

            if(prenotato)
            {
                stampaPrenotazioni(treno.getId());
            }
        }
    }

}
