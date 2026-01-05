/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import domen.Igraonica;
import domen.Mesto;
import forme.DodajIgraonicuForma;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author User
 */
public class DodajIgraonicuController {

    private final DodajIgraonicuForma dif;

    public DodajIgraonicuController(DodajIgraonicuForma dif) {
        this.dif = dif;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dif.setVisible(true);
    }

    private void addActionListener() {
        dif.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                String naziv = dif.getjTextFieldNaziv().getText().trim();
                String lokacija = dif.getjTextFieldLokacija().getText().trim();
                String tip = dif.getjTextFieldTipIgraonice().getText().trim();
                double cena;

                try {
                    cena = Double.parseDouble(dif.getjTextFieldCenaSat().getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dif, "Cena mora biti broj.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Igraonica i = new Igraonica(-1, naziv, lokacija, tip, cena);
                try {
                    Komunikacija.getInstanca().dodajIgraonicu(i);
                    JOptionPane.showMessageDialog(dif, "Uspešno dodavanje igraonice.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    dif.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dif, "Greška pri dodavanju igraonice.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        dif.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                int id = Integer.parseInt(dif.getjTextFieldId().getText().trim());
                String naziv = dif.getjTextFieldNaziv().getText().trim();
                String lokacija = dif.getjTextFieldLokacija().getText().trim();
                String tip = dif.getjTextFieldTipIgraonice().getText().trim();
                double cena;

                try {
                    cena = Double.parseDouble(dif.getjTextFieldCenaSat().getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dif, "Cena mora biti broj.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Igraonica i = new Igraonica(id, naziv, lokacija, tip, cena);
                try {
                    Komunikacija.getInstanca().azurirajIgraonicu(i);
                    JOptionPane.showMessageDialog(dif, "Uspešno ažurirana igraonica.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    Cordinator.getInstanca().osveziFormuIgraonica();
                    dif.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dif, "Greška pri ažuriranju igraonice.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dif.getjTextFieldId().setEnabled(false);
                dif.getjButtonAzuriraj().setVisible(false);
                dif.getjButtonDodaj().setVisible(true);
                dif.getjButtonDodaj().setEnabled(true);
                break;
            case IZMENI:
                dif.getjButtonDodaj().setVisible(false);
                dif.getjButtonAzuriraj().setVisible(true);
                dif.getjButtonAzuriraj().setEnabled(true);

                Igraonica i = (Igraonica) Cordinator.getInstanca().vratiParam("igraonica");
                dif.getjTextFieldNaziv().setText(i.getNaziv());
                dif.getjTextFieldLokacija().setText(i.getLokacija());
                dif.getjTextFieldTipIgraonice().setText(i.getTipIgraonice());
                dif.getjTextFieldCenaSat().setText(i.getCenaSat() + "");
                dif.getjTextFieldId().setText(i.getIdIgraonica() + "");
                break;
            default:
                throw new AssertionError();
        }
    }

}
