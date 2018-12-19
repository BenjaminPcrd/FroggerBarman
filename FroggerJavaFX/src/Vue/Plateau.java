package Vue;

import Controller.Controller;
import Model.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class Plateau {
    private Group root;
    private Scene scene;
    private Controller controller;
    private ApplicationMediator mediator;

    private ObjectProperty lvl = new SimpleObjectProperty();
    //public final Object getLvl() { return lvl.get(); }
    //public final void setLvl(String value) { lvl.set(value); }
    public ObjectProperty lvlProperty() { return lvl; }

    public Plateau() {
        this.root = new Group();
        this.scene = new Scene(root, 1280, 720);
        this.controller = new Controller();
        this.mediator = new ApplicationMediator();
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Barman Frogger");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        ImageView background = new ImageView(new Image(new FileInputStream("images/fond.png")));
        root.getChildren().add(background);
        controller.setEvenementClavier(scene);

        Button menu = new Button("Retour au menu");
        root.getChildren().add(menu);
        menu.setOnAction(actionEvent -> {
            Main main = new Main();
            try {
                main.start(primaryStage);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        });

        switch((String)lvlProperty().get()) {
            case "Facile":
                System.out.println("Facile");
                Niveau facile = new Niveau(1, 4, 0.5, 1, false, 2, 5, mediator, root, controller, scene);
                facile.generer();
                break;
            case "Normal":
                System.out.println("Normal");
                Niveau normal = new Niveau(0.7, 6, 0.7, 1.5, false, 2, 8, mediator, root, controller, scene);
                normal.generer();
                break;
            case "Hardcore":
                System.out.println("Hardcore");
                Niveau hardcore = new Niveau(0.7, 8, 1, 2, true, 2, 15, mediator, root, controller, scene);
                hardcore.generer();
                break;
            case "DrunkMode":
                System.out.println("DrunkMode");
                Niveau drunkMode = new Niveau(0.5, 8, 1.7, 2.7, true, 2, 20, mediator, root, controller, scene);
                drunkMode.generer();
                break;
        }
    }
}

