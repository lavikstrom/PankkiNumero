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
public interface IPankkiNumero {
    //vähimmäispituus: 6 numeroa, viiva ja 2 numeroa
    public static final int NUMERO_PITUUS_MIN = (6+1+2);
    //enimmäispituus: 6 numeroa, viiva ja 8 numeroa
    public static final int NUMERO_PITUUS_MAX = (6+1+8);
    //viivan sijainti merkkijonossa (1. kohta olisi 0)
    public static final int VIIVA_KOHTA = 6;
    //konekielisen numeron pituus
    public static final int PITUUS_KONE = 14;
    
    //konekielisen numeron alkuosan pituus luvuilla 1, 2, 3, 6 tai 8 alkavalle numerolle
    public static final int TYYPPI_A = 6;
    //konekielisen numeron alkuosan pituus luvuilla 4 tai 5 alkavalle numerolle
    public static final int TYYPPI_B = 7; 
    
    //painoarvot tarkistelaskulle
    public static final int[] PAINOARVOT = {2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2};
    
    public String getLyhytNumero();
    public String getPitkaNumero();
    public String getPankki();
}
