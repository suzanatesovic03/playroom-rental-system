/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.ApstraktniDomenskiObjekat;
import domen.Igraonica;
import domen.Iznajmljivanje;
import domen.Klijent;
import domen.Mesto;
import domen.SmenaZaposlenog;
import domen.StavkaIznajmljivanja;
import domen.ZapSmena;
import domen.Zaposleni;
import java.util.ArrayList;
import java.util.List;
import operacija.igraonice.AzurirajIgraonicuSO;
import operacija.igraonice.DodajIgraonicuSO;
import operacija.igraonice.ObrisiIgraonicuSO;
import operacija.igraonice.UcitajIgraoniceSO;
import operacija.iznajmljivanja.AzurirajIznajmljivanjeSO;
import operacija.iznajmljivanja.DodajIznajmljivanjeSO;
import operacija.iznajmljivanja.ObrisiIznajmljivanjeSO;
import operacija.iznajmljivanja.UcitajIznajmljivanjaSO;
import operacija.klijenti.AzurirajKlijentaSO;
import operacija.klijenti.KreirajKlijentaSO;
import operacija.klijenti.ObrisiKlijentaSO;
import operacija.klijenti.UcitajKlijenteSO;
import operacija.login.LoginOperacija;
import operacija.mesto.AzurirajMestoSO;
import operacija.mesto.KreirajMestoSO;
import operacija.mesto.ObrisiMestoSO;
import operacija.mesto.UcitajMestaSO;
import operacija.smene.DodajSmenuSO;
import operacija.smene.ObrisiSmenuSO;
import operacija.smene.UcitajSmeneSO;
import operacija.zaposleni.AzurirajZaposlenogSO;
import operacija.zaposleni.KreirajZaposlenogSO;
import operacija.zaposleni.ObrisiZaposlenogSO;
import operacija.zaposleni.UcitajZaposleneSO;
import operacija.zapsmena.AzurirajZapSmenaSO;
import operacija.zapsmena.DodajZapSmenaSO;
import operacija.zapsmena.ObrisiZapSmenaSO;
import operacija.zapsmena.UcitajZapSmenaSO;
import repository.db.impl.DbRepositoryGeneric;

/**
 *
 * @author User
 */
public class Controller {

    private static Controller instance;
    private final DbRepositoryGeneric repository = new DbRepositoryGeneric();

    private Controller() {

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;

    }

    public Zaposleni login(Zaposleni z) throws Exception {
        LoginOperacija operacija = new LoginOperacija();
        operacija.izvrsi(z, null);
        System.out.println("KLASA CONTROLLER: " + operacija.getZaposleni());
        return operacija.getZaposleni();
    }

    public List<Klijent> ucitajKlijente() throws Exception {
        UcitajKlijenteSO operacija = new UcitajKlijenteSO();
        operacija.izvrsi(null, null);
        for (Klijent k : operacija.getKlijenti()) {
            if (k.getMesto() == null) {
                System.out.println("Klijent " + k.getIdKlijent() + " nema mesto!");
            }
        }

        System.out.println("KLASA CONTROLLER: " + operacija.getKlijenti());
        return operacija.getKlijenti();
    }

    public void obrisiKlijenta(Klijent k) throws Exception {
        ObrisiKlijentaSO operacija = new ObrisiKlijentaSO();
        operacija.izvrsi(k, null);
    }

    public void dodajKlijenta(Klijent k) throws Exception {
        KreirajKlijentaSO operacija = new KreirajKlijentaSO();
        operacija.izvrsi(k, null);
    }

    public List<Mesto> ucitajMesta() throws Exception {
        UcitajMestaSO operacija = new UcitajMestaSO();
        operacija.izvrsi(null, null);
        return operacija.getMesta();
    }

    public void azurirajKlijenta(Klijent k) throws Exception {
        AzurirajKlijentaSO operacija = new AzurirajKlijentaSO();
        operacija.izvrsi(k, null);
    }

    public List<Iznajmljivanje> ucitajIznajmljivanja() throws Exception {
        UcitajIznajmljivanjaSO operacija = new UcitajIznajmljivanjaSO();
        operacija.izvrsi(null, null);
        System.out.println("KLASA CONTROLLER: " + operacija.getIznajmljivanja());
        return operacija.getIznajmljivanja();
    }


