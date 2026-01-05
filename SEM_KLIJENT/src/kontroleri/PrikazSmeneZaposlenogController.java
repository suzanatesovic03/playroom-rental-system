/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Mesto;
import domen.SmenaZaposlenog;
import forme.PrikazSmeneZaposlenogForma;
import forme.model.ModelTabeleMesto;
import forme.model.ModelTabeleSmeneZaposlenog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author User
 */
public class PrikazSmeneZaposlenogController {
    private final PrikazSmeneZaposlenogForma pszf;

    public PrikazSmeneZaposlenogController(PrikazSmeneZaposlenogForma pszf) {
        this.pszf = pszf;
        addActionListener();
    }
    
    public void otvoriFormu(){
        pripremiFormu();
        pszf.setVisible(true);
    }

    private void pripremiFormu() {
        List<SmenaZaposlenog> smene = komunikacija.Komunikacija.getInstanca().ucitajSmene();
        ModelTabeleSmeneZaposlenog mtsz = new ModelTabeleSmeneZaposlenog(smene);
        pszf.getjTableSmeneZaposlenog().setModel(mtsz);
    }

    private void addActionListener() {
         pszf.addBtnObrisiListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pszf.getjTableSmeneZaposlenog().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pszf, "Sistem ne moze da obrise smenu", "GRESKA", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleSmeneZaposlenog mts = (ModelTabeleSmeneZaposlenog) pszf.getjTableSmeneZaposlenog().getModel();
                    SmenaZaposlenog sz = mts.getLista().get(red);
                    try {
                        Komunikacija.getInstanca().obrisiSmenu(sz);
                        JOptionPane.showMessageDialog(pszf, "Sistem je uspesno obrisao smenu", "USPESNO", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(pszf, "Sistem ne moze da obrise smenu", "GRESKA", JOptionPane.ERROR_MESSAGE);

                    }
                }

            }
        });
    }

    
    
    
}
