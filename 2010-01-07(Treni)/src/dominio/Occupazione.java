//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Occupazione.java
//  @ Date : 17/06/2010
//  @ Author : michrag
//
//

package dominio;
import java.util.*;


public class Occupazione
{
    private Posto posto;
    private Biglietto biglietto;
    private List<Fermata> fermate;
    private int giorno;
    boolean conPrenotazione;

    public Occupazione(Posto posto, int data, boolean conPrenot)
    {
        super();
        this.posto = posto;
        giorno = data;
        fermate = new ArrayList<Fermata>();
        conPrenotazione = conPrenot;
    }

    public Posto getPosto()
    {
        return posto;
    }

    public void setPosto(Posto posto)
    {
        this.posto = posto;
    }

    public Biglietto getBiglietto()
    {
        return biglietto;
    }

    public void setBiglietto(Biglietto biglietto)
    {
        this.biglietto = biglietto;
    }

    public List<Fermata> getFermate()
    {
        return fermate;
    }

    public void setFermate(List<Fermata> fermate)
    {
        this.fermate = fermate;
    }

    public void addFermata(Fermata f)
    {
        fermate.add(f);
    }

    public int getGiorno()
    {
        return giorno;
    }

    public void setGiorno(int giorno)
    {
        this.giorno = giorno;
    }

    public boolean isConPrenotazione()
    {
        return conPrenotazione;
    }




}
