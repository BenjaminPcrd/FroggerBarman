package Model;

public class Boisson {
    private String nom;

    public Boisson(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Boisson{" +
                "nom='" + nom + '\'' +
                '}';
    }
}
