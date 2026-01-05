/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import domen.Klijent;
import domen.Mesto;
import forme.DodajMestoForma;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author User
 */
public class DodajMestoController {

    private final DodajMestoForma dmf;

    public DodajMestoController(DodajMestoForma dmf) {
        this.dmf = dmf;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dmf.setVisible(true);
    }

    private void addActionListener() {
        dmf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                String naziv = dmf.getjTextFieldNaziv().getText().trim();
                Mesto m = new Mesto(-1, naziv);
                try {
                    Komunikacija.getInstanca().dodajMesto(m);
                    JOptionPane.showMessageDialog(dmf, "Uspesno dodavanje mesta", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                    dmf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dmf, "GRESKA", "GRESKA", JOptionPane.ERROR_MESSAGE);

                }
            }
        });
        dmf.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                int id = Integer.parseInt(dmf.getjTextFieldId().getText());
                String naziv = dmf.getjTextFieldNaziv().getText().trim();
                Mesto m = new Mesto(id, naziv);
                try {
                    Komunikacija.getInstanca().azurirajMesto(m);
                    JOptionPane.showMessageDialog(dmf, "Uspesno azuriranje mesta", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                    dmf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dmf, "GRESKA", "GRESKA", JOptionPane.ERROR_MESSAGE);

                }
            }
        });
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dmf.getjTextFieldId().setEnabled(false);
                dmf.getjButtonAzuriraj().setVisible(false);
                dmf.getjButtonDodaj().setVisible(true);
                dmf.getjButtonDodaj().setEnabled(true);
                break;
            case IZMENI:
                dmf.getjButtonDodaj().setVisible(false);
                dmf.getjButtonAzuriraj().setVisible(true);
                dmf.getjButtonAzuriraj().setEnabled(true);

                Mesto m = (Mesto) Cordinator.getInstanca().vratiParam("mesto");
                dmf.getjTextFieldNaziv().setText(m.getNaziv());
                dmf.getjTextFieldId().setText(m.getIdMesto() + "");
                break;
            default:
                throw new AssertionError();
        }
    }

}
