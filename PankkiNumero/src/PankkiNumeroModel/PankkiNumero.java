/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PankkiNumeroModel;

/**
 *
 * @author Lauri
 * 26.-27.5.2018
 */
public class PankkiNumero implements IPankkiNumero{
    private String lyhytNumero;
    private String pitkaNumero;
    private Pankki pankki;
    
    public final String liianLyhytMsg = "ammettu pankkinumero on liian lyhyt.";
    public final String liianPitkaMsg = "ammettu pankkinumero on liian pitkä.";
    public final String viivaVirheMsg = "pankkinumeron viiva on väärässä paikassa.";
    public final String merkkiVirheMsg = "annetun pankkinumeron kaikkia merkkejä ei voi muuntaa numeroiksi.";
    public final String HuonoPankkiMsg = "annetun pankkinumeron ensimmäiset merkit eivät vastaa mitään pankkia.";
    
    public PankkiNumero(String numero) throws HuonoSyoteException{
        lyhytNumero = numero;
        tarkistaSyote(numero);
        pankki = luoPankki(numero);
        pitkaNumero = luoPitkaNumero(numero, pankki);
    }
    
    @Override
    public String getLyhytNumero() {
        return lyhytNumero;
    }
    @Override
    public String getPitkaNumero() {
        return pitkaNumero;
    }
    @Override
    public String getPankki() {
        return pankki.getNimi();
    }
    
    private void tarkistaSyote(String syote) throws HuonoSyoteException{
        int luku;
        if(syote.length() < NUMERO_PITUUS_MIN) {
            throw new HuonoSyoteException(liianLyhytMsg);
        }else if(syote.length() > NUMERO_PITUUS_MAX){
            throw new HuonoSyoteException(liianPitkaMsg);
        }else if(syote.charAt(VIIVA_KOHTA) != '-'){
            throw new HuonoSyoteException(viivaVirheMsg);
        }else{
            try{
                for(int i = 0; i < syote.length(); i++){
                    if(i != VIIVA_KOHTA){
                        luku = Integer.parseInt(syote.substring(i, i+1));
                    }
                }
            }catch(NumberFormatException nfe){
                //jos jokin merkki (viivaa lukuunottamatta) ei ole numero
                throw new HuonoSyoteException(merkkiVirheMsg);
            }
            //jos pääsee tänne, ei tullut virheitä
        }
    }
    
    private String luoPitkaNumero(String numero, Pankki pankki)throws HuonoSyoteException{
        String eiViivaa;
        StringBuilder sb = new StringBuilder(PITUUS_KONE);
        
        eiViivaa = numero.substring(0, VIIVA_KOHTA) + numero.substring(VIIVA_KOHTA+1);
        
        for(int i = 0; i < PITUUS_KONE; i++){
            sb.append("0");
        }
        
        switch(pankki){
            case NORDEA: case SHB: case SEB: case DANSKE_BANK: case DNB_NOR: case SWEDBANK:
            case S_PANKKI: case AAB: case SAMPO: 
                sb = syotaMerkit(sb, eiViivaa, TYYPPI_A);
                break;
            case AKTIA: case OP:
                sb = syotaMerkit(sb, eiViivaa, TYYPPI_B);
                break;
            default:
                throw new HuonoSyoteException();
        }
        
        return sb.toString();
    }
    
    private StringBuilder syotaMerkit(StringBuilder sb, String merkit, int alkuMerkit){
        int builderPlace;
        
        for(int i = 0; i < alkuMerkit; i++){
            sb.replace(i, i+1, merkit.substring(i, i+1));
        }
        
        for(int i = merkit.length(); i > alkuMerkit; i--){
            builderPlace = sb.length() - (merkit.length()-i);
            sb.replace(builderPlace-1, builderPlace, merkit.substring(i-1, i));
        }
        return sb;
    }
    private int[] tarkistaPankki(String syote) throws HuonoSyoteException{
        int[] merkit = new int[2];
        
        merkit[0] = 0;
        merkit[1] = 0;
        
        if (syote == null){
            throw new HuonoSyoteException(HuonoPankkiMsg);
        }else if (syote.trim().isEmpty()){
            throw new HuonoSyoteException(HuonoPankkiMsg);
        }else if (syote.length() < 2){
            throw new HuonoSyoteException(HuonoPankkiMsg);
        }else{
            try{
                //luetaan yksi merkki, arvo välillä [0,9] tai tapahtuu virhe
                merkit[0] = Integer.parseInt(syote.substring(0, 1));

                if(merkit[0] == 0 || merkit[0] == 7 || merkit[0] == 9){
                    //näille luvuille ei ole pankkia
                    throw new HuonoSyoteException(HuonoPankkiMsg);
                }
                if (merkit[0] == 3){
                    //luetaan yksi merkki, arvo välillä [0,9] tai tapahtuu virhe
                    merkit[1] = Integer.parseInt(syote.substring(1, 2));

                    if(merkit[1] == 0 || merkit[1] == 2 || merkit[1] == 5){
                        //näille luvuille ei ole pankkia
                        throw new HuonoSyoteException(HuonoPankkiMsg);
                    }
                }
                //jos luku ei ollut väärä
                return merkit;
            }catch(NumberFormatException nfe){
                //jos syötteen luettava merkki ei ole luku
                throw new HuonoSyoteException(HuonoPankkiMsg);
            }
        }
    }
    
    private Pankki luoPankki(String numero) throws HuonoSyoteException{
        int[] luvut;
        Pankki out;
        luvut = tarkistaPankki(numero);
        
        switch(luvut[0]){
            case 1: case 2:
                out = Pankki.NORDEA;
                break;
            case 3:
                switch(luvut[1]){
                    case 1:
                        out = Pankki.SHB;
                        break;
                    case 3:
                        out = Pankki.SEB;
                        break;
                    case 4:
                        out = Pankki.DANSKE_BANK;
                        break;
                    case 6:
                        out = Pankki.TAPIOLA;
                        break;
                    case 7:
                        out = Pankki.DNB_NOR;
                        break;
                    case 8:
                        out = Pankki.SWEDBANK;
                        break;
                    case 9:
                        out = Pankki.S_PANKKI;
                        break;
                    default:
                    throw new HuonoSyoteException(HuonoPankkiMsg);
                }
                break;
            case 4:
                out = Pankki.AKTIA;
                break;
            case 5:
                out = Pankki.OP;
                break;
            case 6:
                out = Pankki.AAB;
                break;
            case 8:
                out = Pankki.SAMPO;
                break;
            default:
                throw new HuonoSyoteException(HuonoPankkiMsg);
        }
        return out;
    }
}
