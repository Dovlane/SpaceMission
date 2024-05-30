package zus.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import zus.view.MainView;
import zus.view.PregledView;

public class PregledKupnjiController implements EventHandler<ActionEvent> {
    MainView mv;
    public PregledKupnjiController(MainView mv) {
        this.mv = mv;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        PregledView pv = new PregledView();
        pv.show();
    }
}
