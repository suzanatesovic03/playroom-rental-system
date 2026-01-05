/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Klijent;
import domen.Mesto;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author User
 */
public class ModelTabeleMesto extends AbstractTableModel {

    List<Mesto> lista;
    String[] kolone = {"idMesto", "naziv"};

    public ModelTabeleMesto(List<Mesto> lista) {
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
        Mesto m = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return m.getIdMesto();
            case 1:
                return m.getNaziv();
            default:
                return "NA";
        }
    }

    public List<Mesto> getLista() {
        return lista;
    }

    public void pretrazi(String naziv) {
        if (naziv == null || naziv.isEmpty()) {
            return;
        }
        List<Mesto> rezultat = lista.stream()
                .filter(m -> m.getNaziv().toLowerCase().contains(naziv.toLowerCase()))
                .collect(Collectors.toList());
        lista = rezultat;
        fireTableDataChanged();
    }

}
