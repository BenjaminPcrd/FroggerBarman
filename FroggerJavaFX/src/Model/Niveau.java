package Model;

import Controller.Controller;
import javafx.scene.Group;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.Random;

public class Niveau extends Group {
    private double speedBarman;
    private int nbVoitures;
    private double minSpeed;
    private double maxSpeed;
    private boolean isTerrePleins;
    private int nbBars;
    private int nbClients;

    private ArrayList<Voiture> voitures;
    private ArrayList<TerrePlein> terrePleins;
    private ArrayList<Bar> bars;
    private ArrayList<Client> clients;

    private ApplicationMediator mediator;
    private Group root;
    private Controller controller;
    private Scene scene;

    public Niveau(double speedBarman, int nbVoitures, double minSpeed, double maxSpeed, boolean isTerrePleins, int nbBars, int nbClients, ApplicationMediator mediator, Group root, Controller controller, Scene scene) {
        this.speedBarman  = speedBarman;
        this.nbVoitures = nbVoitures;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
        this.isTerrePleins = isTerrePleins;
        this.nbBars = nbBars;
        this.nbClients = nbClients;
        this.voitures = new ArrayList<>();
        this.terrePleins = new ArrayList<>();
        this.bars = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.mediator = mediator;
        this.root = root;
        this.controller = controller;
        this.scene = scene;
    }

    public void generer() {
        /*########## Génération du barman ##########*/
        Barman barman = new Barman(mediator, scene.getWidth()/2-25, scene.getHeight()-50, 50, 50, speedBarman, "images/loic.png");
        mediator.ajouterEntite(barman);
        root.getChildren().add(barman);
        controller.updateBarman(scene, barman);
        controller.collisionVoiture(scene, barman);

        /*########## Génération des voitures ##########*/
        Random r = new Random();
        int index = 1;
        for(int i = 1; i <= nbVoitures; i++) {
            Voiture v;
            if(index == 1) {//Pour y : premier cart de la fenêtre - valeur fixe
                v = new Voiture(mediator, i * 250, 180 - 70, 190, 90, minSpeed + (maxSpeed - minSpeed) * r.nextDouble(),"LEFT", "images/voiture" + (r.nextInt((6 - 1) + 1) + 1) + ".png");
            } else if (index == 2) {//premier cart de la fenêtre + valeur fixe
                v = new Voiture(mediator, i * 250, 180 + 70, 190, 90, minSpeed + (maxSpeed - minSpeed) * r.nextDouble(),"LEFT", "images/voiture" + (r.nextInt((6 - 1) + 1) + 1) + ".png");
            } else if (index == 3) {//troisieme cart de la fenêtre - valeur fixe - height
                v = new Voiture(mediator, i * 250, 540 - 70 - 90, 190, 90, minSpeed + (maxSpeed - minSpeed) * r.nextDouble(), "RIGHT", "images/voiture" + (r.nextInt((6 - 1) + 1) + 1) + ".png");
            } else {                //troisieme cart de la fenêtre + valeur fixe - height
                v = new Voiture(mediator, i * 250, 540 + 70 - 90, 190, 90, minSpeed + (maxSpeed - minSpeed) * r.nextDouble(), "RIGHT", "images/voiture" + (r.nextInt((6 - 1) + 1) + 1) + ".png");
            }
            voitures.add(v);
            mediator.ajouterEntite(v);
            root.getChildren().add(v);
            controller.moveVoiture(scene, v);

            index++;
            if(index > 4) {
                index = 1;
            }
        }
        controller.collisionEntreVoiture(voitures, (maxSpeed-minSpeed)/2, maxSpeed, minSpeed);

        /*########## Génération des terres pleins ##########*/
        if (isTerrePleins) {
            TerrePlein t1 = new TerrePlein(mediator, 256, 360 - 10, 256, 20, "images/fond.png");
            terrePleins.add(t1);
            mediator.ajouterEntite(t1);
            root.getChildren().add(t1);
            TerrePlein t2 = new TerrePlein(mediator, 768, 360 - 10, 256, 20, "images/fond.png");
            terrePleins.add(t2);
            mediator.ajouterEntite(t2);
            root.getChildren().add(t2);
        }

        /*########## Génération des bars ##########*/
        Bar bar = new Bar(mediator, 200, scene.getHeight()-60, 50, 50, new Boisson("Bière"), 10,"images/bar.png");
        mediator.ajouterEntite(bar);
        root.getChildren().add(bar);
        controller.collisionBar(barman);


        /*########## Génération des clients ##########*/
        double minX = 20;
        double maxX = 1260;
        double minY = 0;
        double maxY = 50;
        for (int i = 0; i < nbClients; i++) {
            double randomX = minX + (maxX - minX) * r.nextDouble();
            double randomY = minY + (maxY - minY) * r.nextDouble();
            double randomSpeed = minSpeed + (maxSpeed - minSpeed) * r.nextDouble();
            Client client = new Client(mediator, -1000, 0, 50, 50, randomSpeed, new Boisson("Bière"), "images/loic.png");
            mediator.ajouterEntite(client);
            root.getChildren().add(client);
            controller.moveClient(client, randomX, randomY);
        }
        controller.collisionClient(barman);
    }
}
