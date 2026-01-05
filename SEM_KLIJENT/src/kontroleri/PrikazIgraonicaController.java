/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Igraonica;
import domen.Klijent;
import forme.PrikazIgraonicaForma;
import forme.model.ModelTabeleIgraonica;
import forme.model.ModelTabeleKlijent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author User
 */
public class PrikazIgraonicaController {

    private final PrikazIgraonicaForma pif;

    public PrikazIgraonicaController(PrikazIgraonicaForma pif) {
        this.pif = pif;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pif.setVisible(true);
    }

    public void pripremiFormu() {
        List<Igraonica> lista = Komunikacija.getInstanca().ucitajIgraonice();
        ModelTabeleIgraonica model = new ModelTabeleIgraonica(lista);
        pif.getjTableIgraonice().setModel(model);
        pif.getjTableIgraonice().getColumnModel().getColumn(0).setPreferredWidth(30);  // ID
        pif.getjTableIgraonice().getColumnModel().getColumn(1).setPreferredWidth(250); // Naziv
        pif.getjTableIgraonice().getColumnModel().getColumn(2).setPreferredWidth(150); // Lokacija
        pif.getjTableIgraonice().getColumnModel().getColumn(3).setPreferredWidth(250); // Tip
        pif.getjTableIgraonice().getColumnModel().getColumn(4).setPreferredWidth(100);  // Cena
    }

    private void addActionListener() {
        pif.addBtnObrisiListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pif.getjTableIgraonice().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pif, "Sistem ne moze da obrise igraonicu", "GRESKA", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleIgraonica mti = (ModelTabeleIgraonica) pif.getjTableIgraonice().getModel();
                    Igraonica i = mti.getLista().get(red);
                    try {
                        Komunikacija.getInstanca().obrisiIgraonicu(i);
                        JOptionPane.showMessageDialog(pif, "Sistem je uspesno obrisao igronicu", "USPESNO", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(pif, "Sistem ne moze da obrise igraonicu", "GRESKA", JOptionPane.ERROR_MESSAGE);

                    }
                }

            }
        });
        pif.addBtnAzurirajListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pif.getjTableIgraonice().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pif, "Sistem ne može da ažurira igraonicu", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleIgraonica mti = (ModelTabeleIgraonica) pif.getjTableIgraonice().getModel();
                    Igraonica i = mti.getLista().get(red);
                    cordinator.Cordinator.getInstanca().dodajParam("igraonica", i);
                    cordinator.Cordinator.getInstanca().otvoriIzmeniIgraonicuFormu();
                }
            }
        });
        pif.addBtnPretraziListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = pif.getjTextFieldNaziv().getText().trim();
                String lokacija = pif.getjTextFieldLokacija().getText().trim();
                String tip = pif.getjTextFieldTip().getText().trim();
                String cenaText = pif.getjTextFieldCenaSat().getText().trim();
                Double cena = null;
                if (!cenaText.isEmpty()) {
                    try {
                        cena = Double.parseDouble(cenaText);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(pif, "Cena mora biti broj.", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                ModelTabeleIgraonica mti = (ModelTabeleIgraonica) pif.getjTableIgraonice().getModel();
                mti.pretrazi(naziv, lokacija, tip, cena);
            }
        });
        pif.addBtnResetujPretraguListener(new ActionListener() {
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
