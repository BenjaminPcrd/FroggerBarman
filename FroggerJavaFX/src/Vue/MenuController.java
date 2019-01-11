package Vue;

import Model.Scores;
import Model.Score;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    ChoiceBox lvl;

    @FXML
    ListView listeScores;

    private ListProperty<Score> listProperty = new SimpleListProperty<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Scores.loadMesScores("saveScores.bin");
        listProperty.set(FXCollections.observableArrayList(Scores.getMesScores("Facile")));
        listeScores.itemsProperty().bind(listProperty);
    }

    public void updateLvl(ActionEvent actionEvent) {
        listProperty.set(FXCollections.observableArrayList(Scores.getMesScores(String.valueOf(lvl.getValue()))));
        System.out.println();
    }

    public void buttonJouer(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Button) actionEvent.getSource()).getScene().getWindow();
        Plateau p = new Plateau();

        p.lvlProperty().bind(lvl.valueProperty());
        try {
            p.start(primaryStage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
