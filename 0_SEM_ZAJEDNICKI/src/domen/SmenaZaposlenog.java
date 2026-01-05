/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class SmenaZaposlenog implements ApstraktniDomenskiObjekat {

    private int idSmenaZaposlenog;
    private LocalTime vremeOd;
    private LocalTime vremeDo;

    public SmenaZaposlenog() {
    }

    public SmenaZaposlenog(int idSmenaZaposlenog, LocalTime vremeOd, LocalTime vremeDo) {
        this.idSmenaZaposlenog = idSmenaZaposlenog;
        this.vremeOd = vremeOd;
        this.vremeDo = vremeDo;
    }

    public int getIdSmenaZaposlenog() {
        return idSmenaZaposlenog;
    }

    public void setIdSmenaZaposlenog(int idSmenaZaposlenog) {
        this.idSmenaZaposlenog = idSmenaZaposlenog;
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
        final SmenaZaposlenog other = (SmenaZaposlenog) obj;
        return this.idSmenaZaposlenog == other.idSmenaZaposlenog;
    }

    @Override
    public String toString() {
        return vremeOd + "-" + vremeDo;
    }

    @Override
    public String vratiNazivTabele() {
        return "smenazaposlenog";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idSmenaZaposlenog = rs.getInt("smenazaposlenog.idSmenaZaposlenog");
            LocalTime vremeOd = rs.getTime("smenazaposlenog.vremeOd").toLocalTime();
            LocalTime vremeDo = rs.getTime("smenazaposlenog.vremeDo").toLocalTime();
            SmenaZaposlenog s = new SmenaZaposlenog(idSmenaZaposlenog, vremeOd, vremeDo);
            lista.add(s);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "vremeOd, vremeDo";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + vremeOd + "','" + vremeDo + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "smenazaposlenog.idSmenaZaposlenog=" + idSmenaZaposlenog;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "vremeOd='" + vremeOd + "', vremeDo='" + vremeDo + "'";
    }

}
