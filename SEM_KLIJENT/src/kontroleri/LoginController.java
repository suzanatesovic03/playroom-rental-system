/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import domen.Zaposleni;
import forme.LoginForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author User
 */
public class LoginController {

    private final LoginForma lf;

    public LoginController(LoginForma lf) {
        this.lf = lf;
        addActionListeners();
    }

    private void addActionListeners() {
        lf.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava(e);
            }

            private void prijava(ActionEvent e) {
                String ki = lf.getjTextField1().getText().trim();
                String pass = String.valueOf(lf.getjPasswordField1().getPassword());
                Komunikacija komunikacija = Komunikacija.getInstanca();
                komunikacija.konekcija();

                if (komunikacija.getPosiljalac() == null || komunikacija.getPrimalac() == null) {
                    JOptionPane.showMessageDialog(lf, "Nemoguće povezivanje sa serverom", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Zaposleni ulogovani = Komunikacija.getInstanca().login(ki, pass);
                if (ulogovani == null) {
                    JOptionPane.showMessageDialog(lf, "Korisnicko ime i sifra nisu ispravni", "GRESKA", JOptionPane.ERROR_MESSAGE);
                } else {
                    Cordinator.getInstanca().setUlogovani(ulogovani);
                    JOptionPane.showMessageDialog(lf, "Korisnicko ime i sifra su ispravni", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                    Cordinator.getInstanca().otvoriGlavnuFormu();
                    lf.dispose();
                }

                try {
                    Cordinator.getInstanca().otvoriGlavnuFormu();
                    lf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            lf,
                            "Ne moze da se otvori glavna forma ili meni",
                            "Greska",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }

        });
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }

}
