/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.zaposleni;

import domen.Zaposleni;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author User
 */
public class UcitajZaposleneSO extends ApstraktnaGenerickaOperacija {

    private List<Zaposleni> zaposleni;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param != null && !(param instanceof Zaposleni)) {
            throw new Exception("Sistem ne može da učita zaposlene.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        zaposleni = broker.getAll(new Zaposleni(), null);
    }

    public List<Zaposleni> getZaposleni() {
        return zaposleni;
    }
}
