/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cordinator;

import domen.Zaposleni;
import forme.DodajIgraonicuForma;
import forme.DodajIznajmljivanjeForm;
import forme.DodajKlijentaForma;
import forme.DodajMestoForma;
import forme.DodajSmenuForma;
import forme.DodajZapSmenaForma;
import forme.DodajZaposlenogForma;
import forme.FormaMod;
import forme.GlavnaForma;
import forme.LoginForma;
import forme.PrikazIgraonicaForma;
import forme.PrikazIznajmljivanjaForma;
import forme.PrikazKlijenataForma;
import forme.PrikazMestaForma;
import forme.PrikazSmeneZaposlenogForma;
import forme.PrikazZapSmenaForma;
import forme.PrikazZaposlenihForma;
import java.util.HashMap;
import java.util.Map;
import kontroleri.DodajIgraonicuController;
import kontroleri.DodajIznajmljivanjeController;
import kontroleri.DodajKlijentaController;
import kontroleri.DodajMestoController;
import kontroleri.DodajSmenuController;
import kontroleri.DodajZapSmenaController;
import kontroleri.DodajZaposlenogController;
import kontroleri.GlavnaFormaController;
import kontroleri.LoginController;
import kontroleri.PrikazIgraonicaController;
import kontroleri.PrikazIznajmljivanjaController;
import kontroleri.PrikazKlijenataController;
import kontroleri.PrikazMestaController;
import kontroleri.PrikazSmeneZaposlenogController;
import kontroleri.PrikazZapSmenaController;
import kontroleri.PrikazZaposlenihController;

/**
 *
 * @author User
 */
public class Cordinator {

    private static Cordinator instanca;
    private Zaposleni ulogovani;
    private LoginController loginControler;
    private GlavnaFormaController glavnaFormaController;
    private PrikazKlijenataController pkController;
    private DodajKlijentaController dkController;
    private PrikazIznajmljivanjaController piController;
    private PrikazMestaController pmController;
    private DodajMestoController dmController;
    private PrikazIgraonicaController pigController;
    private DodajIgraonicuController diController;
    private Map<String, Object> parametri;
    private PrikazZaposlenihController pzController;
    private DodajZaposlenogController dzController;
    private PrikazSmeneZaposlenogController pszController;
    private DodajZapSmenaController dzsController;
    private PrikazZapSmenaController pzsController;
    private DodajIznajmljivanjeController dizController;
    private DodajSmenuController dsController;

    private Cordinator() {
        parametri = new HashMap<>();
    }

    public static Cordinator getInstanca() {
        if (instanca == null) {
            instanca = new Cordinator();
        }
        return instanca;
    }

    public void otvoriLoginFormu() {
        loginControler = new LoginController(new LoginForma());
        loginControler.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
        glavnaFormaController = new GlavnaFormaController(new GlavnaForma());
        glavnaFormaController.otvoriFormu();
    }

    public void otvoriPrikazKlijenataFormu() {
        pkController = new PrikazKlijenataController(new PrikazKlijenataForma());
        pkController.otvoriFormu();
    }

    public Zaposleni getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Zaposleni ulogovani) {
        this.ulogovani = ulogovani;
    }

    public void otvoriDodajKlijentaFormu() {
        dkController = new DodajKlijentaController(new DodajKlijentaForma());
        dkController.otvoriFormu(FormaMod.DODAJ);
    }

    public void dodajParam(String s, Object o) {
        parametri.put(s, o);
    }

    public Object vratiParam(String s) {
        return parametri.get(s);
    }

    public void otvoriIzmeniKlijentaFormu() {
        dkController = new DodajKlijentaController(new DodajKlijentaForma());
        dkController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziFormu() {
        pkController.osveziFormu();
    }

    public void osveziFormuMesto() {
        pmController.osveziFormu();
    }

    public void otvoriPrikazIznajmljivanjaFormu() {
        piController = new PrikazIznajmljivanjaController(new PrikazIznajmljivanjaForma());
        piController.otvoriFormu();
    }

    public void otvoriPrikazMestataFormu() {
        pmController = new PrikazMestaController(new PrikazMestaForma());
        pmController.otvoriFormu();
    }

    public void otvoriDodajMestoFormu() {
        dmController = new DodajMestoController(new DodajMestoForma());
        dmController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriIzmeniMestoFormu() {
        dmController = new DodajMestoController(new DodajMestoForma());
        dmController.otvoriFormu(FormaMod.IZMENI);
    }

    public void otvoriPrikazIgraonicaFormu() {
        pigController = new PrikazIgraonicaController(new PrikazIgraonicaForma());
        pigController.otvoriFormu();
    }

    public void otvoriDodajIgraonicuFormu() {
        diController = new DodajIgraonicuController(new DodajIgraonicuForma());
        diController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriIzmeniIgraonicuFormu() {
        diController = new DodajIgraonicuController(new DodajIgraonicuForma());
        diController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziFormuIgraonica() {
        pigController.osveziFormu();
    }

    public void otvoriPrikazZaposlenihForma() {
        pzController = new PrikazZaposlenihController(new PrikazZaposlenihForma());
        pzController.otvoriFormu();
    }

    public void otvoriIzmeniZaposlenogFormu() {
        dzController = new DodajZaposlenogController(new DodajZaposlenogForma());
        dzController.otvoriFormu(FormaMod.IZMENI);
    }

    public void otvoriDodajZaposlenogFormu() {
        dzController = new DodajZaposlenogController(new DodajZaposlenogForma());
        dzController.otvoriFormu(FormaMod.DODAJ);
    }
    
    public void osveziFormuZaposleni(){
        pzController.osveziFormu();
    }

    public void otvoriPrikazSmenaForma() {
        pszController = new PrikazSmeneZaposlenogController(new PrikazSmeneZaposlenogForma());
        pszController.otvoriFormu();
    }

    public void otvoriDodajZapSmenuForma() {
        dzsController = new DodajZapSmenaController(new DodajZapSmenaForma());
        dzsController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriPrikazZapSmenaForma() {
        pzsController = new PrikazZapSmenaController(new PrikazZapSmenaForma());
        pzsController.otvoriFormu();
    }

    public void osveziZapSmenaFormu() {
        pzsController.osveziFormu();
    }

    public void otvoriIzmeniZapSmenaFormu() {
        dzsController = new DodajZapSmenaController(new DodajZapSmenaForma());
        dzsController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziFormuIznajmljivanje() {
        if (piController == null) {
            System.out.println("piController je null!");
        } else {
            System.out.println("piController postoji, pozivam osveziFormu()");
            piController.osveziFormu();
        }
    }

    public void otvoriDodajIznajmljivanjeFormu() {
        dizController = new DodajIznajmljivanjeController(new DodajIznajmljivanjeForm());
        dizController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriAzurirajIznajmljivanjeFormu() {
        dizController = new DodajIznajmljivanjeController(new DodajIznajmljivanjeForm());
        dizController.otvoriFormu(FormaMod.IZMENI);
    }

    public void otvoriDodajSmenuForma() {
        dsController = new DodajSmenuController(new DodajSmenuForma());
        dsController.otvoriFormu(FormaMod.DODAJ);
    }
    
    

}
