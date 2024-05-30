package zus.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import zus.controller.PregledKupnjiController;
import zus.entity.*;
import zus.model.utility.JDBCUtils;

import java.time.LocalDate;

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
        planetaComboBox = new ComboBox<>(JDBCUtils.getPlanete());
        planetaComboBox.setOnAction((e) -> {
            JDBCUtils.setPlanetaId(planetaComboBox.getSelectionModel().getSelectedItem().getId_planete());
            MainView.getInstance().ispuniCheckBoxove();
        });
        Label odaberiStanLabel = new Label("Odaberi stan");
        stanComboBox = new ComboBox<>(JDBCUtils.getStanbeniObjekti());
        Label datumPolaskaLabel = new Label("Datum polaska");
        datumPolaskaField = new DatePicker();
        Label odaberitePrevozLabel = new Label("Odaberite prevoz");
        prevozComboBox = new ComboBox<>(JDBCUtils.getPrevozi());
        Label imeLabel = new Label("Ime putnika");
        imeField = new TextField();
        Label prezimeLabel = new Label("Prezime putnika");
        prezimeField = new TextField();
        Button pregledKupovinaButton = new Button("Pregled kupovina");
        pregledKupovinaButton.setOnAction(new PregledKupnjiController(this));
        Button kupiKartuButton = new Button("Kupi kartu");
        Label praznaLabel = new Label();
        Label sveKupovineLabel = new Label("Sve kupovine");
        TableView<PrikazPutovanja> kupovine = new TableView<>();

        TableColumn<PrikazPutovanja, String> kol1 = new TableColumn<>("ime putnika");
        TableColumn<PrikazPutovanja, String> kol2 = new TableColumn<>("prezime putnika");
        TableColumn<PrikazPutovanja, String> kol3 = new TableColumn<>("planeta");
        TableColumn<PrikazPutovanja, String> kol4 = new TableColumn<>("stan");
        TableColumn<PrikazPutovanja, LocalDate> kol5 = new TableColumn<>("polazak");
        TableColumn<PrikazPutovanja, String> kol6 = new TableColumn<>("prevoz");


        kol1.setCellValueFactory(new PropertyValueFactory<>("ime_putnika"));
        kol2.setCellValueFactory(new PropertyValueFactory<>("prezime_putnika"));
        kol3.setCellValueFactory(new PropertyValueFactory<>("naziv_planete"));
        kol4.setCellValueFactory(new PropertyValueFactory<>("naziv_stana"));
        kol5.setCellValueFactory(new PropertyValueFactory<>("datum_i_vreme"));
        kol6.setCellValueFactory(new PropertyValueFactory<>("prevoz"));

        kupovine.getColumns().addAll(kol1, kol2, kol3, kol4, kol5, kol6);
        kupovine.setItems(JDBCUtils.getPutovanja());
        //ime_putnika+" "+prezime_putnika+" "+naziv_planete+" "+naziv_stana+" "+datum_i_vreme+" "+prevoz+"



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

        this.show();
    }
    public static MainView getInstance() {
        if (mainView == null) {
            mainView = new MainView();
        }
        return mainView;
    }

    public void ispuniCheckBoxove() {
        JDBCUtils.selectNastanjivePlanete();
        JDBCUtils.selectAllFromStanovi();
    }

    public void setKorisnikName(int korisnickiId) {
        String ime_prezime = JDBCUtils.izvuciImeIPrezime(korisnickiId);
        korisnikLabel.setText("Korisnik: "+ime_prezime);
    }

}
