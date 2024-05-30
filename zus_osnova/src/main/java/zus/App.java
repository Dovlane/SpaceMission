package zus;

import javafx.application.Application;
import javafx.stage.Stage;
import zus.model.utility.JDBCUtils;
import zus.view.SignInView;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        stage = SignInView.getInstance();
        stage.show();
        JDBCUtils.connect();
        //JDBCUtils.selectAllFromKorisnik();
    }
}
