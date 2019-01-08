package Vue;

import Model.Scores;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MenuController {

    @FXML
    ChoiceBox lvl;

    @FXML
    ListView listeScores;


    /*public MenuController() {
        listeScores.setItems(Scores.getMesScores());
    }*/

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
