package zus.model.utility;


import zus.entity.Korisnik;
import zus.entity.Planeta;
import zus.entity.Putovanja;
import zus.entity.Stan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCUtils {
    public static Connection connection = null;

    public static void connect() {
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "");
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zus", properties);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Korisnik> selectAllFromKorisnik() {
        List<Korisnik> people = new ArrayList<>();
        String query = "select * from korisnici";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int korisnikId = resultSet.getInt(1);
                String ime = resultSet.getString(2);
                String prezime = resultSet.getString(3);
                String korisnickoIme = resultSet.getString(4);
                String lozinka = resultSet.getString(5);
                Korisnik korisnik = new Korisnik(korisnikId,ime,prezime,korisnickoIme,lozinka);
                System.out.println(ime);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }
    public static boolean proveriDaLiSadrzi(String korisnickoIme) {

        String query = "SELECT * FROM korisnici WHERE korisnicko_ime = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, korisnickoIme);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
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
    public static List<Planeta> selectAllFromPlanete() {
        List<Planeta> planete = new ArrayList<>();
        String query = "select * from planete";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int planetaId = resultSet.getInt(1);
                String naziv = resultSet.getString(2);
                int nastanjiva = resultSet.getInt(11);

                Planeta p = new Planeta(planetaId,naziv,nastanjiva);
                planete.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return planete;
    }
    public static String izvuciImeIPrezime(String korIme){
        String query = "SELECT * FROM korisnici WHERE korisnicko_ime = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, korIme);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString(4) + " " + resultSet.getString(5);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "";
    }
    public static List<Stan> selectAllFromStanovi() {
        List<Stan> stanovi = new ArrayList<>();
        String query = "select * from st_objekti";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                String naziv = resultSet.getString(2);

                Stan s = new Stan(naziv);
                stanovi.add(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    return stanovi;
    }


    public static List<String> izvuciPrevoz(){
        List<String> prevozi = new ArrayList<>();
        String query = "select * from putovanja";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                String prevoz = resultSet.getString(2);

                prevozi.add(prevoz);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return prevozi;

    }
    public static List<Putovanja> izvuciTrenutnaPutovanja(){
        List<Putovanja> putovanja = new ArrayList<>();
        String query = "select * from putovanja";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                String prevoz = resultSet.getString(2);
                Putovanja p = new Putovanja(null,null,null,
                                            null,null,prevoz,null);
                putovanja.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return putovanja;

    }



}
