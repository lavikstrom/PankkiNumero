/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PankkiNumeroModel;

/**
 *
 * @author Lauri
 * 26.5.2018
 */
public class VaaraTarkisteException extends Exception{
    
    public VaaraTarkisteException(){
        super("tilinumeron tarkiste ei vastaa tarkistelaskua.");
    }
    
    public VaaraTarkisteException(String msg){
        super(msg);
    }
}