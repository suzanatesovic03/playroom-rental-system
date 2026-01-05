/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author User
 */
public class ZapSmena implements ApstraktniDomenskiObjekat {

    private Zaposleni zap;
    private SmenaZaposlenog smena;
    private LocalDate datumRada;

    private Zaposleni stariZap;
    private SmenaZaposlenog staraSmena;
    private LocalDate stariDatumRada;

    public ZapSmena() {
    }

    public ZapSmena(Zaposleni zap, SmenaZaposlenog smena, LocalDate datumRada) {
        this.zap = zap;
        this.smena = smena;
        this.datumRada = datumRada;
    }

    public Zaposleni getStariZap() {
        return stariZap;
    }

    public void setStariZap(Zaposleni stariZap) {
        this.stariZap = stariZap;
    }

    public SmenaZaposlenog getStaraSmena() {
        return staraSmena;
    }

    public void setStaraSmena(SmenaZaposlenog staraSmena) {
        this.staraSmena = staraSmena;
    }

    public LocalDate getStariDatumRada() {
        return stariDatumRada;
    }

    public void setStariDatumRada(LocalDate stariDatumRada) {
        this.stariDatumRada = stariDatumRada;
    }

    public Zaposleni getZap() {
        return zap;
    }

    public void setZap(Zaposleni zap) {
        this.zap = zap;
    }

    public SmenaZaposlenog getSmena() {
        return smena;
    }

    public void setSmena(SmenaZaposlenog smena) {
        this.smena = smena;
    }

    public LocalDate getDatumRada() {
        return datumRada;
    }

    public void setDatumRada(LocalDate datumRada) {
        this.datumRada = datumRada;
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
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ZapSmena other = (ZapSmena) obj;
        if (!Objects.equals(this.zap, other.zap)) {
            return false;
        }
        if (!Objects.equals(this.smena, other.smena)) {
            return false;
        }
        return Objects.equals(this.datumRada, other.datumRada);
    }

    @Override
    public String toString() {
        return "ZapSmena{" + "zap=" + zap + ", smena=" + smena + ", datumRada=" + datumRada + '}';
    }

    @Override
    public String vratiNazivTabele() {
        return "zapsmena";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idZap = rs.getInt("zapsmena.zap");
            String imePrezime = rs.getString("z.imePrezime");

            int idSmena = rs.getInt("zapsmena.smena");
            LocalTime vremeOd = rs.getTime("s.vremeOd").toLocalTime();
            LocalTime vremeDo = rs.getTime("s.vremeDo").toLocalTime();

            LocalDate datum = rs.getDate("zapsmena.datumRada").toLocalDate();

            Zaposleni zap = new Zaposleni();
            zap.setIdZaposleni(idZap);
            zap.setImePrezime(imePrezime);

            SmenaZaposlenog smena = new SmenaZaposlenog(idSmena, vremeOd, vremeDo);

            ZapSmena zs = new ZapSmena(zap, smena, datum);
            lista.add(zs);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "zap, smena, datumRada";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return zap.getIdZaposleni() + "," + smena.getIdSmenaZaposlenog() + ",'" + datumRada + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        int idZap = (stariZap != null) ? stariZap.getIdZaposleni() : zap.getIdZaposleni();
        int idSmena = (staraSmena != null) ? staraSmena.getIdSmenaZaposlenog() : smena.getIdSmenaZaposlenog();
        LocalDate datum = (stariDatumRada != null) ? stariDatumRada : datumRada;

        return "zap=" + idZap + " AND smena=" + idSmena + " AND datumRada='" + datum + "'";
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "zap=" + zap.getIdZaposleni() + ", smena=" + smena.getIdSmenaZaposlenog() + ", datumRada='" + datumRada + "'";
    }

}
