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
public class HuonoSyoteException extends Exception{
    
    public HuonoSyoteException(){
        super("syötetty numero on virheellinen.");
    }
    
    public HuonoSyoteException(String msg){
        super(msg);
    }
}
