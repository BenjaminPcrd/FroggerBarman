package Model;

public interface Mediator {
    Entite collisionObstacle(Entite e);
    Entite collisionBar(Entite e);
    Entite collisionClient(Entite e);
}
