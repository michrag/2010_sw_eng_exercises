package applicazione;

import java.util.*;

import dominio.*;

public class Manager
{

    private static Albergo albergo = Albergo.getInstance();

    private static int dataOdierna;

    public static void loadData(int day)
    {
        dataOdierna = day;
    }

    public static void creaCamera(String nome, int posti, double prezzo)
    {
        Camera camera;
        camera = new Camera(nome, posti, prezzo);
        albergo.addCamera(camera);
    }

    public static void effettuaPrenotazione(String nomeCliente, String nomeCamera,
                                            int dataInizio, int dataFine, int dataPrenot, int numCdC)
    {

        Cliente cliente;

        try
        {
            cliente = albergo.getCliente(nomeCliente);
        }
        catch(Exception e)
        {
            cliente = new Cliente(nomeCliente);
            albergo.addCliente(cliente);
            cliente.setCdC(numCdC);
        }

        Camera camera = albergo.getCamera(nomeCamera);
        Soggiorno soggiorno = new Soggiorno(dataInizio, dataFine, camera, cliente);
        camera.addSoggiorno(soggiorno);
        camera.setPrenotata(true);
        Prenotazione prenotazione = new Prenotazione(dataPrenot, soggiorno);
        soggiorno.setPrenot(prenotazione);
        cliente.setSoggiorno(soggiorno);
    }


    public static void checkInPrenot(String nomeCliente)
    {
        Cliente cliente;
        cliente = albergo.getCliente(nomeCliente); // c'è di sicuro perché ha prenotato!
        Soggiorno soggiorno = cliente.getSoggiorno();
        soggiorno.setInCorso(true);
        soggiorno.getCamera().setOccupata(true);
        cliente.setTipo("standard");
    }

    public static void creaAccompagnatore(String nomeCliente, String nomeAcc)
    {
        Cliente cliente;
        cliente = albergo.getCliente(nomeCliente);
        Cliente accompagnatore = new Cliente(nomeAcc);
        accompagnatore.setTipo("accompagnatore");
        Soggiorno soggiorno = cliente.getSoggiorno();
        accompagnatore.setSoggiorno(soggiorno);
        soggiorno.addCliente(accompagnatore);
    }

    public static void eseguiSpesa(String nomeCliente, double importo)
    {
        Cliente cliente;
        cliente = albergo.getCliente(nomeCliente);
        Soggiorno soggiorno = cliente.getSoggiorno();
        Spesa spesa = new Spesa(importo);
        soggiorno.addSpesa(spesa);
    }

    public static Camera getCameraLibera(int posti)
    {
        List<Camera> camere = albergo.getCamere();
        Camera room = null;
        double prezzomin = 200;

        for(Iterator<Camera> i = camere.iterator(); i.hasNext();)
        {
            Camera camera = (Camera)i.next();

            if(!(camera.isPrenotata()) && (!camera.isOccupata())
                    && (camera.getPosti() == posti) && (camera.getPrezzo() < prezzomin))
            {
                prezzomin = camera.getPrezzo();
                room = camera;
            }
        }

        return room;
    }

    public static void checkIn(String nomeCliente, int posti, int di, int df)
    {
        Cliente cliente;

        try
        {
            cliente = albergo.getCliente(nomeCliente);
        }
        catch(Exception e)
        {
            cliente = new Cliente(nomeCliente);
            albergo.addCliente(cliente);
        }

        Camera camera = getCameraLibera(posti);
        Soggiorno soggiorno = new Soggiorno(di, df, camera, cliente);
        camera.addSoggiorno(soggiorno);
        camera.setOccupata(true);
        camera.setPrenotata(false);
        cliente.setSoggiorno(soggiorno);
        cliente.setTipo("standard");
        soggiorno.setInCorso(true);
    }

    public static void cancellaPrenot(String nomeCliente, int data)
    {
        Cliente cliente;
        cliente = albergo.getCliente(nomeCliente); // c'è di sicuro perché ha prenotato!
        cliente.getSoggiorno().getPrenot().setDataCanc(data);
        Soggiorno soggiorno = cliente.getSoggiorno();
        albergo.rimuoviSoggiorno(soggiorno);
        soggiorno.getCamera().setPrenotata(false);

        if(cliente.getTipo().equalsIgnoreCase("potenziale"))
        {
            cliente.setTipo("infedele");
        }
    }

