package applicazione;

import java.util.*;

import dominio.*;

public class Manager
{

    private static StudioProfessionale StudioRagFantozzi = StudioProfessionale.getInstance();

    public static void creaCondominio(String nome, String indirizzo)
    {
        Condominio condominio = new Condominio(nome, indirizzo);
        StudioRagFantozzi.addCondominio(condominio);
    }

    public static void addEdificio2Condominio(String nomeCondominio, int numEdificio,
            String indirizzoEd)
    {
        Condominio condominio = StudioRagFantozzi.getCondominio(nomeCondominio);
        Edificio edificio = new Edificio(indirizzoEd, numEdificio, condominio);
        condominio.addEdificio(edificio);
    }

    public static void addPiano2Edificio(String nomeCondominio, int numEd, int numPiano)
    {
        Condominio condominio = StudioRagFantozzi.getCondominio(nomeCondominio);
        Edificio edificio = condominio.getEdificio(numEd);
        Piano piano = new Piano(numPiano, edificio);
        edificio.addPiano(piano);
    }

    public static void addAppartamento2Piano(String nomeCondominio, int numEd, int numPiano,
            int numApp, int quota, String nomeProprietario)
    {
        Condominio condominio = StudioRagFantozzi.getCondominio(nomeCondominio);
        Edificio edificio = condominio.getEdificio(numEd);
        Piano piano = edificio.getPiano(numPiano);
        Proprietario proprietario;

        try
        {
            proprietario = StudioRagFantozzi.getProprietario(nomeProprietario);
        }
        catch(Exception e)
        {
            proprietario = new Proprietario(nomeProprietario);
            StudioRagFantozzi.addProprietario(proprietario);
        }

        Appartamento appartamento = new Appartamento(numApp, quota, piano, proprietario);
        piano.addAppartamento(appartamento);
        proprietario.addAppartamento(appartamento);

    }

    public static void stampaTuttiProprietari()
    {

        List<Proprietario> proprietari = StudioRagFantozzi.getProprietari();
        System.out.println("Elenco dei proprietari e relativi appartamenti:");

        for(Iterator<Proprietario> j = proprietari.iterator(); j.hasNext();)
        {
            Proprietario proprietario = (Proprietario)j.next();
            System.out.println("\n" + proprietario.getNome() + " possiede i seguenti appartamenti:");
            System.out.println("Condominio - Indirizzo edificio - Piano - Numero al piano - Millesimi");
            List<Appartamento> appartamenti = proprietario.getAppartamenti();

            for(Iterator<Appartamento> i = appartamenti.iterator(); i.hasNext();)
            {
                Appartamento appartamento = (Appartamento)i.next();
                System.out.println(appartamento.getPiano().getEdificio().getCondominio().getNome() + " - " + appartamento.getPiano().getEdificio().getIndirizzo() + " - " + appartamento.getPiano().getNumeroInEd() + " - " + appartamento.getNumInPiano() + " - " + appartamento.getQuota());
            }
        }

    }

    public static void registraSpesaCondominiale(String nomeCondominio, String descrizioneSpesa,
            int importo, int anno, int data)
    {

        Condominio condominio = StudioRagFantozzi.getCondominio(nomeCondominio);
        AnnoFiscale annofiscale;

        try
        {
            annofiscale = StudioRagFantozzi.getAnnoFiscale(anno);
        }
        catch(Exception e)
        {
            annofiscale = new AnnoFiscale(anno);
            StudioRagFantozzi.addAnnoFiscale(annofiscale);
        }

        DiCondominio spesaCondominiale = new DiCondominio(annofiscale, data, importo, descrizioneSpesa, condominio);
        annofiscale.addSpesa(spesaCondominiale);

    }

    public static void registraSpesaDiEdificio(String nomeCondominio, int numEdificio,
            String descrizioneSpesa, int importo, int anno, int data)
    {

        Condominio condominio = StudioRagFantozzi.getCondominio(nomeCondominio);
        Edificio edificio = condominio.getEdificio(numEdificio);
        AnnoFiscale annofiscale;

        try
        {
            annofiscale = StudioRagFantozzi.getAnnoFiscale(anno);
        }
        catch(Exception e)
        {
            annofiscale = new AnnoFiscale(anno);
            StudioRagFantozzi.addAnnoFiscale(annofiscale);
        }

        DiEdificio spesaDiEdificio = new DiEdificio(annofiscale, data, importo, descrizioneSpesa, edificio);
        annofiscale.addSpesa(spesaDiEdificio);

    }

    public static void registraSpesaDiAppartamento(String nomeCondominio,
            String nomeProprietario, String descrizioneSpesa, int importo, int anno, int data)
    {

        boolean match = false;
        Appartamento appartamento = null;

        Condominio condominio = StudioRagFantozzi.getCondominio(nomeCondominio);
        Proprietario proprietario = StudioRagFantozzi.getProprietario(nomeProprietario);
        List<Appartamento> appartamenti = proprietario.getAppartamenti();

        Iterator<Appartamento> itAppartamenti = appartamenti.iterator();

        while(!match)
        {
            appartamento = (Appartamento)itAppartamenti.next();
            Condominio cond = appartamento.getPiano().getEdificio().getCondominio();

            if(cond == condominio)
            {
                match = true;
            }
        }

        AnnoFiscale annofiscale;

        try
        {
            annofiscale = StudioRagFantozzi.getAnnoFiscale(anno);
        }
        catch(Exception e)
        {
            annofiscale = new AnnoFiscale(anno);
            StudioRagFantozzi.addAnnoFiscale(annofiscale);
        }

        DiAppartamento spesaDiAppartamento = new DiAppartamento(annofiscale, data, importo, descrizioneSpesa, appartamento);
        annofiscale.addSpesa(spesaDiAppartamento);

    }

    public static void stampaTutteSpeseAnno(int anno)
    {
        AnnoFiscale annofiscale = StudioRagFantozzi.getAnnoFiscale(anno);
        System.out.println("Spese dell'anno fiscale " + annofiscale.getAnno() + ":");

        List<Spesa> spese = annofiscale.getSpese();

        for(Iterator<Spesa> i = spese.iterator(); i.hasNext();)
        {
            Spesa spesa = (Spesa)i.next();
            spesa.stampa();
        }

    }

    public static void stampaQuotaSpeseProprietario(String nomeProprietario, int anno)
    {

        AnnoFiscale annofiscale = StudioRagFantozzi.getAnnoFiscale(anno);
        Proprietario proprietario = StudioRagFantozzi.getProprietario(nomeProprietario);
        List<Appartamento> appartamenti = proprietario.getAppartamenti();

        double quota = 0;

        for(Iterator <Appartamento> k = appartamenti.iterator(); k.hasNext();)
        {
            Appartamento appartamento = (Appartamento)k.next();
            List<Spesa> spese = annofiscale.getSpese();

            for(Iterator<Spesa> i = spese.iterator(); i.hasNext();)
            {
                Spesa spesa = (Spesa)i.next();

                if(((spesa instanceof DiCondominio) && (spesa.getCondominio() == appartamento.getPiano().getEdificio().getCondominio()))
                        || ((spesa instanceof DiEdificio) && (spesa.getEdificio() == appartamento.getPiano().getEdificio()))
                        || ((spesa instanceof DiAppartamento) && (spesa.getAppartamento() == appartamento)))
                {
                    quota += spesa.calcolaQuotaParte(appartamento);
                }
            }

        }

        System.out.println("Alla data attuale, la spesa imputabile a " + proprietario.getNome() + " nell'anno fiscale " + anno + " è di euro: " + quota);
    }

}
