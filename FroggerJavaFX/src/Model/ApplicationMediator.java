package Model;

import java.util.ArrayList;

public class ApplicationMediator implements Mediator {
    private ArrayList<Entite> entites;

    public ApplicationMediator() {
        this.entites = new ArrayList<>();
    }

    public void ajouterEntite(Entite e) {
        entites.add(e);
    }

    public void enleverEntite(Entite e) throws Exception {
        if (entites.contains(e)) {
            entites.remove(e);
        } else {
            throw new Exception("Entite non présente dans le tableau");
        }
    }

    @Override
    public Entite collisionVoiture(Entite e) {
        for (Entite entite : entites) {
            if (e.getRect().intersects(entite.getBoundsInLocal()) && entite != e && entite instanceof Voiture) {
                return entite;
            }
        }
        return null;
    }

    @Override
    public Entite collisionBar(Entite e) {
        for (Entite entite : entites) {
            if (e.getRect().intersects(entite.getBoundsInLocal()) && entite != e && entite instanceof Bar) {
                return entite;
            }
        }
        return null;
    }

    @Override
    public Entite collisionClient(Entite e) {
        for (Entite entite : entites) {
            if (e.getRect().intersects(entite.getBoundsInLocal()) && entite != e && entite instanceof Client) {
                return entite;
            }
        }
        return null;
    }

    @Override
    public Entite collisionTerrePlein(Entite e){
        for (Entite entite : entites) {
            if (e.getRect().intersects(entite.getBoundsInLocal()) && entite != e && entite instanceof TerrePlein) {
                return entite;
            }
        }
        return null;
    }
}
