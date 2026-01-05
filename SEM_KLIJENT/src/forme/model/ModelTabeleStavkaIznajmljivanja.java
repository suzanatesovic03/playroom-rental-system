/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Iznajmljivanje;
import domen.StavkaIznajmljivanja;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author User
 */
public class ModelTabeleStavkaIznajmljivanja extends AbstractTableModel {

    private List<StavkaIznajmljivanja> lista;

    private static final String[] kolone = {
        "rb",
        "datum",
        "vreme od",
        "vreme do",
        "trajanje (sati)",
        "cena/sat",
        "dodatna usluga",
        "cena dodatne usluge",
        "ukupan iznos",
        "igraonica"
    };

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm");

    public ModelTabeleStavkaIznajmljivanja(List<StavkaIznajmljivanja> lista) {
        this.lista = (lista == null) ? new ArrayList<>() : lista;
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
        StavkaIznajmljivanja si = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return si.getRb();

            case 1:
                return si.getDatumIznajmljivanja() != null
                        ? si.getDatumIznajmljivanja().format(DATE_FMT)
                        : "";

            case 2:
                return si.getVremeOd() != null
                        ? si.getVremeOd().format(TIME_FMT)
                        : "";

            case 3:
                return si.getVremeDo() != null
                        ? si.getVremeDo().format(TIME_FMT)
                        : "";

            case 4:

                return si.getBrojSati();

            case 5:
                return si.getCenaSat();

            case 6:
                return si.getDodatnaUsluga() != null ? si.getDodatnaUsluga() : "";

            case 7:
                return si.getCenaDodatneUsluge();

            case 8:

                return si.getUkupanIznosIgraonice();

            case 9:
                return (si.getIgraonica() != null) ? si.getIgraonica().getNaziv() : "";

            default:
                return "";
        }
    }

    public List<StavkaIznajmljivanja> getLista() {
        return lista;
    }

    public void dodaj(StavkaIznajmljivanja si) {
        if(si==null) return;
        si.izracunajUkupanIznos();
        lista.add(si);
        fireTableRowsInserted(lista.size() - 1, lista.size() - 1);
    }

    public void obrisiStavku(int red) {
        lista.remove(red);
        fireTableDataChanged();
    }
}
