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
public class NumeroLoytyyException extends Exception{
    
    public NumeroLoytyyException(){
        super("tilinumero on jo tietokannassa.");
    }
    
    public NumeroLoytyyException(String msg){
        super(msg);
    }
}