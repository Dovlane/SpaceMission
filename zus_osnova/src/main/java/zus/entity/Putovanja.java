package zus.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Putovanja {
    private int id_putovanja;
    private String prevoz;
    private LocalDate datum_i_vreme;
    private int id_korisnika,id_objekta;
    private String imeKorisnika,prezimeKorisnika,objekat;
    private Planeta planeta;
    private Stan stan;
    List<Korisnik> saputnici = new ArrayList<>();


    public Putovanja(int id_putovanja, String prevoz, LocalDate datum_i_vreme, int id_korisnika, int id_objekta) {
        this.id_putovanja = id_putovanja;
        this.prevoz = prevoz;
        this.datum_i_vreme = datum_i_vreme;
        this.id_korisnika = id_korisnika;
        this.id_objekta = id_objekta;
    }

    public Putovanja(String imeKorisnika, String prezimeKorisnika,
                     Planeta planeta, Stan stan, LocalDate datum_i_vreme, String prevoz, List<Korisnik> saputnici) {
        this.imeKorisnika = imeKorisnika;
        this.prezimeKorisnika = prezimeKorisnika;
        this.planeta = planeta;
        this.stan = stan;
        this.datum_i_vreme = datum_i_vreme;
        this.prevoz = prevoz;
        this.saputnici = saputnici;
    }

    public int getId_putovanja() {
        return id_putovanja;
    }

    public String getPrevoz() {
        return prevoz;
    }

    public LocalDate getDatum_i_vreme() {
        return datum_i_vreme;
    }

    public int getId_korisnika() {
        return id_korisnika;
    }

    public int getId_objekta() {
        return id_objekta;
    }

    public String getImeKorisnika() {
        return imeKorisnika;
    }

    public String getPrezimeKorisnika() {
        return prezimeKorisnika;
    }

    public String getObjekat() {
        return objekat;
    }

    public Planeta getPlaneta() {
        return planeta;
    }

    public Stan getStan() {
        return stan;
    }

    public List<Korisnik> getSaputnici() {
        return saputnici;
    }

    @Override
    public String toString() {
        return imeKorisnika+" "+prezimeKorisnika+" "+planeta+" "+stan+" "+datum_i_vreme+" "+prevoz+" "+saputnici;
    }
}
