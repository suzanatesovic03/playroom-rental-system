/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Zaposleni;
import forme.PrikazZaposlenihForma;
import forme.model.ModelTabeleKlijent;
import forme.model.ModelTabeleZaposleni;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author User
 */
public class PrikazZaposlenihController {

    private final PrikazZaposlenihForma pzf;

    public PrikazZaposlenihController(PrikazZaposlenihForma pzf) {
        this.pzf = pzf;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pzf.setVisible(true);
    }

    public void pripremiFormu() {
        List<Zaposleni> zaposleni = komunikacija.Komunikacija.getInstanca().ucitajZaposlene();
        ModelTabeleZaposleni mtz = new ModelTabeleZaposleni(zaposleni);
        pzf.getjTableZaposleni().setModel(mtz);
    }

    private void addActionListener() {
        pzf.addBtnObrisiListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pzf.getjTableZaposleni().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pzf, "Sistem ne može da obriše zaposlenog", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleZaposleni mtz = (ModelTabeleZaposleni) pzf.getjTableZaposleni().getModel();
                    Zaposleni z = mtz.getLista().get(red);
                    try {
                        Komunikacija.getInstanca().obrisiZaposlenog(z);
                        JOptionPane.showMessageDialog(pzf, "Sistem je uspešno obrisao zaposlenog", "USPEŠNO", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    } catch (Exception ex) {
                        String poruka = ex.getMessage();
                        if (poruka == null || poruka.isBlank()) {
                            poruka = "Brisanje nije dozvoljeno: postoje povezana iznajmljivanja.";
                        }
                        JOptionPane.showMessageDialog(
                                pzf,
                                poruka, 
                                "Greška",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }
        });
        pzf.addBtnAzurirajListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pzf.getjTableZaposleni().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pzf, "Sistem ne može da ažurira zaposlenog", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleZaposleni mtz = (ModelTabeleZaposleni) pzf.getjTableZaposleni().getModel();
                    Zaposleni z = mtz.getLista().get(red);
                    cordinator.Cordinator.getInstanca().dodajParam("zaposleni", z);
                    cordinator.Cordinator.getInstanca().otvoriIzmeniZaposlenogFormu();
                }
            }
        });
        pzf.addBtnPretraziListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = pzf.getjTextFieldIme().getText().trim();
                String prezime = pzf.getjTextFieldPrezime().getText().trim();
                String email = pzf.getjTextFieldEmail().getText().trim();
                String adresa = pzf.getjTextFieldAdresa().getText().trim();
                ModelTabeleZaposleni mtz = (ModelTabeleZaposleni) pzf.getjTableZaposleni().getModel();
                mtz.pretrazi(ime, prezime, email, adresa);
            }
        });
        pzf.addBtnResetujPretraguListener(new ActionListener() {
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
