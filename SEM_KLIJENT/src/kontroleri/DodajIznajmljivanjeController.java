/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import domen.Igraonica;
import domen.Iznajmljivanje;
import domen.Klijent;
import domen.Mesto;
import domen.StavkaIznajmljivanja;
import domen.Zaposleni;
import forme.DodajIznajmljivanjeForm;
import forme.FormaMod;
import forme.model.ModelTabeleStavkaIznajmljivanja;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import komunikacija.Komunikacija;

/**
 *
 * @author User
 */
public class DodajIznajmljivanjeController {

    private final DodajIznajmljivanjeForm f;
    private final List<StavkaIznajmljivanja> stavke = new ArrayList<>();
    private int ukupnoSati = 0;
    private double ukupanIznos = 0.0;

    private final DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
    private final DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");

    public DodajIznajmljivanjeController(DodajIznajmljivanjeForm f) {
        this.f = f;
        postaviFormu();
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        if (mod == FormaMod.DODAJ) {
            pripremiZaDodaj();
        } else {
            pripremiZaIzmenu();
        }
        f.setVisible(true);

    }

    private void postaviFormu() {
        f.getjLabelUkupnoSati().setText("0");
        f.getjLabelUkupnanIznos().setText("0.00");
        f.getjTextFieldUkupanIznosStavke().setEditable(false);

        ModelTabeleStavkaIznajmljivanja model = new ModelTabeleStavkaIznajmljivanja(stavke);
        f.getjTableListaStavki().setModel(model);
        f.getjTableListaStavki().removeColumn(f.getjTableListaStavki().getColumnModel().getColumn(0));

        try {
            for (Zaposleni z : Komunikacija.getInstanca().ucitajZaposlene()) {
                f.getjComboBoxZaposleni().addItem(z);
            }
            for (Klijent k : Komunikacija.getInstanca().ucitajKlijente()) {
                f.getjComboBoxKlijent().addItem(k);
            }
            for (Igraonica ig : Komunikacija.getInstanca().ucitajIgraonice()) {
                f.getjComboBoxIgraonice().addItem(ig);
            }
            String[] usluge = {
                "Organizacija rođendana",
                "Hrana i piće",
                "Muzika / DJ",
                "Animatori za decu",
                "Mađioničar / maskote",
                "PlayStation / Xbox kutak",
                "Stoni fudbal / stoni tenis",
                "Projektor i platno",
                "Fotograf / kamera",
                "Face painting",
                "Nema dodatne usluge"
            };

            for (String u : usluge) {
                f.getjComboBoxDodatnaUsluga().addItem(u);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(f, "Greska pri punjenju combo box-a.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
        f.getjComboBoxKlijent().setSelectedIndex(-1);
        f.getjComboBoxZaposleni().setSelectedIndex(-1);
        f.getjTextFieldDatum().setText("");

    }

    Igraonica i = null;

    private void addActionListener() {
        f.getjComboBoxIgraonice().addActionListener(e -> {
            Igraonica i = (Igraonica) f.getjComboBoxIgraonice().getSelectedItem();
            if (i != null) {
                f.getjTextFieldCenaSatIgraonica().setText(String.valueOf(i.getCenaSat()));
            } else {
                f.getjTextFieldCenaSatIgraonica().setText("");
            }
        });
        f.getjComboBoxDodatnaUsluga().addActionListener(e -> {
            String dodatnaUsluga = (String) f.getjComboBoxDodatnaUsluga().getSelectedItem();
            if (dodatnaUsluga != null) {
                double cena = 0.0;

                switch (dodatnaUsluga) {
                    case "Organizacija rođendana":
                        cena = 5000.0;
                        break;
                    case "Hrana i piće":
                        cena = 3000.0;
                        break;
                    case "Muzika / DJ":
                        cena = 4000.0;
                        break;
                    case "Animatori za decu":
                        cena = 3500.0;
                        break;
                    case "Mađioničar / maskote":
                        cena = 4500.0;
                        break;
                    case "PlayStation / Xbox kutak":
                        cena = 2000.0;
                        break;
                    case "Stoni fudbal / stoni tenis":
                        cena = 1500.0;
                        break;
                    case "Projektor i platno":
                        cena = 2500.0;
                        break;
                    case "Fotograf / kamera":
                        cena = 6000.0;
                        break;
                    case "Face painting":
                        cena = 1800.0;
                        break;
                    case "Nema dodatne usluge":
                        cena = 0;
                        break;
                    default:
                        cena = 0.0;
                        break;
                }
                f.getjTextFieldCenaDodatneUsluge().setText(String.format("%.2f", cena));
            }
        });
        f.getjButtonDodajStavku().addActionListener(e -> dodajStavku());
        f.getjButtonDodajIznajmljivanje().addActionListener(e -> sacuvajIznajmljivanje());
        f.izmeniAddActionListener(e -> azurirajIznajmljivanje());
        f.getjButtonObrisiStavku().addActionListener(e -> {
            int red = f.getjTableListaStavki().getSelectedRow();
            if (red >= 0) {
                ModelTabeleStavkaIznajmljivanja model = (ModelTabeleStavkaIznajmljivanja) f.getjTableListaStavki().getModel();
                model.obrisiStavku(red);
                izracunajSateIznos();
            } else {
                JOptionPane.showMessageDialog(null, "Izaberi stavku za brisanje");
            }
        });

        f.getjButtonKreirajIznajmljivanje().addActionListener(e -> {
            String datum = f.getjTextFieldDatum().getText().trim();
            Zaposleni z = (Zaposleni) f.getjComboBoxZaposleni().getSelectedItem();
            Klijent k = (Klijent) f.getjComboBoxKlijent().getSelectedItem();

            if (datum.isEmpty() || z == null || k == null) {
                JOptionPane.showMessageDialog(f,
                        "Sistem ne može da kreira iznajmljivanje",
                        "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(f,
                    "Sistem je kreirao iznajmljivanje",
                    "Uspešno", JOptionPane.INFORMATION_MESSAGE);
            f.getjComboBoxZaposleni().setEnabled(false);
            f.getjComboBoxKlijent().setEnabled(false);
            f.getjTextFieldDatum().setEnabled(false);
            f.getjButtonDodajIznajmljivanje().setEnabled(true);
        });
    }

    private void dodajStavku() {
        try {
            Object igObj = f.getjComboBoxIgraonice().getSelectedItem();
            if (!(igObj instanceof Igraonica ig)) {
                JOptionPane.showMessageDialog(f, "Izaberi igraonicu.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String datumStr = f.getjTextFieldDatumIznajmljivanja().getText().trim();
            String odStr = f.getjTextFieldVremeOd().getText().trim();
            String doStr = f.getjTextFieldVremeDo().getText().trim();

            if (datumStr.isEmpty() || odStr.isEmpty() || doStr.isEmpty()) {
                JOptionPane.showMessageDialog(f, "Unesi datum i vreme (od/do).", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LocalDate datum = LocalDate.parse(datumStr, df);
            LocalTime tOd = LocalTime.parse(odStr, tf);
            LocalTime tDo = LocalTime.parse(doStr, tf);

            if (!tDo.isAfter(tOd)) {
                JOptionPane.showMessageDialog(f, "Vreme do mora biti posle vremena od.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int sati = (int) Duration.between(tOd, tDo).toHours();
            if (sati <= 0) {
                JOptionPane.showMessageDialog(f, "Trajanje mora biti ceo broj sati > 0.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double cenaSat = validirajBroj(f.getjTextFieldCenaSatIgraonica().getText(), "Cena po satu");
            double cenaDodatne = procitajBroj(f.getjTextFieldCenaDodatneUsluge().getText());
            String dodatna = (String) f.getjComboBoxDodatnaUsluga().getSelectedItem();

            double ukStavke = sati * cenaSat + cenaDodatne;
            f.getjTextFieldUkupanIznosStavke().setText(String.format("%.2f", ukStavke));

            StavkaIznajmljivanja si = new StavkaIznajmljivanja();
            si.setIznjamljivanje(null);
            si.setIgraonica(ig);
            si.setDatumIznajmljivanja(datum);
            si.setVremeOd(tOd);
            si.setVremeDo(tDo);
            si.setCenaSat(cenaSat);
            si.setDodatnaUsluga(dodatna);
            si.setCenaDodatneUsluge(cenaDodatne);
            si.setUkupanIznosIgraonice(ukStavke);

            ModelTabeleStavkaIznajmljivanja model = (ModelTabeleStavkaIznajmljivanja) f.getjTableListaStavki().getModel();
            model.dodaj(si);

            izracunajSateIznos();
            f.getjButtonDodajIznajmljivanje().setEnabled(!model.getLista().isEmpty());
            ocistiPoljaStavke();

            JOptionPane.showMessageDialog(f, "Stavka dodata.", "Uspesno", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(f, "Proveri format datuma (dd.MM.yyyy.) i vremena (HH:mm).", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sacuvajIznajmljivanje() {
        try {
            ModelTabeleStavkaIznajmljivanja model
                    = (ModelTabeleStavkaIznajmljivanja) f.getjTableListaStavki().getModel();
            List<StavkaIznajmljivanja> uneteStavke = new ArrayList<>(model.getLista());
            if (uneteStavke.isEmpty()) {
                JOptionPane.showMessageDialog(f, "Sistem ne moze da zapamti iznajmljivanje.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Zaposleni z = (Zaposleni) f.getjComboBoxZaposleni().getSelectedItem();
            Klijent k = (Klijent) f.getjComboBoxKlijent().getSelectedItem();
            String datumPlacanjaStr = f.getjTextFieldDatum().getText().trim();
            if (z == null || k == null || datumPlacanjaStr.isEmpty()) {
                JOptionPane.showMessageDialog(f, "Izaberi zaposlenog, klijenta i unesi datum.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            LocalDate datumPlacanja = LocalDate.parse(datumPlacanjaStr, df);
            for (StavkaIznajmljivanja s : uneteStavke) {
                if (s.getVremeOd() != null && s.getVremeDo() != null) {
                    ukupnoSati += Math.max(0, (int) Duration.between(s.getVremeOd(), s.getVremeDo()).toHours());
                }
                ukupanIznos += s.getUkupanIznosIgraonice();
            }

            Iznajmljivanje iz = new Iznajmljivanje();
            iz.setDatum(datumPlacanja);
            iz.setZaposleni(z);
            iz.setKlijent(k);

            iz.setUkupanIznos(ukupanIznos);
            iz.setUkupnoSati(ukupnoSati);
            iz.setStavke((ArrayList<StavkaIznajmljivanja>) uneteStavke);
            Komunikacija.getInstanca().dodajIznajmljivanje(iz);
            JOptionPane.showMessageDialog(f, "Sistem je zapamtio iznajmljivanje.", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
            f.dispose();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(f, "Sistem ne može da zapamti iznajmljivanje.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void azurirajIznajmljivanje() {
        int id = Integer.parseInt(f.getjTextFieldId().getText().trim());
        String datum = f.getjTextFieldDatum().getText().trim();
        if (datum == null || datum.isEmpty()) {
            JOptionPane.showMessageDialog(f, "Sistem ne može da zapamti iznajmljivanje.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
        LocalDate datumPlacanja = LocalDate.parse(datum, df);
        Zaposleni z = (Zaposleni) f.getjComboBoxZaposleni().getSelectedItem();
        Klijent k = (Klijent) f.getjComboBoxKlijent().getSelectedItem();
        ModelTabeleStavkaIznajmljivanja model = (ModelTabeleStavkaIznajmljivanja) f.getjTableListaStavki().getModel();
        List<StavkaIznajmljivanja> stavkeIzmena = new ArrayList<>(model.getLista());
        if (stavkeIzmena.isEmpty()) {
            JOptionPane.showMessageDialog(f, "Sistem ne moze da zapamti iznajmljivanje.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int ukupnoSatiIzmena;
        double ukupanIznosIzmena;
        try {
            ukupnoSatiIzmena = Integer.parseInt(f.getjLabelUkupnoSati().getText().trim());

            ukupanIznosIzmena = Double.parseDouble(f.getjLabelUkupnanIznos().getText().trim().replace(',', '.'));
        } catch (NumberFormatException nfe) {
            ukupnoSatiIzmena = 0;
            ukupanIznosIzmena = 0.0;
            for (StavkaIznajmljivanja s : stavkeIzmena) {
                if (s.getVremeOd() != null && s.getVremeDo() != null) {
                    int sati = (int) java.time.Duration.between(s.getVremeOd(), s.getVremeDo()).toHours();
                    ukupnoSatiIzmena += Math.max(0, sati);
                }
                ukupanIznosIzmena += s.getUkupanIznosIgraonice();
            }
        }

        Iznajmljivanje iz = new Iznajmljivanje();
        iz.setIdIznajmljivanje(id);
        iz.setDatum(datumPlacanja);
        iz.setKlijent(k);
        iz.setZaposleni(z);
        iz.setUkupanIznos(ukupanIznosIzmena);
        iz.setUkupnoSati(ukupnoSatiIzmena);

        for (StavkaIznajmljivanja s : stavkeIzmena) {
            s.setIznjamljivanje(iz);

        }
        iz.setStavke((ArrayList<StavkaIznajmljivanja>) stavkeIzmena);
        try {
            Komunikacija.getInstanca().azurirajIznajmljivanje(iz);
            JOptionPane.showMessageDialog(f, "Sistem je zapamtio iznajmljivanje.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
            Cordinator.getInstanca().osveziFormuIznajmljivanje();
            f.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(f, "Sistem ne moze da zapamti iznajmljivanje.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void izracunajSateIznos() {
        ModelTabeleStavkaIznajmljivanja model
                = (ModelTabeleStavkaIznajmljivanja) f.getjTableListaStavki().getModel();

        int sati = 0;
        double iznos = 0.0;
        for (StavkaIznajmljivanja s : model.getLista()) {
            if (s.getVremeOd() != null && s.getVremeDo() != null) {
                sati += Math.max(0, (int) Duration.between(s.getVremeOd(), s.getVremeDo()).toHours());
            }
            iznos += s.getUkupanIznosIgraonice();
        }
        f.getjLabelUkupnoSati().setText(String.valueOf(sati));
        f.getjLabelUkupnanIznos().setText(String.format("%.2f", iznos));
    }

    private void ocistiPoljaStavke() {
        f.getjTextFieldDatumIznajmljivanja().setText("");
        f.getjTextFieldVremeOd().setText("");
        f.getjTextFieldVremeDo().setText("");
        f.getjComboBoxDodatnaUsluga().setSelectedIndex(-1);
        f.getjTextFieldCenaDodatneUsluge().setText("");
        f.getjTextFieldCenaSatIgraonica().setText("");
        f.getjTextFieldUkupanIznosStavke().setText("");
        f.getjComboBoxIgraonice().setSelectedIndex(-1);
    }

    private double validirajBroj(String s, String label) {
        try {
            return Double.parseDouble(s.trim());
        } catch (Exception e) {
            throw new IllegalArgumentException(label + " mora biti broj.");
        }
    }

    private double procitajBroj(String s) {
        if (s == null || s.trim().isEmpty()) {
            return 0.0;
        }
        return Double.parseDouble(s.trim());
    }

    private void pripremiZaDodaj() {
        f.getjButtonDodajIznajmljivanje().setVisible(true);
        f.getjButtonAzuriraj().setVisible(false);
        f.getjButtonObrisiStavku().setEnabled(false);
        f.getjTextFieldId().setEditable(false);
        f.getjTextFieldId().setEnabled(false);
        f.getjButtonObrisiStavku().setVisible(false);

        f.getjTextFieldId().setText("");
        f.getjTextFieldDatum().setText("");

        ModelTabeleStavkaIznajmljivanja model = new ModelTabeleStavkaIznajmljivanja(new ArrayList<>());
        f.getjTableListaStavki().setModel(model);
        if (f.getjTableListaStavki().getColumnModel().getColumnCount() > 0) {
            f.getjTableListaStavki().removeColumn(f.getjTableListaStavki().getColumnModel().getColumn(0));
        }
        izracunajSateIznos();
        ocistiPoljaStavke();
    }

    private void pripremiZaIzmenu() {
        f.getjButtonDodajIznajmljivanje().setVisible(false);
        f.getjButtonKreirajIznajmljivanje().setVisible(false);
        f.getjButtonAzuriraj().setVisible(true);
        f.getjTextFieldId().setEditable(false);
        f.getjTextFieldId().setEnabled(false);
        f.getjComboBoxZaposleni().setEnabled(false);
        f.getjComboBoxKlijent().setEnabled(false);
        f.getjButtonObrisiStavku().setEnabled(true);

        Iznajmljivanje iz = (Iznajmljivanje) Cordinator.getInstanca().vratiParam("iznajmljivanje");
        if (iz == null) {
            JOptionPane.showMessageDialog(f, "Nije prosleđeno iznajmljivanje za izmenu.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        f.getjTextFieldId().setText(String.valueOf(iz.getIdIznajmljivanje()));
        f.getjTextFieldDatum().setText(iz.getDatum() != null ? iz.getDatum().format(df) : "");
        f.getjComboBoxZaposleni().setSelectedItem(iz.getZaposleni());
        f.getjComboBoxKlijent().setSelectedItem(iz.getKlijent());
        List<StavkaIznajmljivanja> lista = iz.getStavke() != null ? new ArrayList<>(iz.getStavke()) : new ArrayList<>();
        List<Igraonica> sveIgraonice = komunikacija.Komunikacija.getInstanca().ucitajIgraonice();
        for (StavkaIznajmljivanja s : lista) {
            if (s.getIgraonica() != null) {
                int id = s.getIgraonica().getIdIgraonica();
                for (Igraonica ig : sveIgraonice) {
                    if (ig.getIdIgraonica() == id) {
                        s.setIgraonica(ig);
                        break;
                    }
                }
            }
        }
        ModelTabeleStavkaIznajmljivanja model = new ModelTabeleStavkaIznajmljivanja(lista);
        f.getjTableListaStavki().setModel(model);

        if (f.getjTableListaStavki().getColumnModel().getColumnCount() > 0) {
            f.getjTableListaStavki().removeColumn(f.getjTableListaStavki().getColumnModel().getColumn(0));
        }

        izracunajSateIznos();
        ocistiPoljaStavke();
    }

}
