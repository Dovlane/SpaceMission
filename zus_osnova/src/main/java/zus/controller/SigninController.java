package zus.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import zus.entity.Korisnik;
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
            Korisnik k = JDBCUtils.proveriDaLiSadrzi(korisnickoIme);
            if (k != null) {
                if (!k.getLozinka().equals(password)) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Error");
                    a.setContentText("Pogresna lozinka - pokušajte ponovo!");
                    a.show();
                    return;
                }
                MainView.getInstance().show();
                JDBCUtils.setKorisnicki_id(k.getId_korisnik());
                MainView.getInstance().setKorisnikName(k.getId_korisnik());
                JDBCUtils.prikaziTrenutnaPutovanja();
                MainView.getInstance().ispuniCheckBoxove();
            }else{
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("Niste registrovani..");
                a.show();
            }
        }
    }
}
