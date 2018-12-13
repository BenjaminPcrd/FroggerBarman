package Model;

public interface Mediator {
    Entite collisionVoiture(Entite e);
    Entite collisionBar(Entite e);
    Entite collisionClient(Entite e);
    Entite collisionTerrePlein(Entite e);
}
