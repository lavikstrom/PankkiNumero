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
public enum Pankki {
    NORDEA("Nordea Pankki"),
    SHB("Handelsbanken"),
    SEB("Skandinaviska Enskilda Banken"),
    DANSKE_BANK("Danske Bank"),
    TAPIOLA("Tapiola Pankki"),
    DNB_NOR("DnB NOR Bank ASA"),
    SWEDBANK("Swedbank"),
    S_PANKKI(""),
    AKTIA("säästöpankki, paikallisosuuspankki tai Aktia"),
    OP("osuuspankkki, OKO tai Okopankki"),
    AAB("Ålandsbanken"),
    SAMPO("Sampo Pankki");
    
    private final String nimi;
    
    public String getNimi(){
        return nimi;
    }
    
    private Pankki(String nimi){
        this.nimi = nimi;
    }
}
