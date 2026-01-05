/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.zapsmena;

import domen.ZapSmena;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author User
 */
public class AzurirajZapSmenaSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.edit((ZapSmena) param);
    }
    
}
