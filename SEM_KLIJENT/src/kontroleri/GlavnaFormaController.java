/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Zaposleni;
import forme.GlavnaForma;

/**
 *
 * @author User
 */
public class GlavnaFormaController {
    private final GlavnaForma gf;
     public GlavnaFormaController(GlavnaForma gf) {
        this.gf = gf;
        addActionListeners();
    }

    private void addActionListeners() {
    }

    public void otvoriFormu() {
        Zaposleni ulogovani = cordinator.Cordinator.getInstanca().getUlogovani();
        String imePrezime = ulogovani.getImePrezime();
        gf.setVisible(true);
        gf.getjLabel2().setText(imePrezime);
    }
}
