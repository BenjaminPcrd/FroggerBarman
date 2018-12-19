package Vue;

import Controller.Controller;
import Model.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.Random;

public class Plateau {
    private Group root;
    private Scene scene;
    private Controller controller;
    private ApplicationMediator mediator;
    private Barman barman;

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

        switch((String)lvlProperty().get()) {
            case "Facile":
                System.out.println("Facile");
                Niveau facile = new Niveau(1, 4, 0.5, 1, 0, 2, 3);
                loadLevel(facile);
                break;
            case "Normal":
                System.out.println("Normal");
                Niveau normal = new Niveau(0.7, 6, 0.7, 1.5, 0, 2, 3);
                loadLevel(normal);
                break;
            case "Hardcore":
                System.out.println("Difficile");
                Niveau hardcore = new Niveau(0.5, 8, 1, 2, 0, 2, 3);
                loadLevel(hardcore);
                break;
            case "Bouré":
                System.out.println("Bouré");
                Niveau boure = new Niveau(0.2, 8, 1.5, 2.5, 0, 2, 3);
                loadLevel(boure);
                break;
        }



        Boisson biere = new Boisson("biere");
        Bar bar = new Bar(mediator, 199, scene.getHeight()-60, 50, 50, biere, 10,"images/bar.png");
        //b.ajouterBoisson(bar, bar.getBoisson());
        mediator.ajouterEntite(bar);
        root.getChildren().add(bar);
        Random rClient = new Random();
        double valeurMin = 20;
        double valeurMax = 1260;
        for(int i = 1 ; i<= 10; i++){
            Client client = new Client(mediator, -1500 + (-50 - -1500 ) * rClient.nextDouble(), scene.getHeight()-670, 50, 50, 1, bar.getBoisson(),"images/loic.png");
            mediator.ajouterEntite(client);
            root.getChildren().add(client);

            double valeur = valeurMin + (valeurMax - valeurMin ) * rClient.nextDouble();
            controller.moveClientArrive(client, valeur, 0);
        }


        controller.collisionClient(this, barman, mediator);
        controller.collisionBar(barman);

    }

    public void loadLevel(Niveau niveau) {
        Random r = new Random();
        double min = niveau.getMinSpeed();
        double max = niveau.getMaxSpeed();

        int index = 1;
        for(int i = 1; i <= niveau.getNbVoitures(); i++) {
            Voiture v;
            if(index == 1 || index == 2) {
                v = new Voiture(mediator, i * 250, 125 * index, 200, 100, min + (max - min) * r.nextDouble(),"LEFT", "images/voiture" + index + ".png");
            } else {
                v = new Voiture(mediator, i * 250, 125 * index, 200, 100, min + (max - min) * r.nextDouble(), "RIGHT", "images/voiture" + index + ".png");
            }
            niveau.ajouterVoiture(v);
            mediator.ajouterEntite(v);
            root.getChildren().add(v);
            controller.moveVoiture(scene, v);

            index++;
            if(index > 4) {
                index = 1;
            }
        }
        this.barman = new Barman(mediator, scene.getWidth()/2-25, scene.getHeight()-50, 50, 50, niveau.getSpeedBarman(), "images/loic.png");
        mediator.ajouterEntite(barman);
        root.getChildren().add(barman);
        controller.updateBarman(scene, barman);


        controller.collisionEntreVoiture(scene, niveau.getVoitures(), (max-min)/2, max, min);
        controller.collisionObstacle(scene, barman);
    }

    public void enlever(Object o){
        root.getChildren().remove(o);
    }


}