    public void obrisiMesto(Mesto m) throws Exception {
        ObrisiMestoSO operacija = new ObrisiMestoSO();
        operacija.izvrsi(m, null);
    }

    public void dodajMesto(Mesto m) throws Exception {
        KreirajMestoSO operacija = new KreirajMestoSO();
        operacija.izvrsi(m, null);
    }

    public void azurirajMesto(Mesto m1) throws Exception {
        AzurirajMestoSO operacija = new AzurirajMestoSO();
        operacija.izvrsi(m1, null);
    }

    public List<Igraonica> ucitajIgraonice() throws Exception {
        UcitajIgraoniceSO operacija = new UcitajIgraoniceSO();
        operacija.izvrsi(null, null);
        return operacija.getIgraonice();
    }

    public void obrisiIgraonicu(Igraonica i) throws Exception {
        ObrisiIgraonicuSO operacija = new ObrisiIgraonicuSO();
        operacija.izvrsi(i, null);
    }

    public void dodajIgraonicu(Igraonica i) throws Exception {
        DodajIgraonicuSO operacija = new DodajIgraonicuSO();
        operacija.izvrsi(i, null);
    }

    public void azurirajIgraonicu(Igraonica i) throws Exception {
        AzurirajIgraonicuSO operacija = new AzurirajIgraonicuSO();
        operacija.izvrsi(i, null);
    }

    public List<Zaposleni> ucitajZaposlene() throws Exception {
        UcitajZaposleneSO operacija = new UcitajZaposleneSO();
        operacija.izvrsi(null, null);
        return operacija.getZaposleni();
    }

    public void obrisiZaposlenog(Zaposleni z) throws Exception {
        ObrisiZaposlenogSO operacija = new ObrisiZaposlenogSO();
        operacija.izvrsi(z, null);
    }

    public void dodajZaposlenog(Zaposleni z) throws Exception {
         KreirajZaposlenogSO operacija = new KreirajZaposlenogSO();
         operacija.izvrsi(z,null);
    }

    public void azurirajZaposlenog(Zaposleni zap) throws Exception {
        AzurirajZaposlenogSO operacija = new AzurirajZaposlenogSO();
        operacija.izvrsi(zap,null);
    }

    public List<SmenaZaposlenog> ucitajSmene() throws Exception {
        UcitajSmeneSO operacija = new UcitajSmeneSO();
        operacija.izvrsi(null,null);
        return operacija.getSmene();
    }

    public List<ZapSmena> ucitajZapSmena() throws Exception {
        UcitajZapSmenaSO operacija = new UcitajZapSmenaSO();
        operacija.izvrsi(null,null);
        return operacija.getZapSmena();
    }

    public void dodajZapSmena(ZapSmena zs) throws Exception {
        DodajZapSmenaSO operacija = new DodajZapSmenaSO();
        operacija.izvrsi(zs,null);
    }

    public void obrisiZapSmena(ZapSmena zs) throws Exception {
        ObrisiZapSmenaSO operacija = new ObrisiZapSmenaSO();
        operacija.izvrsi(zs,null);
    }

    public void azurirajZapSmena(ZapSmena zs) throws Exception {
        AzurirajZapSmenaSO operacija = new AzurirajZapSmenaSO();
        operacija.izvrsi(zs,null);
    }

    public void dodajIznajmljivanje(Iznajmljivanje iz) throws Exception {
        DodajIznajmljivanjeSO operacija = new DodajIznajmljivanjeSO();
        operacija.izvrsi(iz, null);
    }

    public void obrisiIznajmljivanje(Iznajmljivanje iz) throws Exception {
        ObrisiIznajmljivanjeSO operacija = new ObrisiIznajmljivanjeSO();
        operacija.izvrsi(iz, null);
    }

    public void azurirajIznajmljivanje(Iznajmljivanje i) throws Exception {
        AzurirajIznajmljivanjeSO operacija = new AzurirajIznajmljivanjeSO();
        operacija.izvrsi(i, null);
    }

    public void dodajSmenuZaposlenog(SmenaZaposlenog sz) throws Exception {
        DodajSmenuSO operacija=new DodajSmenuSO();
        operacija.izvrsi(sz, null);
        
    }

    public void obrisiSmenu(SmenaZaposlenog sz) throws Exception {
        ObrisiSmenuSO operacija=new ObrisiSmenuSO();
        operacija.izvrsi(sz, null);
    }



}
