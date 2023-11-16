package applicazione;

import java.util.*;

import dominio.*;

public class Gestore
{

    private static StabilimentoBalneare BagnoOndaMarina = StabilimentoBalneare.getInstance();


    public static void creaOmbrellone(String posizione)
    {
        Ombrellone ombrellone = new Ombrellone(posizione);
        BagnoOndaMarina.addOmbrellone(ombrellone);
    }


    public static void creaCabina(int numero)
    {
        Cabina cabina = new Cabina(numero);
        BagnoOndaMarina.addCabina(cabina);
    }


    public static void occupaOmbrellone(String nomeCliente, String posizione, int dataInizio,
                                        int dataFine)
    {

        Cliente cliente;

        try
        {
            cliente = BagnoOndaMarina.getCliente(nomeCliente);
        }
        catch(Exception e)
        {
            cliente = new Cliente(nomeCliente);
            BagnoOndaMarina.addCliente(cliente);
        }

        Ombrellone ombrellone = BagnoOndaMarina.getOmbrellone(posizione);

        Periodo periodo = new Periodo(dataInizio, dataFine, cliente);
        Occupazione occupazione = new Occupazione(periodo);
        occupazione.setOmbrellone(ombrellone);
        periodo.setOccupazione(occupazione);
        ombrellone.setOccupazione(occupazione);
        cliente.addPeriodo(periodo);

        System.out.println(cliente.getNome() + " occuperà l'ombrellone " + ombrellone.getPosizione() + " dal " + periodo.getDataInizio() + " al " + periodo.getDataFine());

    }


    public static void opzionaOmbrellone(String nomeCliente, String posizione,
                                         int dataInizio, int dataFine)
    {

        Cliente cliente = BagnoOndaMarina.getCliente(nomeCliente);

        Ombrellone ombrellone = BagnoOndaMarina.getOmbrellone(posizione);

        Periodo periodo = new Periodo(dataInizio, dataFine, cliente);
        Opzione opzione = new Opzione(periodo);
        opzione.setOmbrellone(ombrellone);
        periodo.setOpzione(opzione);
        ombrellone.setOpzione(opzione);
        cliente.addPeriodo(periodo);

        System.out.println(cliente.getNome() + " ha opzionato l'ombrellone " + ombrellone.getPosizione() + " dal " + periodo.getDataInizio() + " al " + periodo.getDataFine());
    }


    public static void cambiaOmbrellone(String nomeCliente)
    {
        Cliente cliente = BagnoOndaMarina.getCliente(nomeCliente);

        boolean trovata = false;
        Periodo periodo = null;
        Opzione opzione = null;

        List<Periodo> periodi = cliente.getPeriodi();

        for(Iterator<Periodo> i = periodi.iterator(); i.hasNext();)
        {
            Periodo periodoOld = (Periodo)i.next();

            try
            {
                Ombrellone ombrellone = periodoOld.getOccupazione().getOmbrellone();
                System.out.println(cliente.getNome() + " lascia l'ombrellone " + ombrellone.getPosizione());
            }
            catch(Exception e) {}
        }

        Iterator<Periodo> itPeriodi = periodi.iterator();

        while(!trovata)
        {
            periodo = (Periodo)itPeriodi.next();

            try
            {
                opzione = periodo.getOpzione();
                Ombrellone ombrellone = periodo.getOpzione().getOmbrellone();
                trovata = true;
                ombrellone.setOpzione(null);
                periodo.setOpzione(null);
            }
            catch(Exception e) {}
        }

        String posizione = opzione.getOmbrellone().getPosizione();
        int dataInizio = periodo.getDataInizio();
        int dataFine = periodo.getDataFine();
        rimuoviAssegnazione(nomeCliente, true);
        occupaOmbrellone(nomeCliente, posizione, dataInizio, dataFine);
    }


    public static void stampaCabinaLibera()
    {

        List<Cabina> cabine = BagnoOndaMarina.getCabine();

        boolean trovata = false;
        Cabina cabina = null;

        Iterator<Cabina> itCabine = cabine.iterator();

        while(!trovata && itCabine.hasNext())
        {
            cabina = (Cabina)itCabine.next();

            try
            {
                @SuppressWarnings("unused")
                Occupazione occupazione = cabina.getOccupazione();
            }
            catch(Exception e)
            {
                trovata = true;
            }
        }

        if(trovata)
        {
            System.out.println("La prima cabina libera è la numero " + cabina.getNumero());
        }
        else
        {
            System.out.println("Nessuna cabina libera!");
        }

    }


