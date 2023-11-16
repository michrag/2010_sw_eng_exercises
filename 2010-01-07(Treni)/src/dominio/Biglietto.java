//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Biglietto.java
//  @ Date : 17/06/2010
//  @ Author : michrag
//
//

package dominio;


public class Biglietto
{
    private Occupazione occupazione;
    private boolean conPrenotazione;
    private int dataViaggio;
    private String idTreno;
    private String stazionePartenza;
    private int oraPartenza;
    private String stazioneArrivo;
    private int oraArrivo;
    private boolean primaClasse;
    private int carrozza;
    private int posto;

    public Biglietto(Occupazione occupazione, boolean conPrenotazione,
                     int dataViaggio, String idTreno, String stazionePartenza,
                     int oraPartenza, String stazioneArrivo, int oraArrivo,
                     boolean primaClasse, int carrozza, int posto)
    {
        super();
        this.occupazione = occupazione;
        this.conPrenotazione = conPrenotazione;
        this.dataViaggio = dataViaggio;
        this.idTreno = idTreno;
        this.stazionePartenza = stazionePartenza;
        this.oraPartenza = oraPartenza;
        this.stazioneArrivo = stazioneArrivo;
        this.oraArrivo = oraArrivo;
        this.primaClasse = primaClasse;
        this.carrozza = carrozza;
        this.posto = posto;
    }

    public Occupazione getOccupazione()
    {
        return occupazione;
    }

    public boolean isConPrenotazione()
    {
        return conPrenotazione;
    }

    public int getDataViaggio()
    {
        return dataViaggio;
    }

    public String getIdTreno()
    {
        return idTreno;
    }

    public String getStazionePartenza()
    {
        return stazionePartenza;
    }

    public int getOraPartenza()
    {
        return oraPartenza;
    }

    public String getStazioneArrivo()
    {
        return stazioneArrivo;
    }

    public int getOraArrivo()
    {
        return oraArrivo;
    }

    public boolean isPrimaClasse()
    {
        return primaClasse;
    }

    public int getCarrozza()
    {
        return carrozza;
    }

    public int getPosto()
    {
        return posto;
    }




}