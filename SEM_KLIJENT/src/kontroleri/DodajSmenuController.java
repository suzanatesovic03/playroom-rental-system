/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import domen.Igraonica;
import domen.SmenaZaposlenog;
import forme.DodajSmenuForma;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author User
 */
public class DodajSmenuController {

    private final DodajSmenuForma dsf;

    public DodajSmenuController(DodajSmenuForma dsf) {
        this.dsf = dsf;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu();
        dsf.setVisible(true);
    }

    private void addActionListener() {
        dsf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                try {
                    LocalTime vremeOd = LocalTime.parse(dsf.getjTextFieldVremeOd().getText().trim());
                    LocalTime vremeDo = LocalTime.parse(dsf.getjTextFieldVremeDo().getText().trim());
                    if (vremeDo.isBefore(vremeOd)) {
                        JOptionPane.showMessageDialog(dsf, "Vreme do mora da bude posle vreme od", "Greska", JOptionPane.ERROR_MESSAGE);
                    }

                    long sati = java.time.Duration.between(vremeOd, vremeDo).toHours();
                    if (sati != 8) {
                        JOptionPane.showMessageDialog(dsf,
                                "Sistem ne može da zapamti smenu.",
                                "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (vremeOd.isAfter(LocalTime.of(13, 0))) {
                        JOptionPane.showMessageDialog(dsf,
                                "Sistem ne može da zapamti smenu.",
                                "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    SmenaZaposlenog s = new SmenaZaposlenog(-1, vremeOd, vremeDo);

                    Komunikacija.getInstanca().dodajSmenu(s);

                    JOptionPane.showMessageDialog(dsf, "Sistem je zapamtio smenu", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    dsf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dsf, "Sistem ne moze da zapamti smenu.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void pripremiFormu() {
        dsf.getjButtonDodajSmenu().setVisible(true);

    }

}
