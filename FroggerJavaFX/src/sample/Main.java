package sample;

import Metier.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group root = new Group();
        Barman b = new Barman(100, 100, 100, 100);
        root.getChildren().add(b);


        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();

        //test();

    }

    public void test() {
        Boisson b1 = new Boisson("Biere");
        Boisson b2 = new Boisson("Coca");
        Bar barBiere = new Bar(0, 0, 0, 0, b1, 10);
        Bar barCoca = new Bar(0, 0, 0, 0, b2, 10);
        Barman loic = new Barman(0, 0, 0, 0);
        try {
            loic.ajouterBoisson(barBiere);
            loic.ajouterBoisson(barBiere);
            loic.ajouterBoisson(barCoca);
            loic.ajouterBoisson(barCoca);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            loic.ajouterBoisson(barCoca);
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
