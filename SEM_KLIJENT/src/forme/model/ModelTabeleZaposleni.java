/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Zaposleni;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author User
 */
public class ModelTabeleZaposleni extends AbstractTableModel {

    private List<Zaposleni> lista;
    private final String[] kolone = {"ID", "Ime i prezime", "Email", "Adresa"};

    public ModelTabeleZaposleni(List<Zaposleni> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Zaposleni z = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return z.getIdZaposleni();
            case 1:
                return z.getImePrezime();
            case 2:
                return z.getEmail();
            case 3:
                return z.getAdresa();
            default:
                return "NA";
        }
    }

    public List<Zaposleni> getLista() {
        return lista;
    }

    public void pretrazi(String ime, String prezime, String email, String adresa) {
        List<Zaposleni> filteredList = lista.stream()
                .filter(z -> (ime == null || ime.isEmpty()) || z.getImePrezime().toLowerCase().contains(ime.toLowerCase()))
                .filter(z -> (prezime == null || prezime.isEmpty()) || z.getImePrezime().toLowerCase().contains(prezime.toLowerCase()))
                .filter(z -> (email == null || email.isEmpty()) || z.getEmail().toLowerCase().contains(email.toLowerCase()))
                .filter(z -> (adresa == null || adresa.isEmpty()) || z.getAdresa().toLowerCase().contains(adresa.toLowerCase()))
                .collect(Collectors.toList());
        this.lista = filteredList;
        fireTableDataChanged();
    }

}
