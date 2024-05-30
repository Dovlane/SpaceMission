package zus.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import zus.model.utility.JDBCUtils;
import zus.view.MainView;
import zus.view.SignInView;

public class SigninController implements EventHandler<ActionEvent> {
    SignInView mv;
    public SigninController(SignInView mv){
        this.mv = mv;
    }
    @Override
    public void handle(ActionEvent actionEvent) {

        String korisnickoIme = mv.getUsernameTextField().getText();
        String password = mv.getPasswordTextField().getText();

        //MainView.getInstance().show();

        if(korisnickoIme.isEmpty() || password.isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setContentText("Prazna polja..");
            a.show();
        }else{

            if(JDBCUtils.proveriDaLiSadrzi(korisnickoIme)){
                MainView.getInstance().show();
                MainView.getInstance().setKorisnikName(mv.getUsernameTextField().getText());

            }else{
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("Niste registrovani..");
                a.show();
            }
        }




    }
}
