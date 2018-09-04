/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PankkiNumeroModel;

/**
 *
 * @author Lauri
 * 26.5.-27.2018
 */
public interface IPNModel {
    
    public String haeNumerot(String erotin);
    public String haeNumerotModel();
    public IPankkiNumero lisaaNumero(String syote) throws HuonoSyoteException, VaaraTarkisteException, NumeroLoytyyException;
}
