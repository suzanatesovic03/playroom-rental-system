/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Igraonica;
import domen.Klijent;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author User
 */
public class ModelTabeleIgraonica extends AbstractTableModel {

    List<Igraonica> lista;
    String[] kolone = {"Id", "Naziv", "Lokacija", "Tip igraonice", "Cena sat"};

    public ModelTabeleIgraonica(List<Igraonica> lista) {
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
        Igraonica i = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return i.getIdIgraonica();
            case 1:
                return i.getNaziv();
            case 2:
                return i.getLokacija();
            case 3:
                return i.getTipIgraonice();
            case 4:
                return i.getCenaSat();
            default:
                return "NA";
        }
    }

    public List<Igraonica> getLista() {
        return lista;
    }

    public void pretrazi(String naziv, String lokacija, String tip, Double cena) {
        final double EPSILON = 0.0001;

    List<Igraonica> filtrirano = lista.stream()
            .filter(i -> naziv == null || naziv.isEmpty() || i.getNaziv().toLowerCase().contains(naziv.toLowerCase()))
            .filter(i -> lokacija == null || lokacija.isEmpty() || i.getLokacija().toLowerCase().contains(lokacija.toLowerCase()))
            .filter(i -> tip == null || tip.isEmpty() || i.getTipIgraonice().toLowerCase().contains(tip.toLowerCase()))
            .filter(i -> cena == null || Math.abs(i.getCenaSat() - cena) < EPSILON)
            .collect(Collectors.toList());

    this.lista = filtrirano;
    fireTableDataChanged();
    }

}
