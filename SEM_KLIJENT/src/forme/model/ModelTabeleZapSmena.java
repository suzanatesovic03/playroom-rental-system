/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Mesto;
import domen.ZapSmena;
import domen.Zaposleni;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author User
 */
public class ModelTabeleZapSmena extends AbstractTableModel {

    private List<ZapSmena> lista;
    private final String[] kolone = {"Zaposleni", "Smena", "Datum rada"};

    public ModelTabeleZapSmena(List<ZapSmena> lista){
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
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy.");

        ZapSmena zs = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return zs.getZap().getImePrezime();
            case 1:
                return zs.getSmena().getVremeOd()+"-"+zs.getSmena().getVremeDo();
            case 2:
                return zs.getDatumRada().format(df);
            default:
                return "NA";
        }
    }

    public List<ZapSmena> getLista() {
        return lista;
    }

    public void pretrazi(String naziv) {
        if (naziv == null || naziv.isEmpty()) {
            return;
        }
        List<ZapSmena> rezultat = lista.stream()
                .filter(z -> z.getZap().getImePrezime().toLowerCase().contains(naziv.toLowerCase()))
                .collect(Collectors.toList());
        lista = rezultat;
        fireTableDataChanged();
    }
    
}
