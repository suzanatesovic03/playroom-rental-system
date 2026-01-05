/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.iznajmljivanja;

import domen.Iznajmljivanje;
import domen.StavkaIznajmljivanja;
import java.util.Collections;
import static java.util.Collections.emptyList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author User
 */
public class AzurirajIznajmljivanjeSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Iznajmljivanje)) {
            throw new Exception("Sistem ne moze da azurira iznajmljivanje.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Iznajmljivanje iz = (Iznajmljivanje) param;
        broker.edit(iz);

        List<StavkaIznajmljivanja> stareStavke = broker.getAll(new StavkaIznajmljivanja(), " WHERE iznajmljivanje = " + iz.getIdIznajmljivanje());

        List<StavkaIznajmljivanja> nove = iz.getStavke();
        if (nove == null) {
            nove = emptyList();
        }

        for (StavkaIznajmljivanja n : nove) {
            n.setIznjamljivanje(iz);
            StavkaIznajmljivanja stara = nadjiPoRb(stareStavke, n.getRb());
            if (stara == null) {
                broker.add(n);
            } else if (!istiSadrzaj(stara, n)) {
                broker.delete(stara);
                broker.add(n);
            }
        }
        for (StavkaIznajmljivanja s : stareStavke) {
            if (nadjiPoRb(nove, s.getRb()) == null) {
                broker.delete(s);
            }
        }
    }

    private StavkaIznajmljivanja nadjiPoRb(List<StavkaIznajmljivanja> lista, int rb) {
        for (StavkaIznajmljivanja s : lista) {
            if (s.getRb() == rb) {
                return s;
            }
        }
        return null;

    }

    private boolean istiSadrzaj(StavkaIznajmljivanja a, StavkaIznajmljivanja b) {
        boolean istaIgraonica = (a.getIgraonica() == null && b.getIgraonica() == null)
                || (a.getIgraonica() != null && b.getIgraonica() != null
                && a.getIgraonica().getIdIgraonica() == b.getIgraonica().getIdIgraonica());
        return istaIgraonica
                && java.util.Objects.equals(a.getDatumIznajmljivanja(), b.getDatumIznajmljivanja())
                && java.util.Objects.equals(a.getVremeOd(), b.getVremeOd())
                && java.util.Objects.equals(a.getVremeDo(), b.getVremeDo())
                && Double.compare(a.getCenaSat(), b.getCenaSat()) == 0
                && java.util.Objects.equals(a.getDodatnaUsluga(), b.getDodatnaUsluga())
                && Double.compare(a.getCenaDodatneUsluge(), b.getCenaDodatneUsluge()) == 0
                && Double.compare(a.getUkupanIznosIgraonice(), b.getUkupanIznosIgraonice()) == 0;
    }

}
