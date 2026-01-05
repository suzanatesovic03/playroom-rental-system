/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Iznajmljivanje implements ApstraktniDomenskiObjekat {

    private int idIznajmljivanje;
    private LocalDate datum;
    private Zaposleni zaposleni;
    private Klijent klijent;

    private int ukupnoSati;
    private double ukupanIznos;
    private ArrayList<StavkaIznajmljivanja> stavke = new ArrayList<>();

    public Iznajmljivanje() {
    }

    public Iznajmljivanje(int idIznajmljivanje, LocalDate datum,
        Zaposleni zaposleni, Klijent klijent,
        int ukupnoSati, double ukupanIznos) {
        this.idIznajmljivanje = idIznajmljivanje;
        this.datum = datum;
        this.zaposleni = zaposleni;
        this.klijent = klijent;
        this.ukupnoSati = ukupnoSati;
        this.ukupanIznos = ukupanIznos;
    }

    public int getIdIznajmljivanje() {
        return idIznajmljivanje;
    }

    public void setIdIznajmljivanje(int idIznajmljivanje) {
        this.idIznajmljivanje = idIznajmljivanje;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public int getUkupnoSati() {
        return ukupnoSati;
    }

    public void setUkupnoSati(int ukupnoSati) {
        this.ukupnoSati = ukupnoSati;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    @Override
    public String toString() {
        return "Iznajmljivanje{"
                + "idIznajmljivanje=" + idIznajmljivanje
                + ", datum=" + datum
                + ", zaposleni=" + zaposleni
                + ", klijent=" + klijent
                + ", ukupnoSati=" + ukupnoSati
                + ", ukupanIznos=" + ukupanIznos
                + '}';
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idIznajmljivanje);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Iznajmljivanje other)) {
            return false;
        }
        return this.idIznajmljivanje == other.idIznajmljivanje;
    }

    @Override
    public String vratiNazivTabele() {
        return "iznajmljivanje";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("i.idIznajmljivanje");
            LocalDate datum = rs.getDate("i.datum").toLocalDate();

            Klijent k = new Klijent();
            k.setIdKlijent(rs.getInt("k.idKlijent"));
            k.setImePrezime(rs.getString("k.imePrezime"));

            Zaposleni z = new Zaposleni();
            z.setIdZaposleni(rs.getInt("z.idZaposleni"));
            z.setImePrezime(rs.getString("z.imePrezime"));

            int sati = rs.getInt("i.ukupnoSati");
            double iznos = rs.getDouble("i.ukupanIznos");

            Iznajmljivanje i = new Iznajmljivanje(id, datum, z, k, sati, iznos);
            lista.add(i);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datum, zaposleni, klijent, ukupnoSati, ukupanIznos";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + datum + "',"
                + zaposleni.getIdZaposleni() + ","
                + klijent.getIdKlijent() + ","
                + ukupnoSati + ","
                + ukupanIznos;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "iznajmljivanje.idIznajmljivanje=" + idIznajmljivanje;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        int id = rs.getInt("i.idIznajmljivanje");
        LocalDate datum = rs.getDate("i.datum").toLocalDate();

        Klijent k = new Klijent();
        k.setIdKlijent(rs.getInt("k.idKlijent"));
        k.setImePrezime(rs.getString("k.imePrezime"));

        Zaposleni z = new Zaposleni();
        z.setIdZaposleni(rs.getInt("z.idZaposleni"));
        z.setImePrezime(rs.getString("z.imePrezime"));

        int sati = rs.getInt("i.ukupnoSati");
        double iznos = rs.getDouble("i.ukupanIznos");

        return new Iznajmljivanje(id, datum, z, k, sati, iznos);
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "datum='" + datum + "'"
                + ", zaposleni=" + zaposleni.getIdZaposleni()
                + ", klijent=" + klijent.getIdKlijent()
                + ", ukupnoSati=" + ukupnoSati
                + ", ukupanIznos=" + ukupanIznos;
    }

    public void setStavke(ArrayList<StavkaIznajmljivanja> stavke) {
        this.stavke = stavke;
    }

    public ArrayList<StavkaIznajmljivanja> getStavke() {
        return stavke;
    }

}
