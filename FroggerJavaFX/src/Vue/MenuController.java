package Vue;

import Model.Scores;
import Model.Score;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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

   public void buttonRegles(ActionEvent actionEvent){
        Stage stageRegles = new Stage();
        try {
            Scene sceneRegles = new Scene(FXMLLoader.load(getClass().getResource("Regles.fxml")));
            stageRegles.setScene(sceneRegles);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stageRegles.initModality(Modality.APPLICATION_MODAL);
        stageRegles.show();
    }


}
