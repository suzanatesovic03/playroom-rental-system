/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Igraonica;
import domen.Iznajmljivanje;
import domen.Klijent;
import domen.Mesto;
import domen.SmenaZaposlenog;
import domen.StavkaIznajmljivanja;
import domen.ZapSmena;
import domen.Zaposleni;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Komunikacija {

    private Socket soket;
    private Posiljalac posiljalac;
    private Primalac primalac;
    private static Komunikacija instanca;

    public Posiljalac getPosiljalac() {
        return posiljalac;
    }

    public Primalac getPrimalac() {
        return primalac;
    }

    private Komunikacija() {

    }

    public static Komunikacija getInstanca() {
        if (instanca == null) {
            instanca = new Komunikacija();
        }
        return instanca;
    }

    public void konekcija() {
        try {
            soket = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(soket);
            primalac = new Primalac(soket);
        } catch (IOException ex) {
            System.out.println("SERVER NIJE POVEZAN");
        }
    }

    public Zaposleni login(String ki, String pass) {
        if (posiljalac == null || primalac == null) {
            konekcija();
        }
        Zaposleni z = new Zaposleni();
        z.setPassword(pass);
        z.setUsername(ki);
        Zahtev zahtev = new Zahtev(Operacija.LOGIN, z);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        z = (Zaposleni) odg.getOdgovor();
        return z;
    }

    public List<Klijent> ucitajKlijente() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_KLIJENTE, null);
        List<Klijent> klijenti = new ArrayList<>();
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        klijenti = (List<Klijent>) odg.getOdgovor();
        return klijenti;
    }

    public void obrisiKlijenta(Klijent k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_KLIJENTA, k);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        Object poruka = odg.getOdgovor();
        if (poruka == null) {
            return;
        }
        if (poruka instanceof String) {
            throw new Exception((String) poruka);
        }
        if (poruka instanceof Exception) {
            Exception ex = (Exception) poruka;
            throw new Exception(ex.getMessage() != null ? ex.getMessage() : "Operacija neuspešna.");
        }
        throw new Exception("Operacija neuspešna.");
    }

    public void dodajKlijenta(Klijent k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_KLIJENTA, k);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();

        Object odgovor = odg.getOdgovor();
        if (odgovor == null) {
            return;
        }

        if (odgovor instanceof String) {
            throw new Exception((String) odgovor); 
        }

        if (odgovor instanceof Exception) {
            throw (Exception) odgovor;
        }

        throw new Exception("Operacija neuspesna");
    }

    public List<Mesto> ucitajMesta() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_MESTA, null);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        return (List<Mesto>) odg.getOdgovor();
    }

    public void azurirajKlijenta(Klijent k) {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_KLIJENTA, k);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("USPESNO");
            cordinator.Cordinator.getInstanca().osveziFormu();
        } else {
            System.out.println("GRESKA");
        }
    }

    public List<Iznajmljivanje> ucitajIznajmljivanja() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_IZNAJMLJIVANJA, null);
        List<Iznajmljivanje> iznajmljivanja = new ArrayList<>();
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        iznajmljivanja = (List<Iznajmljivanje>) odg.getOdgovor();
        return iznajmljivanja;
    }

    public List<StavkaIznajmljivanja> ucitajStavke(int idIznajmljivanje) {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_STAVKE, idIznajmljivanje);
        List<StavkaIznajmljivanja> stavke = new ArrayList<>();
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        stavke = (List<StavkaIznajmljivanja>) odg.getOdgovor();
        return stavke;
    }

    public void obrisiMesto(Mesto m) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_MESTO, m);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("USPESNO");
        } else {
            System.out.println("GRESKA");
            ((Exception) odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
    }

    public void dodajMesto(Mesto m) {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_MESTO, m);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("USPESNO");
        } else {
            System.out.println("GRESKA");
        }
    }

    public void azurirajMesto(Mesto m) {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_MESTO, m);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("USPESNO");
            cordinator.Cordinator.getInstanca().osveziFormuMesto();
        } else {
            System.out.println("GRESKA");
        }
    }

    public List<Igraonica> ucitajIgraonice() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_IGRAONICE, null);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        return (List<Igraonica>) odgovor.getOdgovor();

    }

    public void obrisiIgraonicu(Igraonica i) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_IGRAONICU, i);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() != null) {
            ((Exception) odg.getOdgovor()).printStackTrace();
            throw new Exception("Brisanje neuspešno!");
        }
    }

    public void dodajIgraonicu(Igraonica i) {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_IGRAONICU, i);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("USPESNO");
        } else {
            System.out.println("GRESKA");
        }
    }

    public void azurirajIgraonicu(Igraonica i) {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_IGRAONICU, i);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() != null) {
            throw new RuntimeException("Greška pri ažuriranju igraonice.");
        }
    }

    public List<Zaposleni> ucitajZaposlene() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_ZAPOSLENE, null);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        return (List<Zaposleni>) odgovor.getOdgovor();
    }

    public void obrisiZaposlenog(Zaposleni z) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_ZAPOSLENOG, z);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        Object poruka = odg.getOdgovor();
        if (poruka == null) {
            return;
        }
        if (poruka instanceof String) {
            throw new Exception((String) poruka);
        }
        if (poruka instanceof Exception) {
            Exception ex = (Exception) poruka;
            throw new Exception(ex.getMessage() != null ? ex.getMessage() : "Operacija neuspešna.");
        }
        throw new Exception("Operacija neuspešna.");
    }

    public void dodajZaposlenog(Zaposleni z) {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_ZAPOSLENOG, z);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("USPESNO");
        } else {
            System.out.println("GRESKA");
        }
    }

    public void azurirajZaposlenog(Zaposleni z) {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_ZAPOSLENOG, z);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("USPESNO");
            cordinator.Cordinator.getInstanca().osveziFormuZaposleni();
        } else {
            System.out.println("GRESKA");
        }
    }

    public List<SmenaZaposlenog> ucitajSmene() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_SMENE, null);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        return (List<SmenaZaposlenog>) odgovor.getOdgovor();
    }

    public List<ZapSmena> ucitajZapSmena() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_ZAPOSLENISMENA, null);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        return (List<ZapSmena>) odgovor.getOdgovor();
    }

    public void dodajZapSmenu(ZapSmena zapSmena) {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_ZAPOSLENISMENA, zapSmena);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("USPESNO");
        } else {
            System.out.println("GRESKA");
        }
    }

    public void obrisiZapSmena(ZapSmena zs) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_ZAPOSLENOISMENA, zs);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("USPESNO");
        } else {
            System.out.println("GRESKA");
            ((Exception) odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
    }

    public void azurirajZapSmena(ZapSmena zs) {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_ZAPOSLENISMENA, zs);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() instanceof Exception) {
            ((Exception) odg.getOdgovor()).printStackTrace();
            throw new RuntimeException("Greška pri ažuriranju dodeljene smene.");
        }
        System.out.println("Uspešno ažurirano.");
    }

    public void dodajIznajmljivanje(Iznajmljivanje iz) {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_IZNAJMLJIVANJE, iz);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("USPESNO");
        } else {
            System.out.println("GRESKA");
        }
    }

    public void obrisiIznajmljivanje(Iznajmljivanje iz) {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_IZNAJMLJIVANJE, iz);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("USPESNO");
        } else {
            System.out.println("GRESKA");
        }
    }

    public void azurirajIznajmljivanje(Iznajmljivanje i) {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_IZNAJMLJIVANJE, i);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() instanceof Exception) {
            ((Exception) odg.getOdgovor()).printStackTrace();
            throw new RuntimeException("Greška pri ažuriranju iznajmljivanje.");
        }
        System.out.println("Uspešno ažurirano.");
    }

    public void dodajSmenu(SmenaZaposlenog s) {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_SMENU, s);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("USPESNO");
        } else {
            System.out.println("GRESKA");
        }
    }

    public void obrisiSmenu(SmenaZaposlenog sz) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_SMENU, sz);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("USPESNO");
        } else {
            System.out.println("GRESKA");
            ((Exception) odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
    }

}
