package zus.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PregledView extends Stage {

    public PregledView() {

        TableView<String> tableView = new TableView<>();


        TableColumn<String, String> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<String, String> column2 = new TableColumn<>("Description");
        column2.setCellValueFactory(new PropertyValueFactory<>("description"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);


        StackPane root = new StackPane();
        root.getChildren().add(tableView);

        Scene scene = new Scene(root, 500, 500);


        this.setTitle("Pregled View");
        this.setScene(scene);
    }

}
