package Vue;

import Controller.Controller;
import Model.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("Vue.fxml"));
        Group root = new Group();
        primaryStage.setTitle("Barman Frogger");
        Scene scene = new Scene(root, 1280, 720, Color.LIGHTSTEELBLUE);
        primaryStage.setScene(scene);
        //primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.show();

        ImageView background = new ImageView(new Image(new FileInputStream("images/fond.png")));
        root.getChildren().add(background);

        Barman b = new Barman(scene.getWidth()/2-25, scene.getHeight()-50, 50, 50, 0.5, "images/loic.png");
        root.getChildren().add(b);
        Controller c = new Controller();
        c.update(scene, b);

        //test();
    }

    public void test() {
        Boisson b1 = new Boisson("Biere");
        Boisson b2 = new Boisson("Coca");
        Bar barBiere = new Bar(0, 0, 0, 0, b1, 10, null);
        Bar barCoca = new Bar(0, 0, 0, 0, b2, 10, null);
        Barman loic = new Barman(0, 0, 0, 0, 0, "images/loifffc.png");
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
