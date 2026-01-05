/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.iznajmljivanja;

import domen.Iznajmljivanje;
import domen.StavkaIznajmljivanja;
import operacija.ApstraktnaGenerickaOperacija;
import java.sql.*;
import java.time.LocalDate;
import repository.db.DbConnectionFactory;

/**
 *
 * @author User
 */
public class DodajIznajmljivanjeSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Iznajmljivanje)) {
            throw new Exception("Sistem ne moze da kreira iznajmljivanje");
        }
        Iznajmljivanje i = (Iznajmljivanje) param;
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Iznajmljivanje i = (Iznajmljivanje) param;
        broker.add(i);

        try ( Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();  ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID() AS id")) {
            if (rs.next()) {
                i.setIdIznajmljivanje(rs.getInt("id"));
            } else {
                throw new RuntimeException("Nije moguće pročitati LAST_INSERT_ID().");
            }
        }

        if (i.getStavke() != null) {
            for (StavkaIznajmljivanja s : i.getStavke()) {
                s.setIznjamljivanje(i);
                broker.add(s);
            }
        }

    }

}
