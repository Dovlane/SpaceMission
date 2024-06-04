package zus.entity;

public class Stan {
    private int id_objekta;
    private String naziv;
    private int kapacitet;
    private int id_planete;

    public Stan(int id_objekta, String naziv, int kapacitet, int id_planete) {
        this.id_objekta = id_objekta;
        this.naziv = naziv;
        this.kapacitet = kapacitet;
        this.id_planete = id_planete;
    }
    public Stan(String naziv) {
        this.naziv = naziv;
    }

    public int getId_objekta() {
        return id_objekta;
    }

    public String getNaziv() {
        return naziv;
    }

    public int getId_planete() {
        return id_planete;
    }

    @Override
    public String toString() {
        return naziv;
    }
}
