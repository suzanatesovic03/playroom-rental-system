/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import domen.Mesto;
import domen.SmenaZaposlenog;
import domen.ZapSmena;
import domen.Zaposleni;
import forme.DodajZapSmenaForma;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author User
 */
public class DodajZapSmenaController {

    private final DodajZapSmenaForma dzsf;

    public DodajZapSmenaController(DodajZapSmenaForma dzsf) {
        this.dzsf = dzsf;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dzsf.setVisible(true);
    }

    private void pripremiFormu(FormaMod mod) {
        for (Zaposleni z : komunikacija.Komunikacija.getInstanca().ucitajZaposlene()) {
            dzsf.getjComboBoxZaposleni().addItem(z);
        }

        for (SmenaZaposlenog s : komunikacija.Komunikacija.getInstanca().ucitajSmene()) {
            dzsf.getjComboBoxSmena().addItem(s);
        }

        switch (mod) {
            case DODAJ:
                dzsf.getjButtonAzuriraj().setVisible(false);
                dzsf.getjButtonDodaj().setVisible(true);
                dzsf.getjButtonDodaj().setEnabled(true);
                break;
            case IZMENI:
                dzsf.getjButtonDodaj().setVisible(false);
                dzsf.getjButtonAzuriraj().setVisible(true);
                dzsf.getjButtonAzuriraj().setEnabled(true);

                ZapSmena zs = (ZapSmena) Cordinator.getInstanca().vratiParam("zapsmena");

                dzsf.getjComboBoxZaposleni().setSelectedItem(zs.getZap());
                dzsf.getjComboBoxSmena().setSelectedItem(zs.getSmena());
                DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
                dzsf.getjTextFieldDatumRada().setText(zs.getDatumRada().format(df));

                dzsf.setStariZap(zs.getZap());
                dzsf.setStaraSmena(zs.getSmena());
                dzsf.setStariDatumRada(zs.getDatumRada());
                break;
            default:
                throw new AssertionError();
        }
    }

    private void addActionListener() {
        dzsf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                try {
                    Zaposleni zap = (Zaposleni) dzsf.getjComboBoxZaposleni().getSelectedItem();
                    SmenaZaposlenog smena = (SmenaZaposlenog) dzsf.getjComboBoxSmena().getSelectedItem();
                    String datumText = dzsf.getjTextFieldDatumRada().getText().trim();
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
                    LocalDate datumRada = LocalDate.parse(datumText, df);

                    ZapSmena zapSmena = new ZapSmena(zap, smena, datumRada);
                    komunikacija.Komunikacija.getInstanca().dodajZapSmenu(zapSmena);

                    JOptionPane.showMessageDialog(dzsf, "Uspešno dodata smena zaposlenom.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    dzsf.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(dzsf, "Greška prilikom dodavanja smene.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        dzsf.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                try {
                    Zaposleni noviZap = (Zaposleni) dzsf.getjComboBoxZaposleni().getSelectedItem();
                    SmenaZaposlenog novaSmena = (SmenaZaposlenog) dzsf.getjComboBoxSmena().getSelectedItem();
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
                    LocalDate noviDatum = LocalDate.parse(dzsf.getjTextFieldDatumRada().getText().trim(), df);

                    ZapSmena novaZapSmena = new ZapSmena(noviZap, novaSmena, noviDatum);

                    novaZapSmena.setStariZap(dzsf.getStariZap());
                    novaZapSmena.setStaraSmena(dzsf.getStaraSmena());
                    novaZapSmena.setStariDatumRada(dzsf.getStariDatumRada());

                    Komunikacija.getInstanca().azurirajZapSmena(novaZapSmena);
                    JOptionPane.showMessageDialog(dzsf, "Uspešno ažuriranje dodeljene smene", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    dzsf.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(dzsf, "GREŠKA pri ažuriranju dodeljene smene", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

}
