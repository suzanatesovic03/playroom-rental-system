/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.smene;

import domen.SmenaZaposlenog;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author User
 */
public class DodajSmenuSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof SmenaZaposlenog)) {
            throw new Exception("Sistem nije mogao da kreira smenu zaposlenog");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((SmenaZaposlenog) param);
    }

}
