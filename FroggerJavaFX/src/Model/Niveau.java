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
    private Barman barman;
    private int nbViebarman;

    private ArrayList<Voiture> voitures;
    private ArrayList<TerrePlein> terrePleins;
    private ArrayList<Bar> bars;
    private ArrayList<Client> clients;

    private ApplicationMediator mediator;
    private Group root;
    private Controller controller;
    private Scene scene;

    public Niveau(double speedBarman, int nbVoitures, double minSpeed, double maxSpeed, boolean isTerrePleins, int nbBars, int nbClients, int nbViebarman, ApplicationMediator mediator, Group root, Controller controller, Scene scene) {
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
        this.nbViebarman = nbViebarman;
        this.mediator = mediator;
        this.root = root;
        this.controller = controller;
        this.scene = scene;
    }

    private void genererClient(String nomBoisson){
        double minX = 50;
        double maxX = 1050;
        double minY = 20;
        double maxY = 50;

        Random r = new Random();

        for (int i = 0; i < nbClients/nbBars; i++) {

            double randomX = minX + (maxX - minX) * r.nextDouble();
            double randomY = minY + (maxY - minY) * r.nextDouble();
            double randomSpeed = 0.1 + 0.6 * r.nextDouble();
            Client client = new Client(mediator, -1000, 0, 50, 25, randomSpeed, new Boisson(nomBoisson), "images/client" + (r.nextInt((12 - 1) + 1) + 1) + ".png");
            clients.add(client);
            mediator.ajouterEntite(client);

            root.getChildren().add(client);
            controller.moveClient(client, randomX, randomY, mediator);
        }
    }

    private void genererBar(String nomBoisson, double x){
        Bar bar = new Bar(mediator, x, scene.getHeight()-60, 100, 70, new Boisson(nomBoisson), 50,"images/bar.png");
        mediator.ajouterEntite(bar);
        root.getChildren().add(bar);
        controller.collisionBar(barman);
    }


    public void generer() {
        /*########## Génération du barman ##########*/
        barman = new Barman(mediator, scene.getWidth()/2-25, scene.getHeight()-50, 50, 50,  speedBarman, nbViebarman,  "images/loic.png");
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

        /*########## Génération des bars et des clients ##########*/

        switch (nbBars){
            case 1:
                    genererBar("Bière", 150);
                    genererClient("Bière");
                    break;
            case 2:
                    System.out.println(scene.getWidth());
                    genererBar("Bière", 280);
                    genererBar("Mojito", scene.getWidth()- 320);
                    genererClient("Bière");
                    genererClient("Mojito");


                    break;
            case 3:
                    genererBar("Bière", 100);
                    genererBar("Mojito", scene.getWidth()- 320);
                    genererBar("Limonade",  400);
                    genererClient("Bière");
                    genererClient("Mojito");
                    genererClient("Limonade");
                    break;
            case 4:
                    genererBar("Bière", 100);
                    genererBar("Mojito", scene.getWidth()- 500);
                    genererBar("Limonade",  400);
                    genererBar("Potion Magique", scene.getWidth() - 200);
                    genererClient("Bière");
                    genererClient("Mojito");
                    genererClient("Limonade");
                    genererClient("Potion Magique");
                    break;

        }
        controller.collisionClient(barman);
    }

    public boolean isWin() {
        int nbClientOk = 0;
        for(Client c : clients) {
            if(c.isSatisfait() && c.isArrive()) {
                nbClientOk++;
            }
        }
        return nbClientOk == nbClients;
    }

    public boolean isLose(){
        if(barman.getNbVie() == 0) return true;
        else return false;
    }

    public Barman getBarman() {
        return barman;
    }
}
