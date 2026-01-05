/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import domen.Klijent;
import domen.Zaposleni;
import forme.DodajZaposlenogForma;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author User
 */
public class DodajZaposlenogController {

    private final DodajZaposlenogForma dzf;

    public DodajZaposlenogController(DodajZaposlenogForma dzf) {
        this.dzf = dzf;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dzf.setVisible(true);
    }

    private void addActionListener() {
        dzf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                String imePrezime = dzf.getjTextFieldImePrezime().getText().trim();
                String email = dzf.getjTextFieldEmail().getText().trim();
                String adresa = dzf.getjTextFieldAdresa().getText().trim();
                String username = dzf.getjTextFieldUsername().getText().trim();
                String password = dzf.getjTextFieldPassword().getText().trim();
                if (!(email.contains("@"))) {
                    JOptionPane.showMessageDialog(dzf, "Email mora da sadrzi @", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Zaposleni z = new Zaposleni(-1, imePrezime, email, adresa, username, password);
                try {
                    Komunikacija.getInstanca().dodajZaposlenog(z);
                    JOptionPane.showMessageDialog(dzf, "Uspešno dodavanje zaposlenog", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    dzf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dzf, "GREŠKA", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        dzf.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                int id = Integer.parseInt(dzf.getjTextFieldId().getText());
                String imePrezime = dzf.getjTextFieldImePrezime().getText().trim();
                String email = dzf.getjTextFieldEmail().getText().trim();
                String adresa = dzf.getjTextFieldAdresa().getText().trim();
                String username = dzf.getjTextFieldUsername().getText().trim();
                String password = dzf.getjTextFieldPassword().getText().trim();

                Zaposleni z = new Zaposleni(id, imePrezime, email, adresa, username, password);
                try {
                    Komunikacija.getInstanca().azurirajZaposlenog(z);
                    JOptionPane.showMessageDialog(dzf, "Uspešno ažuriranje zaposlenog", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    dzf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dzf, "GREŠKA", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dzf.getjTextFieldId().setEnabled(false);
                dzf.getjButtonAzuriraj().setVisible(false);
                dzf.getjButtonDodaj().setVisible(true);
                dzf.getjButtonDodaj().setEnabled(true);
                break;
            case IZMENI:
                dzf.getjButtonDodaj().setVisible(false);
                dzf.getjButtonAzuriraj().setVisible(true);
                dzf.getjButtonAzuriraj().setEnabled(true);

                Zaposleni z = (Zaposleni) Cordinator.getInstanca().vratiParam("zaposleni");
                dzf.getjTextFieldImePrezime().setText(z.getImePrezime());
                dzf.getjTextFieldEmail().setText(z.getEmail());
                dzf.getjTextFieldAdresa().setText(z.getAdresa());
                dzf.getjTextFieldUsername().setText(z.getUsername());
                dzf.getjTextFieldPassword().setText(z.getPassword());
                dzf.getjTextFieldId().setText(z.getIdZaposleni() + "");
                break;
            default:
                throw new AssertionError();
        }
    }

}
