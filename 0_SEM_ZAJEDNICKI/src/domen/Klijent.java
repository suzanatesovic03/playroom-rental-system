/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author User
 */
public class Klijent implements ApstraktniDomenskiObjekat{
    private int idKlijent;
    private String imePrezime;
    private String kontaktTelefon;
    private String email; 
    private Mesto mesto;

    public Klijent() {
    }

    public Klijent(int idKlijent, String imePrezime, String kontaktTelefon, String email, Mesto mesto) {
        this.idKlijent = idKlijent;
        this.imePrezime = imePrezime;
        this.kontaktTelefon = kontaktTelefon;
        this.email = email;
        this.mesto = mesto;
    }

    public int getIdKlijent() {
        return idKlijent;
    }

    public void setIdKlijent(int idKlijent) {
        this.idKlijent = idKlijent;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getKontaktTelefon() {
        return kontaktTelefon;
    }

    public void setKontaktTelefon(String kontaktTelefon) {
        this.kontaktTelefon = kontaktTelefon;
    }

    public String getEmail() { 
        return email;
    }

    public void setEmail(String email) { 
        this.email = email;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKlijent, imePrezime);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Klijent other = (Klijent) obj;
        return idKlijent == other.idKlijent &&
               Objects.equals(imePrezime, other.imePrezime);
    }

    @Override
    public String toString() {
        return imePrezime;
    }

    @Override
    public String vratiNazivTabele() {
        return "klijent";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idKlijent = rs.getInt("klijent.idKlijent");
            String imePrezime = rs.getString("klijent.imePrezime");
            String kontaktTelefon = rs.getString("klijent.kontaktTelefon");
            String email = rs.getString("klijent.email");

            int idMesto = rs.getInt("idMesto");
            String naziv = rs.getString("naziv");
            Mesto m = new Mesto(idMesto, naziv);

            Klijent k = new Klijent(idKlijent, imePrezime, kontaktTelefon, email, m);
            lista.add(k);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "imePrezime, kontaktTelefon, email, mesto";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + imePrezime + "','" + kontaktTelefon + "','" + email + "'," + mesto.getIdMesto();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "klijent.idKlijent=" + idKlijent;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "imePrezime='" + imePrezime +
               "', kontaktTelefon='" + kontaktTelefon +
               "', email='" + email +
               "', mesto=" + mesto.getIdMesto();
    }
}
