package zus.entity;

import java.time.LocalDate;

public class PrikazPutovanja {
    private String ime_putnika;
    private String prezime_putnika;
    private String naziv_planete;
    private String naziv_stana;
    private LocalDate datum_i_vreme;
    private String prevoz;

    public PrikazPutovanja(String ime_putnika, String prezime_putnika, String naziv_planete, String naziv_stana, LocalDate dateTime, String prevoz) {
        this.ime_putnika = ime_putnika;
        this.prezime_putnika = prezime_putnika;
        this.naziv_planete = naziv_planete;
        this.naziv_stana = naziv_stana;
        this.datum_i_vreme = dateTime;
        this.prevoz = prevoz;
    }

    public String getIme_putnika() {
        return ime_putnika;
    }

    public String getPrezime_putnika() {
        return prezime_putnika;
    }

    public String getNaziv_planete() {
        return naziv_planete;
    }

    public String getNaziv_stana() {
        return naziv_stana;
    }

    public LocalDate getDatum_i_vreme() {
        return datum_i_vreme;
    }

    public String getPrevoz() {
        return prevoz;
    }
}
