/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Iznajmljivanje;
import domen.Klijent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author User
 */
public class ModelTabeleIznajmljivanje extends AbstractTableModel {

    List<Iznajmljivanje> lista=new ArrayList<>();
    String[] kolone = {"id", "datum", "klijent", "zaposleni","ukupno sati", "ukupan iznos"};

    public ModelTabeleIznajmljivanje(List<Iznajmljivanje> lista) {
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
        Iznajmljivanje i = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return i.getIdIznajmljivanje();
            case 1:
                return i.getDatum();
            case 2:
                return i.getKlijent() != null ? i.getKlijent().getImePrezime() : "Nepoznat klijent";
            case 3:
                return i.getZaposleni() != null ? i.getZaposleni().getImePrezime() : "Nepoznat zaposleni";
            case 4:
                return i.getUkupnoSati();
            case 5:
                return i.getUkupanIznos();
            default:
                return "NA";
        }
    }

    public List<Iznajmljivanje> getLista() {
        return lista;
    }

    public void setLista(List<Iznajmljivanje> lista) {
        this.lista = lista;
    }

    public void pretrazi(String klijent, String zaposleni, LocalDate datum) {
         List<Iznajmljivanje> filteredList = lista.stream()
                .filter(i -> (klijent == null || klijent.isEmpty()) || i.getKlijent().getImePrezime().toLowerCase().contains(klijent.toLowerCase()))
                .filter(i -> (zaposleni == null || zaposleni.isEmpty()) || i.getZaposleni().getImePrezime().toLowerCase().contains(zaposleni.toLowerCase()))
                .filter(i -> (datum == null ) || i.getDatum().equals(datum))
                .collect(Collectors.toList());
        this.lista=filteredList;
        fireTableDataChanged();
    }

}
