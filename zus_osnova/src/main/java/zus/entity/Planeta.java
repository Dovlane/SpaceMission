package zus.entity;

public class Planeta {
    private int id_planete;
    private String naziv;
    private int nastanjiva;

    public Planeta(int id_planete, String naziv) {
        this.id_planete = id_planete;
        this.naziv = naziv;
    }
    public Planeta(int id_planete, String naziv,int nastanjiva) {
        this.id_planete = id_planete;
        this.naziv = naziv;
        this.nastanjiva = nastanjiva;
    }

    public int getId_planete() {
        return id_planete;
    }

    public void setId_planete(int id_planete) {
        this.id_planete = id_planete;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv;
    }
}
