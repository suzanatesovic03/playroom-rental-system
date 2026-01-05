/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.zapsmena;

import domen.ZapSmena;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author User
 */
public class UcitajZapSmenaSO extends ApstraktnaGenerickaOperacija{

    List<ZapSmena> zapsmena;
    
    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        String uslov = " JOIN zaposleni z ON zapsmena.zap = z.idZaposleni " +
                       "JOIN smenazaposlenog s ON zapsmena.smena = s.idSmenaZaposlenog";
        zapsmena = broker.getAll(new ZapSmena(), uslov);
    }

    public List<ZapSmena> getZapSmena() {
        return zapsmena;
    }
    
    
}
