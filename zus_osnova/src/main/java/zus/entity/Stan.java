package zus.entity;

public class Stan {
    private int id_objekta;
    private String naziv;
    private int id_planete;

    public Stan(int id_objekta, String naziv) {
        this.id_objekta = id_objekta;
        this.naziv = naziv;
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
