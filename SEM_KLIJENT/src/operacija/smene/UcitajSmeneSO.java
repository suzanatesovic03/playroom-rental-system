/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.smene;

import domen.SmenaZaposlenog;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author User
 */
public class UcitajSmeneSO extends ApstraktnaGenerickaOperacija{

    List<SmenaZaposlenog> smene;
    
    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        smene = broker.getAll(new SmenaZaposlenog(), "");
    }
    
    public List<SmenaZaposlenog> getSmene(){
        return smene;
    }
}
