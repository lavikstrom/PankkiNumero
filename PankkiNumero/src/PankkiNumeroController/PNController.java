/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PankkiNumeroController;

import PankkiNumeroModel.HuonoSyoteException;
import PankkiNumeroModel.IPNModel;
import PankkiNumeroModel.IPankkiNumero;
import PankkiNumeroModel.NumeroLoytyyException;
import PankkiNumeroModel.VaaraTarkisteException;
import PankkiNumeroView.IPNView;

/**
 *
 * @author Lauri
 * 26.-27.5.2018
 */
public class PNController implements IPNController {
    
    private IPNModel model;
    private IPNView view;
    
    public PNController(){
        model = new PankkiNumeroModel.PNModel();
        view = new PankkiNumeroView.PNView();
        view.rekisteroiOhjain(this);
    } 
    
    @Override
    public String haeNumerot() {
        return model.haeNumerotModel();
    }

    @Override
    public IPankkiNumero lisaaNumero(String syote) throws HuonoSyoteException, VaaraTarkisteException, NumeroLoytyyException {
        return model.lisaaNumero(syote);
    }
}
