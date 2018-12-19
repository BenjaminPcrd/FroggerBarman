package Model;

public interface Mediator {
    void ajouterEntite(Entite e);
    void enleverEntite(Entite e);
    Entite collisionEntite(Entite e);
    Entite collisionVoiture(Entite e);
    Entite collisionBar(Entite e);
    Entite collisionClient(Entite e);
    Entite collisionTerrePlein(Entite e);
}
