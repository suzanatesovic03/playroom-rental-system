/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.zaposleni;

import domen.Klijent;
import domen.Zaposleni;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author User
 */
public class KreirajZaposlenogSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Zaposleni)) {
            throw new Exception("Sistem nije mogao da kreira zaposlenog");
        }

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((Zaposleni) param);
    }

}
