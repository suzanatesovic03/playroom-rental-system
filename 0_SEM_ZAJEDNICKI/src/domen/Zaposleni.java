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
public class Zaposleni implements ApstraktniDomenskiObjekat {

    private int idZaposleni;
    private String imePrezime;
    private String email;
    private String adresa;
    private String username;
    private String password;

    public Zaposleni() {
    }

    public Zaposleni(int idZaposleni, String imePrezime, String email, String adresa, String username, String password) {
        this.idZaposleni = idZaposleni;
        this.imePrezime = imePrezime;
        this.email = email;
        this.adresa = adresa;
        this.username = username;
        this.password = password;
    }

    public int getIdZaposleni() {
        return idZaposleni;
    }

    public void setIdZaposleni(int idZaposleni) {
        this.idZaposleni = idZaposleni;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return imePrezime;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Zaposleni other = (Zaposleni) obj;

        // Ako ID postoji (nije 0), koristi ID (za prikaz u ComboBoxu, tabele itd.)
        if (this.idZaposleni != 0 && other.idZaposleni != 0) {
            return this.idZaposleni == other.idZaposleni;
        }

        // Ako je login u toku i ID još nije dodeljen, koristi username i password
        return Objects.equals(this.username, other.username)
                && Objects.equals(this.password, other.password);
    }

    @Override
    public String vratiNazivTabele() {
        return "zaposleni";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idZaposleni = rs.getInt("zaposleni.idZaposleni");
            String imePrezime = rs.getString("zaposleni.imePrezime");
            String email = rs.getString("zaposleni.email");
            String adresa = rs.getString("zaposleni.adresa");
            String username = rs.getString("zaposleni.username");
            String password = rs.getString("zaposleni.password");
            Zaposleni z = new Zaposleni(idZaposleni, imePrezime, email, adresa, username, password);
            lista.add(z);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "imePrezime, email, adresa, username, password";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + imePrezime + "','" + email + "','" + adresa + "','" + username + "','" + password + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "zaposleni.idZaposleni=" + idZaposleni;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "imePrezime='" + imePrezime + "', email='" + email + "', adresa='" + adresa + "', username='" + username + "', password='" + password + "'";
    }

}
