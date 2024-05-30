package zus.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import zus.model.utility.JDBCUtils;

public class RegistrationView extends Stage {


    public RegistrationView() {
        // Create labels
        Label nameLabel = new Label("Ime:");
        Label surnameLabel = new Label("Prezime:");
        Label usernameLabel = new Label("Korisničko ime:");
        Label passwordLabel = new Label("Lozinka:");

        // Create text fields
        TextField nameTextField = new TextField();
        TextField surnameTextField = new TextField();
        TextField usernameTextField = new TextField();
        TextField passwordTextField = new PasswordField();

        // Create button
        Button okButton = new Button("OK");
        okButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                String ime = nameTextField.getText();
                String prezime = surnameTextField.getText();
                String korisnikoIme = usernameTextField.getText();
                String lozinka = passwordTextField.getText();

                if(ime.isEmpty() || prezime.isEmpty() || korisnikoIme.isEmpty() || lozinka.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Upozorenje");
                    alert.setHeaderText(null);
                    alert.setContentText("Popunite sva polja");
                    alert.showAndWait();
                }else{
                    if(JDBCUtils.proveriDaLiSadrzi(korisnikoIme)){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Upozorenje");
                        alert.setHeaderText(null);
                        alert.setContentText("Niste jedini..pokusaj opet");
                        alert.showAndWait();
                    }else{

                        JDBCUtils.insertIntoKorisnik(ime,prezime,korisnikoIme,lozinka);

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Uspesna registracija");
                        alert.showAndWait();
                        close();
                    }

                }

            }
        });


        // Create layout and add elements
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameTextField, 1, 0);
        gridPane.add(surnameLabel, 0, 1);
        gridPane.add(surnameTextField, 1, 1);
        gridPane.add(usernameLabel, 0, 2);
        gridPane.add(usernameTextField, 1, 2);
        gridPane.add(passwordLabel, 0, 3);
        gridPane.add(passwordTextField, 1, 3);
        gridPane.add(okButton, 1, 4);

        // Create VBox to center the GridPane
        VBox vbox = new VBox(gridPane);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefSize(500, 400);

        // Create scene and set stage
        Scene scene = new Scene(vbox);
        this.setTitle("Registracija");
        this.setScene(scene);
    }


}
