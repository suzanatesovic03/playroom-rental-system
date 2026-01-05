/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class StavkaIznajmljivanja implements ApstraktniDomenskiObjekat {
    private int rb;
    private double cenaSat;
    private LocalDate datumIznajmljivanja;
    private LocalTime vremeOd;
    private LocalTime vremeDo;
    private String dodatnaUsluga;
    private double cenaDodatneUsluge;
    private double ukupanIznosIgraonice;
    private Igraonica igraonica;
    private Iznajmljivanje iznajmljivanje;

    public StavkaIznajmljivanja() {
    }

    public StavkaIznajmljivanja(int rb, double cenaSat, LocalDate datumIznajmljivanja,
            LocalTime vremeOd, LocalTime vremeDo, String dodatnaUsluga, double cenaDodatneUsluge,
            double ukupanIznosIgraonice, Igraonica igraonica) {
        this.rb = rb;
        this.cenaSat = cenaSat;
        this.datumIznajmljivanja = datumIznajmljivanja;
        this.vremeOd = vremeOd;
        this.vremeDo = vremeDo;
        this.dodatnaUsluga = dodatnaUsluga;
        this.cenaDodatneUsluge = cenaDodatneUsluge;
        this.ukupanIznosIgraonice = ukupanIznosIgraonice;
        this.igraonica = igraonica;
    }

    public StavkaIznajmljivanja(Iznajmljivanje iznajmljivanje) {
        this.iznajmljivanje = iznajmljivanje;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public double getCenaSat() {
        return cenaSat;
    }

    public void setCenaSat(double cenaSat) {
        this.cenaSat = cenaSat;
    }

    public LocalDate getDatumIznajmljivanja() {
        return datumIznajmljivanja;
    }

    public void setDatumIznajmljivanja(LocalDate datumIznajmljivanja) {
        this.datumIznajmljivanja = datumIznajmljivanja;
    }

    public LocalTime getVremeOd() {
        return vremeOd;
    }

    public void setVremeOd(LocalTime vremeOd) {
        this.vremeOd = vremeOd;
    }

    public LocalTime getVremeDo() {
        return vremeDo;
    }

    public void setVremeDo(LocalTime vremeDo) {
        this.vremeDo = vremeDo;
    }

    public int getBrojSati() {
        if (vremeOd != null && vremeDo != null) {
            return (int) Duration.between(vremeOd, vremeDo).toHours();
        }
        return 0;
    }

    public String getDodatnaUsluga() {
        return dodatnaUsluga;
    }

    public void setDodatnaUsluga(String dodatnaUsluga) {
        this.dodatnaUsluga = dodatnaUsluga;
    }

    public double getCenaDodatneUsluge() {
        return cenaDodatneUsluge;
    }

    public void setCenaDodatneUsluge(double cenaDodatneUsluge) {
        this.cenaDodatneUsluge = cenaDodatneUsluge;
    }

    public double getUkupanIznosIgraonice() {
        return ukupanIznosIgraonice;
    }

    public void setUkupanIznosIgraonice(double ukupanIznosIgraonice) {
        this.ukupanIznosIgraonice = ukupanIznosIgraonice;
    }

    public void izracunajUkupanIznos() {
        this.ukupanIznosIgraonice = (getBrojSati() * cenaSat) + cenaDodatneUsluge;
    }

    public Igraonica getIgraonica() {
        return igraonica;
    }

    public void setIgraonica(Igraonica igraonica) {
        this.igraonica = igraonica;
    }

    public Iznajmljivanje getIznjamljivanje() {
        return iznajmljivanje;
    }

    public void setIznjamljivanje(Iznajmljivanje iznajmljivanje) {
        this.iznajmljivanje = iznajmljivanje;
    }

    @Override
    public String toString() {
        return "StavkaIznajmljivanja{"
                + "rb=" + rb
                + ", datumIznajmljivanja=" + datumIznajmljivanja
                + ", vremeOd=" + vremeOd
                + ", vremeDo=" + vremeDo
                + ", ukupanIznosIgraonice=" + ukupanIznosIgraonice
                + ", igraonica=" + igraonica
                + '}';
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(rb);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        StavkaIznajmljivanja other = (StavkaIznajmljivanja) obj;
        return this.rb == other.rb;
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkaiznajmljivanja";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int rb = rs.getInt("rb");
            double cenaSat = rs.getDouble("stavkaiznajmljivanja.cenaSat");
            LocalDate datum = rs.getDate("stavkaiznajmljivanja.datumIznajmljivanja").toLocalDate();
            LocalTime vremeOd = rs.getTime("stavkaiznajmljivanja.vremeOd").toLocalTime();
            LocalTime vremeDo = rs.getTime("stavkaiznajmljivanja.vremeDo").toLocalTime();
            String dodatnaUsluga = rs.getString("stavkaiznajmljivanja.dodatnaUsluga");
            double cenaDodatneUsluge = rs.getDouble("stavkaiznajmljivanja.cenaDodatneUsluge");
            double ukupanIznos = rs.getDouble("stavkaiznajmljivanja.ukupanIznosIgraonice");

            int idIgraonica = rs.getInt("igraonica");

            Igraonica ig = new Igraonica();
            ig.setIdIgraonica(idIgraonica);

            StavkaIznajmljivanja s = new StavkaIznajmljivanja(
                    rb, cenaSat, datum, vremeOd, vremeDo,
                    dodatnaUsluga, cenaDodatneUsluge, ukupanIznos, ig
            );
            lista.add(s);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "iznajmljivanje, datumIznajmljivanja, cenaSat, vremeOd, vremeDo, dodatnaUsluga, cenaDodatneUsluge, ukupanIznosIgraonice, igraonica";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return iznajmljivanje.getIdIznajmljivanje() + ",'"
                + datumIznajmljivanja + "',"
                + cenaSat + ",'"
                + vremeOd + "','"
                + vremeDo + "','"
                + dodatnaUsluga + "',"
                + cenaDodatneUsluge + ","
                + ukupanIznosIgraonice + ","
                + igraonica.getIdIgraonica();
    }

    @Override
    public String vratiPrimarniKljuc() {
        if (iznajmljivanje != null && rb == 0) {
            return "iznajmljivanje = " + iznajmljivanje.getIdIznajmljivanje();
        } else {
            return "rb = " + rb;
        }
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "datumIznajmljivanja = '" + datumIznajmljivanja + "'"
                + ", cenaSat = " + cenaSat
                + ", vremeOd = '" + vremeOd + "'"
                + ", vremeDo = '" + vremeDo + "'"
                + ", dodatnaUsluga = '" + dodatnaUsluga + "'"
                + ", cenaDodatneUsluge = " + cenaDodatneUsluge
                + ", ukupanIznosIgraonice = " + ukupanIznosIgraonice
                + ", igraonica = " + igraonica.getIdIgraonica();
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
