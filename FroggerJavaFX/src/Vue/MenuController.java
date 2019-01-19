package Vue;

import Model.Scores;
import Model.Score;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    ChoiceBox lvl;

    @FXML
    ListView listeScores;

    @FXML
    TextField text1;

    @FXML
    TextField text2;

    private ListProperty<Score> listProperty = new SimpleListProperty<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Scores.loadMesScores("saveScores.bin");
        listProperty.set(FXCollections.observableArrayList(Scores.getMesScores("Facile")));
        listeScores.itemsProperty().bind(listProperty);

        text1.textProperty().bindBidirectional(text2.textProperty());
    }

    public void updateLvl(ActionEvent actionEvent) {
        listProperty.set(FXCollections.observableArrayList(Scores.getMesScores(String.valueOf(lvl.getValue()))));
        System.out.println();
    }

    public void buttonJouer(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Button) actionEvent.getSource()).getScene().getWindow();
        Plateau p = new Plateau();

        p.getLvl().bind(lvl.valueProperty());
        p.getPseudo().bind(text1.textProperty());
        try {
            p.start(primaryStage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
