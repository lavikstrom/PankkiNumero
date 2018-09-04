/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PankkiNumeroModel;

import static PankkiNumeroModel.IPankkiNumero.NUMERO_PITUUS_MAX;
import static PankkiNumeroModel.IPankkiNumero.NUMERO_PITUUS_MIN;
import static PankkiNumeroModel.IPankkiNumero.VIIVA_KOHTA;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Lauri
 * 26.-27.5.2018
 */
public class PNModel implements IPNModel {
    private Map<String, IPankkiNumero> pankkinumerot;
    
    public PNModel(){
        pankkinumerot = new HashMap();
        try{
            pankkinumerot.put("100000-16", new PankkiNumero("100000-16"));
            pankkinumerot.put("123456-78912341", new PankkiNumero("123456-78912341"));
        }catch (Exception e){
            //tänne ei pitäisi joutua, sillä tässä vain luodaan esimerkkinumeroja
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public String haeNumerot(String erotin) {
        IPankkiNumero pankkinumero;
        StringBuilder sb = new StringBuilder();
        //laitetaan numeron tiedot helpommin luettavaan muotoon.
        for(String lyhytNumero : pankkinumerot.keySet()){
            pankkinumero = pankkinumerot.get(lyhytNumero);
            sb.append(pankkinumero.getLyhytNumero());
            sb.append(erotin);
            sb.append(pankkinumero.getPitkaNumero());
            sb.append(erotin);
            sb.append(pankkinumero.getPankki());
            sb.append('\n');
        }
        
        return sb.toString();
    }
    
    @Override
    public String haeNumerotModel() {
        //'\t' on varmaan helpoimmin luettava erotinmerkki, eli käytetään sitä.
        return haeNumerot("\t");
    }

    private int laskeTarkiste(String syote){
        int summa = 0, tulos, seuraavaKymppi;
        double d;
        char[] merkit;
        int[] luvut;
        
        merkit = syote.toCharArray();
        //Viimeinen merkki on tarkiste, eli ei oteta sitä mukaan
        luvut = new int[merkit.length-1];
        
        for(int i = 0; i < luvut.length; i++){
            luvut[i] = Character.getNumericValue(merkit[i]);
        }
        
        //Tässä tapauksessa sen arvot ovat välillä 0 < tulo < 18.
        //Eli jos tulo > 9, on sen lukujen summa 1+(tulo-10) eli tulo-9.
        for(int i = 0; i < luvut.length; i++){
            tulos = luvut[i] * IPankkiNumero.PAINOARVOT[i];
            if(tulos > 9){
                tulos -= 9;
            }
            summa += tulos;
        }
        d = (double)summa;
        d /= 10;
        
        seuraavaKymppi = ((int) d)+1;
        seuraavaKymppi *= 10;
        tulos = seuraavaKymppi - summa;
        
        if(tulos == 10){
            tulos = 0;
        }
        
        return tulos;
    }

    @Override
    public IPankkiNumero lisaaNumero(String syote) throws HuonoSyoteException, VaaraTarkisteException, NumeroLoytyyException{
        int tarkiste;
        String pitkaNumero;
        IPankkiNumero numero, tarkastusNumero;
        
        tarkastusNumero = pankkinumerot.get(syote);
        
        if(tarkastusNumero !=  null) {
            //jos numero löytyy jo
            throw new NumeroLoytyyException();
        }else{
            numero = new PankkiNumero(syote);

            pitkaNumero = numero.getPitkaNumero();
            tarkiste = laskeTarkiste(pitkaNumero);

            if(tarkiste != Character.getNumericValue(pitkaNumero.charAt(pitkaNumero.length()-1))){
                throw new VaaraTarkisteException("tarkastelaskun tulos ei täsmää.\nAnnettu tarkiste: "
                        + pitkaNumero.charAt(pitkaNumero.length()-1) + "\nLaskettu tarkiste: " + tarkiste);
            }else{
                pankkinumerot.put(syote, numero);
            }
        }
        //palautetaan valmis pankkinumero
        return numero;
    }
}
