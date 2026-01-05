/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Mesto;
import domen.SmenaZaposlenog;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author User
 */
public class ModelTabeleSmeneZaposlenog extends AbstractTableModel {

    List<SmenaZaposlenog> lista;
    String[] kolone = { "Vreme od", "Vreme do"};

    public ModelTabeleSmeneZaposlenog(List<SmenaZaposlenog> lista) {
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
        SmenaZaposlenog s = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return s.getVremeOd();
            case 1:
                return s.getVremeDo();
            default:
                return "NA";
        }
    }

    public List<SmenaZaposlenog> getLista() {
        return lista;
    }

}
