/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.igraonice;

import domen.Igraonica;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author User
 */
public class UcitajIgraoniceSO extends ApstraktnaGenerickaOperacija {

    private List<Igraonica> igraonice;

    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        igraonice = broker.getAll(new Igraonica(), null);
    }

    public List<Igraonica> getIgraonice() {
        return igraonice;
    }
}
