/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;

/**
 *
 * @author User
 */
public class ObradaKlijentskihZahteva extends Thread {

    Socket socket;
    Posiljalac posiljalac;
    Primalac primalac;
    boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
        posiljalac = new Posiljalac(socket);
        primalac = new Primalac(socket);
    }

    @Override
    public void run() {
        while (!kraj) {
            try {
                Zahtev zahtev = (Zahtev) primalac.primi();
                if (zahtev == null) {
                    System.out.println("PAZNJA: Klijent " + socket.getRemoteSocketAddress() + " se odjavio.");
                    break;
                }
                Odgovor odgovor = new Odgovor();
                System.out.println("Controller loaded from: "
                        + controller.Controller.class.getProtectionDomain().getCodeSource().getLocation());

                switch (zahtev.getOperacija()) {
                    case LOGIN:
                        Zaposleni z = (Zaposleni) zahtev.getParametar();
                        z = Controller.getInstance().login(z);
                        odgovor.setOdgovor(z);
                        break;
                    case UCITAJ_KLIJENTE:
                        List<Klijent> klijenti = Controller.getInstance().ucitajKlijente();
                        odgovor.setOdgovor(klijenti);
                        break;
                    case OBRISI_KLIJENTA:
                        try {
                        Klijent k = (Klijent) zahtev.getParametar();
                        Controller.getInstance().obrisiKlijenta(k);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e.getMessage());
                    }
                    break;
                    case DODAJ_KLIJENTA:
                    try {
                        Klijent k = (Klijent) zahtev.getParametar();
                        Controller.getInstance().dodajKlijenta(k);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e.getMessage());
                    }
                    break;
                    case UCITAJ_MESTA:
                    try {
                        List<domen.Mesto> mesta = Controller.getInstance().ucitajMesta();
                        odgovor.setOdgovor(mesta);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case AZURIRAJ_KLIJENTA:
                        Klijent k1 = (Klijent) zahtev.getParametar();
                        Controller.getInstance().azurirajKlijenta(k1);
                        odgovor.setOdgovor(null);
                        break;
                    case UCITAJ_IZNAJMLJIVANJA:
                        try {
                        List<Iznajmljivanje> iznajmljivanja = Controller.getInstance().ucitajIznajmljivanja();
                        System.out.println("KLASA OKZ");
                        System.out.println(iznajmljivanja);
                        odgovor.setOdgovor(iznajmljivanja);
                    } catch (Exception e) {
                        e.printStackTrace();
                        odgovor.setOdgovor(null);
                    }
                    break;
                    case OBRISI_MESTO:
                        try {
                        Mesto m = (Mesto) zahtev.getParametar();
                        Controller.getInstance().obrisiMesto(m);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case DODAJ_MESTO:
                        Mesto m = (Mesto) zahtev.getParametar();
                        Controller.getInstance().dodajMesto(m);
                        odgovor.setOdgovor(null);
                        break;
                    case AZURIRAJ_MESTO:
                        try {
                        Mesto m1 = (Mesto) zahtev.getParametar();
                        Controller.getInstance().azurirajMesto(m1);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case UCITAJ_IGRAONICE:
                    try {
                        List<Igraonica> igraonice = Controller.getInstance().ucitajIgraonice();
                        odgovor.setOdgovor(igraonice);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case OBRISI_IGRAONICU:
                        try {
                        Igraonica i = (Igraonica) zahtev.getParametar();
                        Controller.getInstance().obrisiIgraonicu(i);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case DODAJ_IGRAONICU:
                    try {
                        Igraonica i = (Igraonica) zahtev.getParametar();
                        Controller.getInstance().dodajIgraonicu(i);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case AZURIRAJ_IGRAONICU:
                    try {
                        Igraonica i = (Igraonica) zahtev.getParametar();
                        Controller.getInstance().azurirajIgraonicu(i);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case UCITAJ_ZAPOSLENE:
                        List<Zaposleni> zaposleni = Controller.getInstance().ucitajZaposlene();
                        odgovor.setOdgovor(zaposleni);
                        break;
                    case OBRISI_ZAPOSLENOG:
                    try {
                        Zaposleni z1 = (Zaposleni) zahtev.getParametar();
                        Controller.getInstance().obrisiZaposlenog(z1);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e.getMessage());
                    }
                    break;
                    case DODAJ_ZAPOSLENOG:
                    try {
                        Zaposleni z2 = (Zaposleni) zahtev.getParametar();
                        Controller.getInstance().dodajZaposlenog(z2);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case AZURIRAJ_ZAPOSLENOG:
                    try {
                        Zaposleni zap = (Zaposleni) zahtev.getParametar();
                        Controller.getInstance().azurirajZaposlenog(zap);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case UCITAJ_SMENE:
                        List<SmenaZaposlenog> smene = Controller.getInstance().ucitajSmene();
                        odgovor.setOdgovor(smene);
                        break;
                    case DODAJ_SMENU:
                        try {
                        SmenaZaposlenog sz = (SmenaZaposlenog) zahtev.getParametar();
                        Controller.getInstance().dodajSmenuZaposlenog(sz);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case OBRISI_SMENU:
                    try {
                        SmenaZaposlenog sz = (SmenaZaposlenog) zahtev.getParametar();
                        Controller.getInstance().obrisiSmenu(sz);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case UCITAJ_ZAPOSLENISMENA:
                        List<ZapSmena> zapsmena = Controller.getInstance().ucitajZapSmena();
                        odgovor.setOdgovor(zapsmena);
                        break;
                    case DODAJ_ZAPOSLENISMENA:
                        try {
                        ZapSmena zs = (ZapSmena) zahtev.getParametar();
                        Controller.getInstance().dodajZapSmena(zs);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case OBRISI_ZAPOSLENOISMENA:
                        try {
                        ZapSmena zs = (ZapSmena) zahtev.getParametar();
                        Controller.getInstance().obrisiZapSmena(zs);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case AZURIRAJ_ZAPOSLENISMENA:
                         try {
                        ZapSmena zs = (ZapSmena) zahtev.getParametar();
                        Controller.getInstance().azurirajZapSmena(zs);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case DODAJ_IZNAJMLJIVANJE:
                        try {
                        Iznajmljivanje iz = (Iznajmljivanje) zahtev.getParametar();
                        Controller.getInstance().dodajIznajmljivanje(iz);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case OBRISI_IZNAJMLJIVANJE:
                    try {
                        Iznajmljivanje iz = (Iznajmljivanje) zahtev.getParametar();
                        Controller.getInstance().obrisiIznajmljivanje(iz);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case AZURIRAJ_IZNAJMLJIVANJE:
                        try {
                        Iznajmljivanje i = (Iznajmljivanje) zahtev.getParametar();
                        Controller.getInstance().azurirajIznajmljivanje(i);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    default:
                        System.out.println("GRESKA, TA OPERACIJA NE POSTOJI");

                }
                posiljalac.posalji(odgovor);
            } catch (java.net.SocketException | java.io.EOFException e) {
                System.out.println("PAZNJA: Veza sa klijentom " + socket.getRemoteSocketAddress() + " je prekinuta.");
                break;
            } catch (Exception ex) {
                ex.printStackTrace();
                break;
            }
        }
    }

    public void prekini() throws IOException {
        kraj = true;
        socket.close();
        interrupt();
    }

}
