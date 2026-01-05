/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.klijenti;

import domen.Klijent;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author User
 */
public class ObrisiKlijentaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Klijent)) {
            throw new Exception("Sistem nije mogao da obrise klijenta");
        }

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        try {
            broker.delete((Klijent) param);
        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            throw new Exception("Brisanje nije dozvoljeno jer postoje povezana iznajmljivanja.");
        }
    }

}
