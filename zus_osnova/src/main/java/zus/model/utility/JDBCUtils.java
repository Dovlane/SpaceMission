package zus.model.utility;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import zus.entity.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Properties;

public class JDBCUtils {
    public static Connection connection = null;
    private static ObservableList<PrikazPutovanja> putovanja = FXCollections.observableArrayList();
    private static ObservableList<Planeta> planete = FXCollections.observableArrayList();
    private static ObservableList<Stan> stanovi = FXCollections.observableArrayList();
    private static ObservableList<String> prevozi = FXCollections.observableArrayList();
    private static int korisnicki_id = 0;
    private static int planetaId = 0;
    private static int objekat_id = 0;


    public static void connect() {
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "");
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zus_1", properties);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setKorisnicki_id(int novi_korisnicki_id) {
        korisnicki_id = novi_korisnicki_id;
    }
    public static ObservableList<PrikazPutovanja> getPutovanja() {
        return putovanja;
    }

    public static ObservableList<Planeta> getPlanete() {
        return planete;
    }

    public static ObservableList<Stan> getStanbeniObjekti() {
        return stanovi;
    }

    public static ObservableList<String> getPrevozi() {
        String query = "select distinct prevoz from putovanja";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                String prevoz = resultSet.getString(1);

                prevozi.add(prevoz);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return prevozi;
    }

    public static void setPlanetaId(int noviPlanetaId) {
        planetaId = noviPlanetaId;
    }
    public static int dajIDpoStringuObjekta(String nazivObjekta, int planetaId){
        String query = "SELECT naziv, id_planete FROM st_objekti WHERE naziv = ? AND id_planete = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, nazivObjekta);
            preparedStatement.setInt(2, planetaId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                return id;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public static Korisnik proveriDaLiSadrzi(String korisnickoIme) {

        String query = "SELECT * FROM korisnici WHERE korisnicko_ime = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, korisnickoIme);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Korisnik(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;

    }

    public static void insertIntoKorisnik(String ime, String prezime, String korisnickoIme, String lozinka) {
        String query = "insert into korisnici (ime, prezime, korisnicko_ime, lozinka)" +
                "values (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            connection.setAutoCommit(false);

            statement.setString(1, ime);
            statement.setString(2, prezime);
            statement.setString(3, korisnickoIme);
            statement.setString(4, lozinka);

            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void selectNastanjivePlanete() {
        String query = "select id_planete, naziv from planete where " +
                "(udaljenost is not null and min_temp is not null and max_temp is not null and kiseonik is not null and " +
                "rastvarac is not null and prag_gravitacije is not null and brzina_orb is not null and br_poginulih is not null) " +
                "and (udaljenost between ? and ?) and (min_temp between ? and ?) and (max_temp between ? and ?) and (max_temp - min_temp) <= ? " +
                "and (kiseonik between ? and ?) and ((rastvarac + kiseonik)  between ? and ?) and prag_gravitacije >= ? and (brzina_orb between ? and ?) and br_poginulih <= ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, 100000000);
            statement.setInt(2, 200000000);
            statement.setInt(3, 120);
            statement.setInt(4, 250);
            statement.setInt(5, 250);
            statement.setInt(6, 350);
            statement.setInt(7, 120);
            statement.setDouble(8, 15.0);
            statement.setDouble(9, 25.0);
            statement.setDouble(10, 90.0);
            statement.setDouble(11, 99.0);
            statement.setInt(12, 1000);
            statement.setInt(13, 25);
            statement.setInt(14, 35);
            statement.setInt(15, 20);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                boolean vecUListi = false;
                for (var planeta : planete) {
                    if (planeta.getId_planete() == planetaId) {
                        vecUListi = true;
                        break;
                    }
                }
                if (vecUListi)
                    continue;

                int planetaId = resultSet.getInt(1);
                String naziv = resultSet.getString(2);

                Planeta p = new Planeta(planetaId,naziv);
                planete.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static String izvuciImeIPrezime(int korisnicki_id){
        String query = "SELECT ime, prezime FROM korisnici WHERE id_korisnika = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, korisnicki_id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString(1) + " " + resultSet.getString(2);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "";
    }
    public static String izvuciIme(int korisnicki_id){
        String query = "SELECT ime FROM korisnici WHERE id_korisnika = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, korisnicki_id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString(1) ;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "";
    }
    public static String izvuciPrezime(int korisnicki_id){
        String query = "SELECT prezime FROM korisnici WHERE id_korisnika = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, korisnicki_id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString(1) ;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "";
    }
    public static void selectAllFromStanovi() {
        if (planetaId == 0)
            return;

        String query = "SELECT id_objekta, naziv, id_planete FROM st_objekti WHERE id_planete = ?";
        stanovi.clear();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, planetaId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id_objekta = resultSet.getInt(1);
                String naziv = resultSet.getString(2);
                int id_planete = resultSet.getInt(3);
                Stan s = new Stan(id_objekta, naziv, id_planete);
                stanovi.add(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void prikaziTrenutnaPutovanja(){

        String query = "select put.ime_putnika, put.prezime_putnika, pl.naziv, s.naziv, put.datum_i_vreme, put.prevoz from putovanja put" +
                " " + "join st_objekti s using(id_objekta) join planete pl using (id_planete) where id_korisnika = ?";

        putovanja.clear();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, korisnicki_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String ime_putnika = resultSet.getString(1);
                String prezime_putnika = resultSet.getString(2);
                String naziv_planete = resultSet.getString(3);
                String naziv_stana = resultSet.getString(4);
                LocalDate dateTime = resultSet.getDate(5).toLocalDate();
                String prevoz = resultSet.getString(6);
                PrikazPutovanja p = new PrikazPutovanja(ime_putnika, prezime_putnika ,naziv_planete,
                        naziv_stana, dateTime, prevoz);
                putovanja.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void dodajPutovanje(String ime, String prezime, int korisnickiId, int objekatId, LocalDateTime datumIVreme, String prevoz) {
        String query = "INSERT INTO putovanja (ime_putnika, prezime_putnika, id_korisnika, id_objekta, datum_i_vreme, prevoz) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try  {
            PreparedStatement statement = connection.prepareStatement(query);
            connection.setAutoCommit(false);

            statement.setString(1, ime);
            statement.setString(2, prezime);
            statement.setInt(3, korisnickiId);
            statement.setInt(4, objekatId);
            statement.setTimestamp(5, Timestamp.valueOf(datumIVreme));
            statement.setString(6, prevoz);

            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static int getKorisnickiId() {
        return korisnicki_id;
    }

}
