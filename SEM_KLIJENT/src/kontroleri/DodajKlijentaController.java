/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import domen.Klijent;
import domen.Mesto;
import domen.Zaposleni;
import forme.DodajKlijentaForma;
import forme.FormaMod;
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
public class DodajKlijentaController {

    private final DodajKlijentaForma dkf;

    public DodajKlijentaController(DodajKlijentaForma dkf) {
        this.dkf = dkf;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        ucitajMesta();
        pripremiFormu(mod);
        dkf.setVisible(true);
    }

    private void ucitajMesta() {
        try {
            List<Mesto> mesta = Komunikacija.getInstanca().ucitajMesta();
            dkf.getjComboBoxMesto().removeAllItems();
            for (Mesto mesto : mesta) {
                dkf.getjComboBoxMesto().addItem(mesto);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dkf, "Greška prilikom učitavanja mesta!", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addActionListener() {
        dkf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                String imePrezime = dkf.getjTextFieldImePrezime().getText().trim();
                String brojTelefona = dkf.getjTextFieldTelefon().getText().trim();
                Mesto mesto = (Mesto) dkf.getjComboBoxMesto().getSelectedItem();
                String email = dkf.getjTextFieldEmail().getText().trim();
                if (!email.contains("@")) {
                    JOptionPane.showMessageDialog(dkf, "Mejl mora sadrzati znak @");
                    return;
                }
                if (!(brojTelefona.matches("\\d+")) || brojTelefona.length() != 10) {
                    JOptionPane.showMessageDialog(dkf, "Broj telefona mora sadrzati 10 cifara");
                    return;
                }
                Klijent k = new Klijent(-1, imePrezime, brojTelefona, email, mesto);
                try {
                    Komunikacija.getInstanca().dodajKlijenta(k);
                    JOptionPane.showMessageDialog(dkf, "Sistem je zapamtio klijenta", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                    dkf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dkf, "Sistem ne moze da zapamti klijenta", "GRESKA", JOptionPane.ERROR_MESSAGE);

                }
            }
        });
        dkf.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                int id = Integer.parseInt(dkf.getjTextFieldId().getText());
                String imePrezime = dkf.getjTextFieldImePrezime().getText().trim();
                String brojTelefona = dkf.getjTextFieldTelefon().getText().trim();
                String email = dkf.getjTextFieldEmail().getText().trim();
                if (!email.contains("@")) {
                    JOptionPane.showMessageDialog(dkf, "Mejl mora sadrzati znak @");
                    return;
                }
                if (!(brojTelefona.matches("\\d+")) || brojTelefona.length() != 10) {
                    JOptionPane.showMessageDialog(dkf, "Broj telefona mora sadrzati 10 cifara");
                    return;
                }
                Mesto mesto = (Mesto) dkf.getjComboBoxMesto().getSelectedItem();
                Klijent k = new Klijent(id, imePrezime, brojTelefona, email, mesto);
                if (imePrezime.isEmpty() || brojTelefona.isEmpty() || mesto == null || email.isEmpty()) {
                    JOptionPane.showMessageDialog(dkf, "Sistem ne moze da zapamti klijenta", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Komunikacija.getInstanca().azurirajKlijenta(k);
                    JOptionPane.showMessageDialog(dkf, "Sistem je zapamtio klijenta", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                    dkf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dkf, "Sistem ne moze da zapamti klijenta", "GRESKA", JOptionPane.ERROR_MESSAGE);

                }
            }
        });
        dkf.omoguciIzmenuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dkf.getjTextFieldId().setEnabled(false);
                dkf.getjTextFieldImePrezime().setEditable(true);
                dkf.getjTextFieldTelefon().setEditable(true);
                dkf.getjComboBoxMesto().setEnabled(true);
                dkf.getjButtonAzuriraj().setEnabled(true);
                dkf.getjButtonObrisi().setEnabled(false);
                dkf.getjTextFieldEmail().setEditable(true);
            }
        });
        dkf.obrisiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idText = dkf.getjTextFieldId().getText().trim();
                if (idText.isEmpty()) {
                    JOptionPane.showMessageDialog(dkf, "Sistem ne moze da nadje klijenta", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int id = Integer.parseInt(idText);

                Klijent k = new Klijent(id, null, null, null, null);
                try {
                    Komunikacija.getInstanca().obrisiKlijenta(k);

                    JOptionPane.showMessageDialog(
                            dkf,
                            "Sistem je uspesno obrisao klijenta",
                            "Uspesno",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    dkf.dispose();
                    cordinator.Cordinator.getInstanca().osveziFormu();

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(dkf, "Sistem ne moze da obrise klijenta", "Greska", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
        );
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dkf.getjTextFieldId().setEnabled(false);
                dkf.getjButtonAzuriraj().setVisible(false);
                dkf.getjButtonDodaj().setVisible(true);
                dkf.getjButtonObrisi().setVisible(false);
                dkf.getjButtonOmoguciIzmenu().setVisible(false);
                dkf.getjComboBoxMesto().setSelectedIndex(-1);
                break;
            case IZMENI:
                dkf.getjButtonDodaj().setVisible(false);
                dkf.getjTextFieldId().setEnabled(false);
                dkf.getjTextFieldImePrezime().setEditable(false);
                dkf.getjTextFieldTelefon().setEditable(false);
                dkf.getjTextFieldEmail().setEditable(false);
                dkf.getjComboBoxMesto().setEnabled(false);
                dkf.getjButtonAzuriraj().setEnabled(false);
                dkf.getjButtonOmoguciIzmenu().setVisible(true);
                dkf.getjButtonOmoguciIzmenu().setEnabled(true);
                Klijent k = (Klijent) Cordinator.getInstanca().vratiParam("klijent");
                dkf.getjTextFieldImePrezime().setText(k.getImePrezime());
                dkf.getjTextFieldTelefon().setText(k.getKontaktTelefon());
                dkf.getjComboBoxMesto().setSelectedItem(k.getMesto());
                dkf.getjTextFieldEmail().setText(k.getEmail());
                dkf.getjTextFieldId().setText(k.getIdKlijent() + "");
                break;
            default:
                throw new AssertionError();
        }
    }

}
