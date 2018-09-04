/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PankkiNumeroController;

import PankkiNumeroModel.HuonoSyoteException;
import PankkiNumeroModel.IPankkiNumero;
import PankkiNumeroModel.NumeroLoytyyException;
import PankkiNumeroModel.VaaraTarkisteException;

/**
 *
 * @author Lauri
 * 26.-27.5.2018
 */
public interface IPNController {
    public String haeNumerot();
    public IPankkiNumero lisaaNumero(String syote) throws HuonoSyoteException, VaaraTarkisteException, NumeroLoytyyException;
}
