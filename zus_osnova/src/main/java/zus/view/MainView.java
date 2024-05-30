package zus.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import zus.controller.PregledKupnjiController;
import zus.entity.Korisnik;
import zus.entity.Planeta;
import zus.entity.Putovanja;
import zus.entity.Stan;
import zus.model.utility.JDBCUtils;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class MainView extends Stage {
    private static MainView mainView;
    Label korisnikLabel;
    ComboBox<Planeta> planetaComboBox;
    ComboBox<Stan> stanComboBox;
    DatePicker datumPolaskaField;
    ComboBox<String> prevozComboBox;
    TextField imeField;
    TextField prezimeField;

    private MainView() {
        this.setTitle("Zivot u svemiru");
        BorderPane bp = new BorderPane();


        korisnikLabel = new Label("Korisnik");
        Label odaberiPlanetuLabel = new Label("Odaberi planetu");
        planetaComboBox = new ComboBox<>();
        Label odaberiStanLabel = new Label("Odaberi stan");
        stanComboBox = new ComboBox<>();
        Label datumPolaskaLabel = new Label("Datum polaska");
        datumPolaskaField = new DatePicker();
        Label odaberitePrevozLabel = new Label("Odaberite prevoz");
        prevozComboBox = new ComboBox<>();
        Label imeLabel = new Label("Ime saputnika");
        imeField = new TextField();
        Label prezimeLabel = new Label("Prezime saputnika");
        prezimeField = new TextField();
        Button pregledKupovinaButton = new Button("Pregled kupovina");
        pregledKupovinaButton.setOnAction(new PregledKupnjiController(this));
        Button kupiKartuButton = new Button("Kupi kartu");
        Label praznaLabel = new Label();
        Label sveKupovineLabel = new Label("Sve kupovine");
        TableView<Putovanja> kupovine = new TableView<>();

        TableColumn<Putovanja,String> kol1 = new TableColumn<>("ime");
        TableColumn<Putovanja,String> kol2 = new TableColumn<>("prezime");
        TableColumn<Putovanja,Planeta> kol3 = new TableColumn<>("planeta");
        TableColumn<Putovanja,Stan> kol4 = new TableColumn<>("stan");
        TableColumn<Putovanja, LocalDate> kol5 = new TableColumn<>("polazak");
        TableColumn<Putovanja,String> kol6 = new TableColumn<>("prevoz");
        TableColumn<Putovanja, List<Korisnik>> kol7 = new TableColumn<>("saputnici");


        kol1.setCellValueFactory(new PropertyValueFactory<>("imeKorisnika"));
        kol2.setCellValueFactory(new PropertyValueFactory<>("prezimeKorisnika"));
        kol3.setCellValueFactory(new PropertyValueFactory<>("planeta"));
        kol4.setCellValueFactory(new PropertyValueFactory<>("stan"));
        kol5.setCellValueFactory(new PropertyValueFactory<>("datum_i_vreme"));
        kol6.setCellValueFactory(new PropertyValueFactory<>("prevoz"));
        kol7.setCellValueFactory(new PropertyValueFactory<>("saputnici"));

        ObservableList<Putovanja> obPutovanja = FXCollections.observableArrayList(JDBCUtils.izvuciTrenutnaPutovanja());
        kupovine.setItems(obPutovanja);
        kupovine.getColumns().addAll(kol1, kol2, kol3, kol4, kol5, kol6, kol7);
        //imeKorisnika+" "+prezimeKorisnika+" "+planeta+" "+stan+" "+datum_i_vreme+" "+prevoz+" "+saputnici



        double controlWidth = 200;
        planetaComboBox.setPrefWidth(controlWidth);
        stanComboBox.setPrefWidth(controlWidth);
        prevozComboBox.setPrefWidth(controlWidth);
        datumPolaskaField.setPrefWidth(controlWidth);
        imeField.setPrefWidth(controlWidth);
        prezimeField.setPrefWidth(controlWidth);


        kupovine.setPrefSize(700, 200);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(15));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.TOP_CENTER);


        gridPane.add(korisnikLabel, 0, 0);
        gridPane.add(new Label(), 1, 0);

        gridPane.add(odaberiPlanetuLabel, 0, 1);
        gridPane.add(planetaComboBox, 1, 1);

        gridPane.add(odaberiStanLabel, 0, 2);
        gridPane.add(stanComboBox, 1, 2);

        gridPane.add(datumPolaskaLabel, 0, 3);
        gridPane.add(datumPolaskaField, 1, 3);

        gridPane.add(odaberitePrevozLabel, 0, 4);
        gridPane.add(prevozComboBox, 1, 4);

        gridPane.add(imeLabel, 0, 5);
        gridPane.add(imeField, 1, 5);

        gridPane.add(prezimeLabel, 0, 6);
        gridPane.add(prezimeField, 1, 6);

        HBox buttonHBox = new HBox(10, pregledKupovinaButton, kupiKartuButton);
        buttonHBox.setAlignment(Pos.CENTER);

        gridPane.add(buttonHBox, 0, 7, 2, 1);

        gridPane.add(praznaLabel, 0, 8, 2, 1);

        gridPane.add(sveKupovineLabel, 0, 9);
        gridPane.add(kupovine, 0, 10, 2, 1);


        bp.setCenter(gridPane);

        Scene sc = new Scene(bp, 600, 600);
        setScene(sc);

        dodajPlanete(JDBCUtils.selectAllFromPlanete());
        dodajStanove(JDBCUtils.selectAllFromStanovi());
        dodajPrevoz(JDBCUtils.izvuciPrevoz());
    }
    public static MainView getInstance() {
        if (mainView == null) {
            mainView = new MainView();

        }
        return mainView;
    }
    public void dodajPlanete(Collection<Planeta> planete){
        planetaComboBox.getItems().addAll(planete);
        planetaComboBox.getSelectionModel().select(0);
    }
    public void setKorisnikName(String korIme){
        String ime_prezime = JDBCUtils.izvuciImeIPrezime(korIme);
        korisnikLabel.setText("Korisnik: "+ime_prezime);
    }
    public void dodajStanove(Collection<Stan> s){
        stanComboBox.getItems().addAll(s);
        stanComboBox.getSelectionModel().select(0);
    }
    public void dodajPrevoz(Collection<String>prevozi){
        prevozComboBox.getItems().addAll(prevozi);
        prevozComboBox.getSelectionModel().select(0);
    }

    public void setKorisnikLabel(Label korisnikLabel) {
        this.korisnikLabel = korisnikLabel;
    }

    public ComboBox<Planeta> getPlanetaComboBox() {
        return planetaComboBox;
    }

    public ComboBox<Stan> getStanComboBox() {
        return stanComboBox;
    }

    public DatePicker getDatumPolaskaField() {
        return datumPolaskaField;
    }

    public ComboBox<String> getPrevozComboBox() {
        return prevozComboBox;
    }

    public TextField getImeField() {
        return imeField;
    }

    public TextField getPrezimeField() {
        return prezimeField;
    }
}
