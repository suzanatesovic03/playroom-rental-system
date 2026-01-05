/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Klijent;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author User
 */
public class ModelTabeleKlijent extends AbstractTableModel {

    List<Klijent> lista;
    String[] kolone = {"idKlijent", "imePrezime","kontaktTelefon","email","mesto"};
    
    public ModelTabeleKlijent(List<Klijent> lista){
        this.lista=lista;
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
        Klijent k = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return k.getIdKlijent();
            case 1: return k.getImePrezime();
            case 2: return k.getKontaktTelefon();
            case 3: return k.getEmail();
            case 4: return k.getMesto().getNaziv();
            default: return "NA";
        }
    }

    public List<Klijent> getLista() {
        return lista;
    }

    public void pretrazi(String ime, String mesto) {
        List<Klijent> filteredList = lista.stream()
                .filter(k -> (ime == null || ime.isEmpty()) || k.getImePrezime().toLowerCase().contains(ime.toLowerCase()))
                .filter(k -> (mesto == null || mesto.isEmpty()) || k.getMesto().getNaziv().toLowerCase().contains(mesto.toLowerCase()))
                .collect(Collectors.toList());
        this.lista=filteredList;
        fireTableDataChanged();
    }
    
    
    
}
