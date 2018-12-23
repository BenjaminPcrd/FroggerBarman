package Model;

import java.util.Objects;

public class Boisson {
    private String nom;

    public Boisson(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boisson boisson = (Boisson) o;
        return Objects.equals(nom, boisson.nom);
    }
}
