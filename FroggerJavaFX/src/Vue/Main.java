package Vue;

import Model.*;
import javafx.application.Application;;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        primaryStage.setTitle("Barman Frogger - MenuController");
        Scene scene = new Scene(root, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public void test() {
        Boisson b1 = new Boisson("Biere");
        Boisson b2 = new Boisson("Coca");
        Bar barBiere = new Bar(0, 0, 0, 0, b1, 10, null);
        Bar barCoca = new Bar(0, 0, 0, 0, b2, 10, null);
        Barman loic = new Barman(0, 0, 0, 0, 0, "images/loifffc.png");
        try {
            loic.ajouterBoisson(barBiere, barBiere.getBoisson());
            loic.ajouterBoisson(barBiere, barBiere.getBoisson());
            loic.ajouterBoisson(barCoca, barCoca.getBoisson());
            loic.ajouterBoisson(barCoca, barCoca.getBoisson());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            loic.ajouterBoisson(barCoca, barCoca.getBoisson());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(barBiere.getNbBoisson());
        System.out.println(loic.getPlateau());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
