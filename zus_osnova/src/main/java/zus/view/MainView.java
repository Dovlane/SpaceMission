package zus.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import zus.entity.*;
import zus.model.utility.JDBCUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.IntStream;

public class MainView extends Stage {
    private static MainView mainView;
    Label korisnikLabel;
    ComboBox<Planeta> planetaComboBox;
    ComboBox<Stan> stanComboBox;
    DatePicker datumPolaskaField;
    ComboBox<String> prevozComboBox;

    TextField imeField;
    TextField prezimeField;
    ComboBox<String> vremeComboBox;


    private MainView() {
        this.setTitle("Zivot u svemiru");
        BorderPane bp = new BorderPane();

        korisnikLabel = new Label("Korisnik");
        Label odaberiPlanetuLabel = new Label("Odaberi planetu");
        vremeComboBox = new ComboBox<>();
        vremeComboBox.getItems().addAll("08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00");

        planetaComboBox = new ComboBox<>(JDBCUtils.getPlanete());
        planetaComboBox.setOnAction((e) -> {
            JDBCUtils.setPlanetaId(planetaComboBox.getSelectionModel().getSelectedItem().getId_planete());
            MainView.getInstance().ispuniCheckBoxove();
        });
        Label odaberiStanLabel = new Label("Odaberi stan");
        stanComboBox = new ComboBox<>(JDBCUtils.getStanbeniObjekti());
        Label datumPolaskaLabel = new Label("Datum polaska");
        datumPolaskaField = new DatePicker();

        LocalDate minDate = LocalDate.of(2100, 1, 1);
        LocalDate maxDate = LocalDate.of(2200, 12, 31);

        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.isBefore(minDate) || item.isAfter(maxDate)) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                    setTooltip(new Tooltip("Datum van dozvoljenog opsega"));
                }
            }
        };

        datumPolaskaField.setDayCellFactory(dayCellFactory);
        datumPolaskaField.setValue(minDate);

        Label vremePolaskaLabel = new Label("Vreme polaska");


        Label odaberitePrevozLabel = new Label("Odaberite prevoz");
        prevozComboBox = new ComboBox<>(JDBCUtils.getPrevozi());
        Label imeLabel = new Label("Ime putnika");
        imeField = new TextField();
        Label prezimeLabel = new Label("Prezime putnika");
        prezimeField = new TextField();


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

        Button kupiKartuButton = new Button("Kupi kartu");
        kupiKartuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String ime = imeField.getText();
                String prezime = prezimeField.getText();
                Planeta planeta = planetaComboBox.getSelectionModel().getSelectedItem();
                Stan stan = stanComboBox.getSelectionModel().getSelectedItem();
                LocalDate datum = datumPolaskaField.getValue();
                LocalTime vreme = LocalTime.parse(vremeComboBox.getValue());
                String prevoz = prevozComboBox.getSelectionModel().getSelectedItem();

                if(ime.isEmpty() ||prezime.isEmpty()){
                    ime = JDBCUtils.izvuciIme(JDBCUtils.getKorisnickiId());
                    prezime = JDBCUtils.izvuciPrezime(JDBCUtils.getKorisnickiId());
                }

                if ( planeta == null || stan == null || datum == null || vreme == null || prevoz == null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Popunite sva polja", ButtonType.OK);
                    alert.showAndWait();
                    return;
                }

                LocalDateTime datumIVreme = LocalDateTime.of(datum, vreme);

                JDBCUtils.dodajPutovanje(ime, prezime, JDBCUtils.getKorisnickiId(),
                                        stan.getId_objekta(), datumIVreme, prevoz);
                JDBCUtils.prikaziTrenutnaPutovanja();
                kupovine.setItems(JDBCUtils.getPutovanja());

                ispuniCheckBoxove();
            }
        });

        double controlWidth = 200;
        planetaComboBox.setPrefWidth(controlWidth);
        stanComboBox.setPrefWidth(controlWidth);
        prevozComboBox.setPrefWidth(controlWidth);
        datumPolaskaField.setPrefWidth(controlWidth);
        vremeComboBox.setPrefWidth(controlWidth / 2);

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

        gridPane.add(vremePolaskaLabel, 0, 4);
        HBox timeBox = new HBox(10, vremeComboBox);
        gridPane.add(timeBox, 1, 4);

        gridPane.add(odaberitePrevozLabel, 0, 5);
        gridPane.add(prevozComboBox, 1, 5);

        gridPane.add(imeLabel, 0, 6);
        gridPane.add(imeField, 1, 6);

        gridPane.add(prezimeLabel, 0, 7);
        gridPane.add(prezimeField, 1, 7);

        HBox buttonHBox = new HBox(10, kupiKartuButton);
        buttonHBox.setAlignment(Pos.CENTER);

        gridPane.add(buttonHBox, 0, 8, 2, 1);

        gridPane.add(praznaLabel, 0, 9, 2, 1);

        gridPane.add(sveKupovineLabel, 0, 10);
        gridPane.add(kupovine, 0, 11, 2, 1);

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
        korisnikLabel.setText("Korisnik: " + ime_prezime);
    }

    public TextField getImeField() {
        return imeField;
    }

    public TextField getPrezimeField() {
        return prezimeField;
    }

    public ComboBox<Planeta> getPlanetaComboBox() {
        return planetaComboBox;
    }

    public ComboBox<Stan> getStanComboBox() {
        return stanComboBox;
    }

    public ComboBox<String> getPrevozComboBox() {
        return prevozComboBox;
    }
}
