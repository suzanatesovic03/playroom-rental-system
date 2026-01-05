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
public class AzurirajIgraonicuSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Igraonica)) {
            throw new Exception("Niste prosledili validnu igraonicu.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.edit((Igraonica) param);
    }

}
