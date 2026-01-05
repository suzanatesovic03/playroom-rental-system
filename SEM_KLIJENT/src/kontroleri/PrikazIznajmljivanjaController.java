/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import com.sun.java.accessibility.util.AWTEventMonitor;
import domen.Igraonica;
import domen.Iznajmljivanje;
import domen.StavkaIznajmljivanja;
import forme.PrikazIznajmljivanjaForma;
import forme.model.ModelTabeleIznajmljivanje;
import forme.model.ModelTabeleKlijent;
import forme.model.ModelTabeleStavkaIznajmljivanja;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author User
 */
public class PrikazIznajmljivanjaController {

    private final PrikazIznajmljivanjaForma pif;

    public PrikazIznajmljivanjaController(PrikazIznajmljivanjaForma pif) {
        this.pif = pif;
        addActionListener();
        addMouseListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pif.setVisible(true);
    }

    public void pripremiFormu() {
        List<Igraonica> sveIgraonice = Komunikacija.getInstanca().ucitajIgraonice();
        List<Iznajmljivanje> iznajmljivanja = komunikacija.Komunikacija.getInstanca().ucitajIznajmljivanja();
        for (Iznajmljivanje iz : iznajmljivanja) {
            if (iz.getStavke() == null) {
                continue;
            }
            for (StavkaIznajmljivanja s : iz.getStavke()) {
                if (s.getIgraonica() == null) {
                    continue;
                }
                int id = s.getIgraonica().getIdIgraonica();
                for (Igraonica ig : sveIgraonice) {
                    if (ig.getIdIgraonica() == id) {
                        s.setIgraonica(ig);
                        break;
                    }
                }
            }
        }
        pif.getjTableIznajmljivanja().setModel(new ModelTabeleIznajmljivanje(iznajmljivanja));
        pif.getjTableStavke().setModel(new ModelTabeleStavkaIznajmljivanja(new ArrayList<>()));
        pif.getjTableIznajmljivanja().setEnabled(false);
        pif.getjButtonAzurirajIznajmljivanje().setEnabled(false);
    }

    private void addActionListener() {
        pif.addBtnAzurirajListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pif.getjTableIznajmljivanja().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pif, "Sistem ne može da nadje iznajmljivanje", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(pif, "Sistem je nasao iznajmljivanje");
                    ModelTabeleIznajmljivanje mti = (ModelTabeleIznajmljivanje) pif.getjTableIznajmljivanja().getModel();
                    Iznajmljivanje iz = mti.getLista().get(red);
                    cordinator.Cordinator.getInstanca().dodajParam("iznajmljivanje", iz);
                    cordinator.Cordinator.getInstanca().otvoriAzurirajIznajmljivanjeFormu();
                }
            }
        });

        pif.addBtnPretraziListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pif.getjTableIznajmljivanja().setEnabled(true);
                String klijent = pif.getjTextFieldKlijent().getText().trim();
                String zaposleni = pif.getjTextFieldZaposleni().getText().trim();
                String datumTxt = pif.getjTextFieldDatumPlacanja().getText().trim();
                LocalDate datum = null;
                DateTimeFormatter df = java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy.");
                if (!datumTxt.isEmpty()) {
                    try {
                        datum = java.time.LocalDate.parse(datumTxt, df);
                    } catch (Exception ex) {
                        javax.swing.JOptionPane.showMessageDialog(pif, "Datum mora biti u formatu dd.MM.yyyy.", "Greška", javax.swing.JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                ModelTabeleIznajmljivanje mti = (ModelTabeleIznajmljivanje) pif.getjTableIznajmljivanja().getModel();
                mti.pretrazi(klijent, zaposleni, datum);
                if (mti.getRowCount() > 0) {
                    JOptionPane.showMessageDialog(pif, "Sistem je nasao iznajmljivanje po zadatim kriterijumom");
                    pif.getjButtonAzurirajIznajmljivanje().setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(pif, "Sistem ne moze da nadje iznajmljivanje po zadatim kriterijumima");
                }
            }
        });

        pif.addBtnResetujPretraguListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                osveziFormu();
            }
        });
    }

    private void addMouseListener() {
        pif.getjTableIznajmljivanja().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int red = pif.getjTableIznajmljivanja().getSelectedRow();
                if (red != -1) {
                    ModelTabeleIznajmljivanje mti = (ModelTabeleIznajmljivanje) pif.getjTableIznajmljivanja().getModel();
                    Iznajmljivanje iznajmljivanje = mti.getLista().get(red);
                    ModelTabeleStavkaIznajmljivanja mts = new ModelTabeleStavkaIznajmljivanja(iznajmljivanje.getStavke());
                    pif.getjTableStavke().setModel(mts);
                }
            }

        });

    }

    public void osveziFormu() {
        pripremiFormu();
    }

}
