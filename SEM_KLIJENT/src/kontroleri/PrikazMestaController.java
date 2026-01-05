/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Klijent;
import domen.Mesto;
import forme.PrikazMestaForma;
import forme.model.ModelTabeleKlijent;
import forme.model.ModelTabeleMesto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author User
 */
public class PrikazMestaController {

    private final PrikazMestaForma pmf;

    public PrikazMestaController(PrikazMestaForma pmf) {
        this.pmf = pmf;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pmf.setVisible(true);
    }

    public void pripremiFormu() {
        List<Mesto> mesta = komunikacija.Komunikacija.getInstanca().ucitajMesta();
        ModelTabeleMesto mtm = new ModelTabeleMesto(mesta);
        pmf.getjTableMesta().setModel(mtm);
    }

    private void addActionListener() {
        pmf.addBtnObrisiListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pmf.getjTableMesta().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pmf, "Sistem ne moze da obrise mesto", "GRESKA", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleMesto mtm = (ModelTabeleMesto) pmf.getjTableMesta().getModel();
                    Mesto m = mtm.getLista().get(red);
                    try {
                        Komunikacija.getInstanca().obrisiMesto(m);
                        JOptionPane.showMessageDialog(pmf, "Sistem je uspesno obrisao mesto", "USPESNO", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(pmf, "Sistem ne moze da obrise mesto", "GRESKA", JOptionPane.ERROR_MESSAGE);

                    }
                }

            }
        });

        pmf.addBtnAzurirajListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pmf.getjTableMesta().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pmf, "Sistem ne moze da azurira mesto", "GRESKA", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleMesto mtm = (ModelTabeleMesto) pmf.getjTableMesta().getModel();
                    Mesto m = mtm.getLista().get(red);
                    cordinator.Cordinator.getInstanca().dodajParam("mesto", m);
                    cordinator.Cordinator.getInstanca().otvoriIzmeniMestoFormu();
                }

            }
        });

        pmf.addBtnPretraziListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = pmf.getjTextFieldNaziv().getText().trim();
                ModelTabeleMesto mtk = (ModelTabeleMesto) pmf.getjTableMesta().getModel();
                mtk.pretrazi(naziv);
            }
        });
        pmf.addBtnResetujPretraguListener(new ActionListener() {
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
