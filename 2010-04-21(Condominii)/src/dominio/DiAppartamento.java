//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : 2010-04-21(Condominii)
//  @ File Name : DiAppartamento.java
//  @ Date : 19/06/2010
//  @ Author : michrag
//
//

package dominio;

public class DiAppartamento extends Spesa
{

    private Appartamento appartamento;

    public DiAppartamento(AnnoFiscale annoFiscale, int data, double importo,
                          String descrizione, Appartamento appartamento)
    {
        super(annoFiscale, data, importo, descrizione);
        this.appartamento = appartamento;
    }


    public Appartamento getAppartamento()
    {
        return appartamento;
    }


    @Override
    public double calcolaQuotaParte(Appartamento a)
    {
        return importo;
    }


    @Override
    public void stampa()
    {
        System.out.println("\nSpesa di appartamento");
        System.out.println("Condominio - Proprietario - Descrizione - Importo - Data");
        System.out.println(appartamento.getPiano().getEdificio().getCondominio().getNome() + " - " + appartamento.getProprietario().getNome() + " - " + descrizione + " - " + importo + " - " + data);

    }


    @Override
    public Condominio getCondominio()
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public Edificio getEdificio()
    {
        // TODO Auto-generated method stub
        return null;
    }

}