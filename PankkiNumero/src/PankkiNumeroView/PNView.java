/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PankkiNumeroView;

import PankkiNumeroController.IPNController;
import PankkiNumeroModel.HuonoSyoteException;
import PankkiNumeroModel.NumeroLoytyyException;
import PankkiNumeroModel.VaaraTarkisteException;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Lauri
 * 26.-27.5.2018
 */
public class PNView implements IPNView, ActionListener {
    
    private IPNController controller;
        
    private JFrame ePLFrame;
    private Label syottoTeksti;
    private TextField syotto;
    private Button tallenna;
    private TextArea numeroLista;
    
    public final int nimiPituus = 20;
    
    public PNView(){
        ePLFrame = new JFrame();
        ePLFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ePLFrame.setTitle("Pankkinumerot");
        ePLFrame.setSize(600,500);
        ePLFrame.setLocationRelativeTo(null);
        ePLFrame.setLayout(new FlowLayout());
        ePLFrame.setVisible(true);
        
        syottoTeksti = new Label("Uusi numero:", Label.LEFT);
        
        syotto = new TextField(nimiPituus);
        syotto.setEditable(true);
        
        tallenna = new Button("Lisää");
        tallenna.addActionListener(this);
        
        numeroLista = new TextArea();
        
        ePLFrame.add(syottoTeksti);
        ePLFrame.add(syotto);
        ePLFrame.add(tallenna);
        ePLFrame.add(numeroLista);
    }
    
    @Override
    public void rekisteroiOhjain(IPNController controller) {
        this.controller = controller;
        numeroLista.setText(this.controller.haeNumerot());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String syote;
        
        syote = syotto.getText();
        
        if(syote == null){
            luoIlmoitus("syöttökenttä on tyhjä.");
        }else if(syote.trim().isEmpty()){
            luoIlmoitus("syöttökenttä on tyhjä.");
        }else{
            try{
                controller.lisaaNumero(syote);
                numeroLista.setText(this.controller.haeNumerot());
            }catch(HuonoSyoteException | VaaraTarkisteException | NumeroLoytyyException ex){
                luoIlmoitus(ex);                
            }catch(Exception e){
                luoIlmoitus("tapahtui tuntematon virhe.");
            }
        }
    }  
    
    private void luoIlmoitus(String viesti){
        JOptionPane.showMessageDialog(ePLFrame,
                "Numeroa ei lisätty: " + viesti,
                "Numoreon lisäys keskeytetty",
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void luoIlmoitus(Exception e){
        luoIlmoitus(e.getMessage());
    }
}
