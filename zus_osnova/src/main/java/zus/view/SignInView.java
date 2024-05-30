package zus.view;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import zus.controller.RegController;
import zus.controller.SigninController;

public class SignInView extends Stage {

    private static SignInView instance;
    TextField usernameTextField ;
    TextField passwordTextField ;

    private SignInView() {
        // Create labels
        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");
        Label notLoggedInLabel = new Label("Nisi ulogovan?");

        // Create text fields
        usernameTextField = new TextField();
        passwordTextField = new PasswordField();

        // Create buttons
        Button signInButton = new Button("Sign In");
        signInButton.setOnAction(new SigninController(this));
        Button registerButton = new Button("Registruj se");
        registerButton.setOnAction(new RegController(this));

        // Create layout and add elements
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(usernameTextField, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordTextField, 1, 1);
        gridPane.add(signInButton, 1, 2);
        gridPane.add(notLoggedInLabel, 0, 3);
        gridPane.add(registerButton, 1, 3);

        // Create VBox to center the GridPane
        VBox vbox = new VBox(gridPane);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefSize(400, 300);

        // Create scene and set stage
        Scene scene = new Scene(vbox);
        this.setTitle("Zivot u svemiru");
        this.setScene(scene);
    }

    public static SignInView getInstance() {
        if (instance == null) {
            instance = new SignInView();
        }
        return instance;
    }

    public TextField getUsernameTextField() {
        return usernameTextField;
    }

    public TextField getPasswordTextField() {
        return passwordTextField;
    }
}