    public static void occupaCabina(String nomeCliente, int numeroCabina, int dataInizio,
                                    int dataFine)
    {

        Cliente cliente = BagnoOndaMarina.getCliente(nomeCliente);

        Cabina cabina = BagnoOndaMarina.getCabina(numeroCabina);

        Periodo periodo = new Periodo(dataInizio, dataFine, cliente);
        Occupazione occupazione = new Occupazione(periodo);
        occupazione.setCabina(cabina);
        periodo.setOccupazione(occupazione);
        cabina.setOccupazione(occupazione);
        cliente.addPeriodo(periodo);

        System.out.println(cliente.getNome() + " ha affittato la cabina " + cabina.getNumero() + " dal " + periodo.getDataInizio() + " al " + periodo.getDataFine());

    }


    public static void affittaLettini(String nomeCliente, int numLettini, int dataInizio,
                                      int dataFine)
    {

        Cliente cliente = BagnoOndaMarina.getCliente(nomeCliente);
        Periodo periodo = new Periodo(dataInizio, dataFine, cliente);
        AffittoLettino affittoLettino = new AffittoLettino(cliente, periodo, numLettini);
        cliente.setAffittoLettino(affittoLettino);

        System.out.println(cliente.getNome() + " ha affitato " + affittoLettino.getNumLettini() + " lettini dal " + periodo.getDataInizio() + " al " + periodo.getDataFine());

    }


    public static void stampaOccupazioni()
    {
        List<Cliente> clienti = BagnoOndaMarina.getClienti();

        for(Iterator<Cliente> j = clienti.iterator(); j.hasNext();)
        {
            Cliente cliente = (Cliente)j.next();
            List<Periodo> periodi = cliente.getPeriodi();

            for(Iterator<Periodo> i = periodi.iterator(); i.hasNext();)
            {
                Periodo periodo = (Periodo)i.next();

                try
                {
                    periodo.getOccupazione().getOmbrellone();
                    System.out.print("\n" + cliente.getNome() + " occupa l'ombrellone ");
                }
                catch(Exception e) {}

                try
                {
                    try
                    {
                        System.out.print(periodo.getOccupazione().getOmbrellone().getPosizione());
                        System.out.print(" dal giorno " + periodo.getDataInizio() + " al giorno " + periodo.getDataFine());
                    }
                    catch(Exception e)
                    {
                        try
                        {
                            periodo.getOpzione();
                        }
                        catch(Exception f)
                        {
                            System.out.print(" e la cabina ");
                        }
                    }

                    try
                    {
                        System.out.print(periodo.getOccupazione().getCabina().getNumero());
                        System.out.print(" dal giorno " + periodo.getDataInizio() + " al giorno " + periodo.getDataFine());
                    }
                    catch(Exception e) {}
                }
                catch(Exception e) {} // il periodo non ha occupazione
            }

            if(!periodi.isEmpty())
            {
                System.out.println();
            }

            try
            {
                System.out.print(cliente.getNome() + " ha anche affittato " + cliente.getAffittoLettino().getNumLettini() + " lettini");
                System.out.println(" dal giorno " + cliente.getAffittoLettino().getPeriodo().getDataInizio() + " al giorno " + cliente.getAffittoLettino().getPeriodo().getDataFine());
            }
            catch(Exception e) {}

        }
    }


    public static void stampaOmbrelloniLiberi(int dataFine)
    {
        System.out.println("I seguenti ombrelloni sono disponibili fino al giorno " + dataFine + ":");
        List<Ombrellone> ombrelloni = BagnoOndaMarina.getOmbrelloni();

        for(Iterator<Ombrellone> j = ombrelloni.iterator(); j.hasNext();)
        {
            Ombrellone ombrellone = (Ombrellone)j.next();

            try
            {
                ombrellone.getOccupazione();

                if(ombrellone.getOccupazione().getPeriodo().getDataFine() < dataFine)
                {
                    try
                    {
                        ombrellone.getOpzione();
                        System.out.println("L'ombrellone " + ombrellone.getPosizione() + " è libero dal giorno " + (ombrellone.getOpzione().getPeriodo().getDataFine() + 1));
                    }
                    catch(Exception e)
                    {
                        System.out.println("L'ombrellone " + ombrellone.getPosizione() + " è libero dal giorno " + (ombrellone.getOccupazione().getPeriodo().getDataFine() + 1));
                    }

                }
            }
            catch(Exception e)
            {
                try
                {
                    ombrellone.getOpzione();
                }
                catch(Exception f)
                {
                    System.out.println("L'ombrellone " + ombrellone.getPosizione() + " è libero per tutto il periodo desiderato");
                }
            }
        }
    }


