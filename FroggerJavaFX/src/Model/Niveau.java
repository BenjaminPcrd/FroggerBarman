package Model;

import javafx.scene.Group;

import java.util.ArrayList;

public class Niveau extends Group {
    private int nbVoitures;
    private int nbTerrePleins;
    private int nbBars;
    private int nbClients;
    private ArrayList<Voiture> voitures;
    //private ArrayList<TerrePleins> terrePleins;
    private ArrayList<Bar> bars;
    private ArrayList<Client> clients;

    public Niveau(int nbVoitures, int nbTerrePleins, int nbBars, int nbClients) {
        this.nbVoitures = nbVoitures;
        this.nbTerrePleins = nbTerrePleins;
        this.nbBars = nbBars;
        this.nbClients = nbClients;
        this.voitures = new ArrayList<>();
        //this.nbTerrePleins = new ArrayList<>();
        this.bars = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    public void ajouterVoiture(Voiture v) {
        voitures.add(v);
    }

    /*public void ajouterTerrePleins(TerrePlein t) {
        terrePleins.add(t);
    }*/

    public void ajouterBar(Bar b) {
        bars.add(b);
    }

    public void ajouterClient(Client c) {
        clients.add(c);
    }

    public void start() {
        for(Voiture v : voitures) {
            this.getChildren().add(v);
        }

        /*for(TerrePlein t : terrePleins) {
            this.getChildren().add(t);
        }*/

        /*for(Bar b : bars) {
            this.getChildren().add(b);
        }

        for(Client c : clients) {
            this.getChildren().add(c);
        }*/
    }

    public void stop() {
        for(Voiture v : voitures) {
            this.getChildren().remove(v);
        }

        /*for(TerrePlein t : terrePleins) {
            this.getChildren().remove(t);
        }*/

        for(Bar b : bars) {
            this.getChildren().remove(b);
        }

        for(Client c : clients) {
            this.getChildren().remove(c);
        }
    }

    public int getNbVoitures() {
        return nbVoitures;
    }

    public int getNbTerrePleins() {
        return nbTerrePleins;
    }

    public int getNbBars() {
        return nbBars;
    }

    public int getNbClients() {
        return nbClients;
    }

    public ArrayList<Voiture> getVoitures() {
        return voitures;
    }

    /*public ArrayList<TerrePlein> getTerrePleins() {
        return terrePleins;
    }*/

    public ArrayList<Bar> getBars() {
        return bars;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }
}
