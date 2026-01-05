/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.klijenti;

import domen.Klijent;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author User
 */
public class UcitajKlijenteSO extends ApstraktnaGenerickaOperacija {

    List<Klijent> klijenti;

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat != null && !(objekat instanceof Klijent)) {
            throw new Exception("Sistem ne može da učita klijente.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        String join = " JOIN mesto ON klijent.mesto = mesto.idMesto";
        klijenti = broker.getAll(new Klijent(), join);
    }

    public List<Klijent> getKlijenti() {
        return klijenti;
    }

}