    public static void stampaCamOccupate()
    {
        List<Camera> camere = albergo.getCamere();
        List<Soggiorno> soggiorni;
        List<Cliente> clienti;

        for(Iterator<Camera> i = camere.iterator(); i.hasNext();)
        {
            Camera camera = (Camera)i.next();

            if(camera.isOccupata())
            {
                System.out.print("\nLa camera " + camera.getNome() + " è occupata fino al ");
                soggiorni = camera.getSoggiorni();

                for(Iterator<Soggiorno> j = soggiorni.iterator(); j.hasNext();)
                {
                    Soggiorno soggiorno = (Soggiorno)j.next();

                    if(soggiorno.isInCorso())
                    {
                        System.out.print(soggiorno.getDataFine());
                        System.out.println(" da: ");
                        clienti = soggiorno.getClienti();

                        for(Iterator<Cliente> k = clienti.iterator(); k.hasNext();)
                        {
                            Cliente cliente = (Cliente)k.next();
                            System.out.println(cliente.getNome() + " - " + cliente.getTipo());
                        }
                    }
                }
            }
        }
    }

    public static void calcolaConto(String nomeCliente, String pagamento)
    {
        Cliente cliente;
        Camera camera;
        cliente = albergo.getCliente(nomeCliente);
        Soggiorno soggiorno = cliente.getSoggiorno();
        camera = soggiorno.getCamera();

        ContoFinale conto = new ContoFinale(pagamento);

        int giorni = soggiorno.getDataFine() - soggiorno.getDataInizio();

        double importo = 0;

        List<Spesa> spese = soggiorno.getSpese();

        for(Iterator<Spesa> i = spese.iterator(); i.hasNext();)
        {
            Spesa spesa = (Spesa)i.next();
            importo += spesa.getImporto();
            conto.addSpesa(spesa);
        }

        importo += (giorni * camera.getPrezzo());

        conto.setImporto(importo);

        soggiorno.setConto(conto);

    }

    public static void checkOut(String nomeCliente)
    {
        Cliente cliente;
        cliente = albergo.getCliente(nomeCliente);
        cliente.getSoggiorno().setInCorso(false);
        cliente.getSoggiorno().getCamera().setPrenotata(false);
        cliente.getSoggiorno().getCamera().setOccupata(false);
    }

    public static boolean verificaDisp(int posti)
    {
        List<Camera> camere = albergo.getCamere();
        Camera room = null;
        double prezzomin = 200;

        for(Iterator<Camera> i = camere.iterator(); i.hasNext();)
        {
            Camera camera = (Camera)i.next();

            if(!(camera.isPrenotata()) && (!camera.isOccupata())
                    && (camera.getPosti() == posti) && (camera.getPrezzo() < prezzomin))
            {
                prezzomin = camera.getPrezzo();
                room = camera;
            }
        }

        if(room == null)
        {
            return false;
        }

        return true;
    }

    public static void stampaPrenot(int data)
    {
        List<Camera> camere = albergo.getCamere();
        List<Soggiorno> soggiorni;
        List<Cliente> clienti;

        for(Iterator<Camera> i = camere.iterator(); i.hasNext();)
        {
            Camera camera = (Camera)i.next();

            if(camera.isPrenotata())
            {
                soggiorni = camera.getSoggiorni();

                for(Iterator<Soggiorno> j = soggiorni.iterator(); j.hasNext();)
                {
                    Soggiorno soggiorno = (Soggiorno)j.next();

                    if(soggiorno.isInCorso() && (soggiorno.getDataFine() > data))
                    {
                        System.out.print("\nLa camera " + camera.getNome() + " è prenotata dal ");
                        System.out.print(soggiorno.getDataInizio());
                        System.out.print(" al ");
                        System.out.print(soggiorno.getDataFine());
                        System.out.println(" da: ");
                        clienti = soggiorno.getClienti();

                        for(Iterator<Cliente> k = clienti.iterator(); k.hasNext();)
                        {
                            Cliente cliente = (Cliente)k.next();
                            System.out.println(cliente.getNome() + " - " + cliente.getTipo());
                        }
                    }
                }
            }
        }
    }

}
