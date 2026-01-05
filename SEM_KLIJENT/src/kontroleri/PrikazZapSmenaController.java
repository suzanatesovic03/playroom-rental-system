/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.ZapSmena;
import forme.PrikazZapSmenaForma;
import forme.model.ModelTabeleMesto;
import forme.model.ModelTabeleZapSmena;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author User
 */
public class PrikazZapSmenaController {

    private final PrikazZapSmenaForma pzsf;

    public PrikazZapSmenaController(PrikazZapSmenaForma pzsf) {
        this.pzsf = pzsf;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pzsf.setVisible(true);
    }

    private void pripremiFormu() {
        List<ZapSmena> zapsmena = komunikacija.Komunikacija.getInstanca().ucitajZapSmena();
        ModelTabeleZapSmena mtzs = new ModelTabeleZapSmena(zapsmena);
        pzsf.getjTableZapSmena().setModel(mtzs);
    }

    private void addActionListener() {
        pzsf.addBtnObrisiListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pzsf.getjTableZapSmena().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pzsf, "Sistem ne može da obriše dodeljenu smenu", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleZapSmena mtzs = (ModelTabeleZapSmena) pzsf.getjTableZapSmena().getModel();
                    ZapSmena zs = mtzs.getLista().get(red);
                    try {
                        Komunikacija.getInstanca().obrisiZapSmena(zs);
                        JOptionPane.showMessageDialog(pzsf, "Sistem je uspešno obrisao dodeljenu smenu", "USPEŠNO", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(pzsf, "Sistem ne može da obriše dodeljenu smenu", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        pzsf.addBtnAzurirajListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pzsf.getjTableZapSmena().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pzsf, "Sistem ne može da učita dodeljenu smenu za izmenu", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleZapSmena mtzs = (ModelTabeleZapSmena) pzsf.getjTableZapSmena().getModel();
                    ZapSmena zs = mtzs.getLista().get(red);
                    cordinator.Cordinator.getInstanca().dodajParam("zapsmena", zs);
                    cordinator.Cordinator.getInstanca().otvoriIzmeniZapSmenaFormu();
                }
                
            }
        });

        pzsf.addBtnPretraziListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = pzsf.getjTextFieldZaposleni().getText().trim();
                ModelTabeleZapSmena mtzs = (ModelTabeleZapSmena) pzsf.getjTableZapSmena().getModel();
                mtzs.pretrazi(naziv);
            }
        });
        pzsf.addBtnResetujPretraguListener(new ActionListener() {
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
