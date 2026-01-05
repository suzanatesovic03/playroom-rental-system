/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Klijent;
import forme.PrikazKlijenataForma;
import forme.model.ModelTabeleKlijent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author User
 */
public class PrikazKlijenataController {

    private final PrikazKlijenataForma pkf;

    public PrikazKlijenataController(PrikazKlijenataForma pkf) {
        this.pkf = pkf;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pkf.setVisible(true);
    }

    public void pripremiFormu() {
        List<Klijent> klijenti = komunikacija.Komunikacija.getInstanca().ucitajKlijente();
        ModelTabeleKlijent mtp = new ModelTabeleKlijent(klijenti);
        pkf.getjTableKlijenti().setModel(mtp);
        pkf.getjTableKlijenti().setEnabled(false);
        pkf.getjButtonAzuriraj().setEnabled(false);
    }

    private void addActionListener() {
        pkf.addBtnAzurirajListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pkf.getjTableKlijenti().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pkf, "Sistem ne moze da nadje klijenta", "GRESKA", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(pkf, "Sistem je nasao klijenta");
                    ModelTabeleKlijent mtk = (ModelTabeleKlijent) pkf.getjTableKlijenti().getModel();
                    Klijent k = mtk.getLista().get(red);
                    cordinator.Cordinator.getInstanca().dodajParam("klijent", k);
                    cordinator.Cordinator.getInstanca().otvoriIzmeniKlijentaFormu();
                }

            }
        });
        pkf.addBtnPretraziListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pkf.getjTableKlijenti().setEnabled(true);
                String ime = pkf.getjTextFieldIme().getText().trim();
                String mesto = pkf.getjTextFieldMesto().getText().trim();
                ModelTabeleKlijent mtk = (ModelTabeleKlijent) pkf.getjTableKlijenti().getModel();
                mtk.pretrazi(ime, mesto);
                if (mtk.getRowCount() > 0) {
                    JOptionPane.showMessageDialog(pkf, "Sistem je nasao klijente po zadatim kriterijumom");
                    pkf.getjButtonAzuriraj().setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(pkf, "Sistem ne moze da nadje klijenta po zadatim kriterijumima");
                }
            }
        });
        pkf.addBtnResetujPretraguListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                osveziFormu();
            }
        });

    }

    public void osveziFormu() {
        pripremiFormu();
    }

}
