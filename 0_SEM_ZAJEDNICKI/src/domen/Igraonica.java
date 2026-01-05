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
public class Igraonica implements ApstraktniDomenskiObjekat {
    private int idIgraonica;
    private String naziv;
    private String lokacija;
    private String tipIgraonice;
    private double cenaSat;

    public Igraonica() {
    }
    
    public Igraonica(int idIgraonica){
        this.idIgraonica=idIgraonica;
    }

    public Igraonica(int idIgraonica, String naziv, String lokacija, String tipIgraonice, double cenaSat) {
        this.idIgraonica = idIgraonica;
        this.naziv = naziv;
        this.lokacija = lokacija;
        this.tipIgraonice = tipIgraonice;
        this.cenaSat = cenaSat;
    }

    public int getIdIgraonica() {
        return idIgraonica;
    }

    public void setIdIgraonica(int idIgraonica) {
        this.idIgraonica = idIgraonica;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public String getTipIgraonice() {
        return tipIgraonice;
    }

    public void setTipIgraonice(String tipIgraonice) {
        this.tipIgraonice = tipIgraonice;
    }

    public double getCenaSat() {
        return cenaSat;
    }

    public void setCenaSat(double cenaSat) {
        this.cenaSat = cenaSat;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Igraonica other = (Igraonica) obj;
        if (this.idIgraonica != other.idIgraonica) {
            return false;
        }
        return Objects.equals(this.naziv, other.naziv);
    }

    @Override
    public String toString() {
        return naziv ;
    }

    @Override
    public String vratiNazivTabele() {
        return "igraonica";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int idIgraonica = rs.getInt("igraonica.idIgraonica");
            String naziv = rs.getString("igraonica.naziv");
            String lokacija = rs.getString("igraonica.lokacija");
            String tipIgraonice = rs.getString("igraonica.tipIgraonice");
            double cenaSat = rs.getDouble("igraonica.cenaSat");
            
            Igraonica i = new Igraonica(idIgraonica, naziv, lokacija, tipIgraonice, cenaSat);
            lista.add(i);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv, lokacija, tipIgraonice, cenaSat";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+naziv+"','"+lokacija+"','"+tipIgraonice+"',"+cenaSat;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "igraonica.idIgraonica="+idIgraonica;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='"+naziv+"', lokacija='"+lokacija+"', tipIgraonice='"+tipIgraonice+"', cenaSat="+cenaSat;
    }
    
    
}
