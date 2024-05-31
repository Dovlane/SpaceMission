package zus.entity;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Korisnik {
    private int id_korisnik;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String lozinka;

    public Korisnik(int id_korisnik, String ime, String prezime, String korisnickoIme, String lozinka) {
        this.id_korisnik = id_korisnik;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public Korisnik(ResultSet resultSet) throws SQLException {
        this.id_korisnik = resultSet.getInt(1);
        this.korisnickoIme = resultSet.getString(2);
        this.lozinka = resultSet.getString(3);
        this.ime = resultSet.getString(4);
        this.prezime = resultSet.getString(5);
    }
    public Korisnik(int id_korisnik, String korisnickoIme, String lozinka) {
        this.id_korisnik = id_korisnik;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public int getId_korisnik() {
        return id_korisnik;
    }

    public void setId_korisnik(int id_korisnik) {
        this.id_korisnik = id_korisnik;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    @Override
    public String toString() {
        return ime+ " "+prezime;
    }
}
