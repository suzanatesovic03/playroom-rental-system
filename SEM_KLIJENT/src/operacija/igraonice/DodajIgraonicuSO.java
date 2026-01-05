/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.igraonice;

import domen.Igraonica;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author User
 */
public class DodajIgraonicuSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Igraonica)) {
            throw new Exception("Prosleđeni objekat nije validna instanca klase Igraonica.");
        }

        Igraonica i = (Igraonica) param;

        if (i.getNaziv() == null || i.getNaziv().isEmpty()) {
            throw new Exception("Naziv igraonice ne sme biti prazan.");
        }

        if (i.getLokacija() == null || i.getLokacija().isEmpty()) {
            throw new Exception("Lokacija igraonice ne sme biti prazna.");
        }

        if (i.getTipIgraonice() == null || i.getTipIgraonice().isEmpty()) {
            throw new Exception("Tip igraonice ne sme biti prazan.");
        }

        if (i.getCenaSat() <= 0) {
            throw new Exception("Cena po satu mora biti veća od nule.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((Igraonica) param);
    }

}