    public static void stampaConto(String nomeCliente)
    {
        Cliente cliente = BagnoOndaMarina.getCliente(nomeCliente);
        int conto = 0;
        int di = 0;
        int df = 0;

        List<Periodo> periodi = cliente.getPeriodi();

        for(Iterator<Periodo> i = periodi.iterator(); i.hasNext();)
        {
            Periodo periodo = (Periodo)i.next();

            try
            {
                @SuppressWarnings("unused")
                Ombrellone ombrellone = periodo.getOccupazione().getOmbrellone();
                di = periodo.getDataInizio();
                df = periodo.getDataFine();

                if(df - di == 0)
                {
                    conto += Ombrellone.getPrezzo1g();
                }

                if(df - di == 1 || df - di == 2)
                {
                    conto += Ombrellone.getPrezzo23gg();
                }

                if(df - di > 2 && df - di < 11)
                {
                    conto += Ombrellone.getPrezzo410gg();
                }

                if(df - di >= 11)
                {
                    conto += Ombrellone.getPrezzo11gg();
                }
            }
            catch(Exception e) {}

            try
            {
                @SuppressWarnings("unused")
                Cabina cabina = periodo.getOccupazione().getCabina();
                di = periodo.getDataInizio();
                df = periodo.getDataFine();

                if(df - di == 0)
                {
                    conto += Cabina.getPrezzo1g();
                }

                if(df - di == 1 || df - di == 2)
                {
                    conto += Cabina.getPrezzo23gg();
                }

                if(df - di > 2 && df - di < 11)
                {
                    conto += Cabina.getPrezzo410gg();
                }

                if(df - di >= 11)
                {
                    conto += Cabina.getPrezzo11gg();
                }
            }
            catch(Exception e) {}
        }

        try
        {
            Periodo periodo = cliente.getAffittoLettino().getPeriodo();
            di = periodo.getDataInizio();
            df = periodo.getDataFine();
            int num = cliente.getAffittoLettino().getNumLettini();
            cliente.getAffittoLettino();
            int prezzo = AffittoLettino.getPrezzo();
            conto += ((df - di + 1) * num * prezzo);
        }
        catch(Exception e) {}

        System.out.println(cliente.getNome() + " spende euro " + conto);

    }


    public static void lasciaCabina(String nomeCliente)
    {
        Cliente cliente = BagnoOndaMarina.getCliente(nomeCliente);

        boolean trovata = false;
        Periodo periodo = null;
        Cabina cabina = null;

        List<Periodo> periodi = cliente.getPeriodi();
        Iterator<Periodo> itPeriodi = periodi.iterator();

        while(!trovata)
        {
            periodo = (Periodo)itPeriodi.next();

            try
            {
                cabina = periodo.getOccupazione().getCabina();
                trovata = true;
                cabina.setOccupazione(null);
                periodo.setOccupazione(null);
            }
            catch(Exception e) {}
        }

        periodi.remove(periodo);

        System.out.println(cliente.getNome() + " lascia la cabina " + cabina.getNumero());
    }


    public static void rimuoviAssegnazione(String nomeCliente, boolean cambia)
    {
        Cliente cliente = BagnoOndaMarina.getCliente(nomeCliente);
        List<Periodo> periodi = cliente.getPeriodi();

        for(Iterator<Periodo> i = periodi.iterator(); i.hasNext();)
        {
            Periodo periodo = (Periodo)i.next();

            try
            {
                Ombrellone ombrellone = periodo.getOccupazione().getOmbrellone();
                ombrellone.setOccupazione(null);
            }
            catch(Exception e) {}

            try
            {
                Cabina cabina = periodo.getOccupazione().getCabina();
                cabina.setOccupazione(null);
            }
            catch(Exception e) {}

            periodo.setOccupazione(null);
        }

        periodi.removeAll(periodi);
        cliente.setAffittoLettino(null);

        if(!cambia)
        {
            System.out.println(cliente.getNome() + " lascia lo stabilimento balneare, liberando l'ombrellone che occupava e l'eventuale cabina");
        }
    }

}
