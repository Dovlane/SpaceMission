package zus.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import zus.view.SignInView;
import zus.view.RegistrationView;

public class RegController implements EventHandler<ActionEvent> {
    SignInView mv;

    public RegController(SignInView mv) {
        this.mv = mv;

    }
    @Override
    public void handle(ActionEvent actionEvent) {
        RegistrationView rv = new RegistrationView();
        rv.show();
    }
}
