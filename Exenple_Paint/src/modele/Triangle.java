package modele;

import java.io.Serializable;

/**
 * The type Triangle.
 */
public class Triangle extends Polygone implements Serializable {
    /**
     * Instantiates a new Triangle.
     */
    public Triangle() {
        super();
    }

    /**
     * Methode retournant  le nombre de cliques nécessaires pour réaliser la figure
     *
     * @return Nombre de cliques nécessaires pour réaliser la figure
     */
    public int nbClics() {
        return 3;
    }

    /**
     * Methode qui retourne le nombre de cliqué
     *
     * @return le nombre de cliqué
     */
    public int nbPoints() {
        return this.tab_mem.size();
    }
}
