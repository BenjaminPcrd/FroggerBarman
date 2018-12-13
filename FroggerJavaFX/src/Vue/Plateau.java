package Vue;

import Controller.Controller;
import Model.*;
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

    public Plateau() {
        this.root = new Group();
        this.scene = new Scene(root, 1280, 720);
        this.controller = new Controller();
        this.mediator = new ApplicationMediator();
        this.barman = new Barman(mediator, scene.getWidth()/2-25, scene.getHeight()-50, 50, 50, 1.4, "images/loic.png");
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Barman Frogger");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        ImageView background = new ImageView(new Image(new FileInputStream("images/fond.png")));
        root.getChildren().add(background);
        controller.setEvenementClavier(scene);
        mediator.ajouterEntite(barman);
        root.getChildren().add(barman);
        controller.updateBarman(scene, barman);

        Niveau facile = new Niveau(4, 0, 2, 3);
        loadLevel(facile);


        /*Boisson biere = new Boisson("biere");
        Bar bar = new Bar(mediator, 199, scene.getHeight()-60, 50, 50, biere, 10,"images/bar.png");
        //b.ajouterBoisson(bar, bar.getBoisson());
        mediator.ajouterEntite(bar);
        root.getChildren().add(bar);

        Client client = new Client(mediator, 9, scene.getHeight()-50, 50, 50, 1, bar.getBoisson(),"images/loic.png");
        mediator.ajouterEntite(client);
        root.getChildren().add(client);

        c.collisionClient(this, b);
        c.collisionBar(scene, b);*/

    }

    public void loadLevel(Niveau niveau) {
        Random r = new Random();

        int index = 1;
        for(int i = 1; i <= niveau.getNbVoitures(); i++) {
            niveau.ajouterVoiture(new Voiture(mediator, index * 200, 125 * index, 200, 100, 1 + (1.3 - 0.3) * r.nextDouble(), "images/voiture" + i + ".png"));

            index++;
            if(index > 4) {
                index = 1;
            }
        }

        for (Voiture v : niveau.getVoitures()) {
            mediator.ajouterEntite(v);
            root.getChildren().add(v);
            controller.moveVoiture(scene, v, "RIGHT");
        }

        controller.collisionObstacle(scene, barman);
    }

    public void enlever(Object o){
        root.getChildren().remove(o);
    }


}

