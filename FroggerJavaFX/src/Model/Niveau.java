package Model;

import javafx.scene.Group;

import java.util.ArrayList;

public class Niveau extends Group {
    private double speedBarman;
    private int nbVoitures;
    private double minSpeed;
    private double maxSpeed;
    private int nbTerrePleins;
    private int nbBars;
    private int nbClients;

    private ArrayList<Voiture> voitures;
    private ArrayList<TerrePlein> terrePleins;
    private ArrayList<Bar> bars;
    private ArrayList<Client> clients;

    public Niveau(double speedBarman, int nbVoitures, double minSpeed, double maxSpeed, int nbTerrePleins, int nbBars, int nbClients) {
        this.speedBarman  = speedBarman;
        this.nbVoitures = nbVoitures;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
        this.nbTerrePleins = nbTerrePleins;
        this.nbBars = nbBars;
        this.nbClients = nbClients;
        this.voitures = new ArrayList<>();
        this.terrePleins = new ArrayList<>();
        this.bars = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    public void ajouterVoiture(Voiture v) {
        voitures.add(v);
    }

    public void ajouterTerrePleins(TerrePlein t) {
        terrePleins.add(t);
    }

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

        for(TerrePlein t : terrePleins) {
            this.getChildren().add(t);
        }

        for(Bar b : bars) {
            this.getChildren().add(b);
        }

        for(Client c : clients) {
            this.getChildren().add(c);
        }
    }

    public void stop() {
        for(Voiture v : voitures) {
            this.getChildren().remove(v);
        }

        for(TerrePlein t : terrePleins) {
            this.getChildren().remove(t);
        }

        for(Bar b : bars) {
            this.getChildren().remove(b);
        }

        for(Client c : clients) {
            this.getChildren().remove(c);
        }
    }

    public double getSpeedBarman() {
        return speedBarman;
    }

    public void setSpeedBarman(double speedBarman) {
        this.speedBarman = speedBarman;
    }

    public int getNbVoitures() {
        return nbVoitures;
    }

    public void setNbVoitures(int nbVoitures) {
        this.nbVoitures = nbVoitures;
    }

    public double getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(double minSpeed) {
        this.minSpeed = minSpeed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getNbTerrePleins() {
        return nbTerrePleins;
    }

    public void setNbTerrePleins(int nbTerrePleins) {
        this.nbTerrePleins = nbTerrePleins;
    }

    public int getNbBars() {
        return nbBars;
    }

    public void setNbBars(int nbBars) {
        this.nbBars = nbBars;
    }

    public int getNbClients() {
        return nbClients;
    }

    public void setNbClients(int nbClients) {
        this.nbClients = nbClients;
    }

    public ArrayList<Voiture> getVoitures() {
        return voitures;
    }

    public void setVoitures(ArrayList<Voiture> voitures) {
        this.voitures = voitures;
    }

    public ArrayList<TerrePlein> getTerrePleins() {
        return terrePleins;
    }

    public void setTerrePleins(ArrayList<TerrePlein> terrePleins) {
        this.terrePleins = terrePleins;
    }

    public ArrayList<Bar> getBars() {
        return bars;
    }

    public void setBars(ArrayList<Bar> bars) {
        this.bars = bars;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }
}
