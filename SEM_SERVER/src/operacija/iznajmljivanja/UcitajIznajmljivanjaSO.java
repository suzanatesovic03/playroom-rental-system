/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.iznajmljivanja;

import domen.Iznajmljivanje;
import domen.StavkaIznajmljivanja;
import java.util.ArrayList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author User
 */
public class UcitajIznajmljivanjaSO extends ApstraktnaGenerickaOperacija {

    List<Iznajmljivanje> iznajmljivanja = new ArrayList<>();

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat != null && !(objekat instanceof Iznajmljivanje)) {
            throw new Exception("Sistem ne može da učita iznajmljivanje");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        String uslov = " i JOIN klijent k ON i.klijent = k.idKlijent "
                + "JOIN zaposleni z ON i.zaposleni = z.idZaposleni";

        iznajmljivanja = broker.getAll(new Iznajmljivanje(), uslov);
        for (Iznajmljivanje iz : iznajmljivanja) {
            List<StavkaIznajmljivanja> stavke = broker.getAll(
                    new StavkaIznajmljivanja(),
                    " JOIN igraonica ON stavkaiznajmljivanja.igraonica = igraonica.idIgraonica WHERE iznajmljivanje = " + iz.getIdIznajmljivanje()
            );
            iz.setStavke((ArrayList<StavkaIznajmljivanja>) stavke);
        }
    }

    public List<Iznajmljivanje> getIznajmljivanja() {
        return iznajmljivanja;
    }

}
