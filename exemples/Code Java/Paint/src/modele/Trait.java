package modele;

import java.awt.*;
import java.util.ArrayList;

/**
 * The type Trait.
 */
public class Trait extends FigureColoree {

    /**
     * Instantiates a new Trait.
     *
     * @param points  the points
     * @param couleur the couleur
     */
    public Trait(ArrayList<Point> points, Color couleur) {
        super();
        this.tab_mem = points;
        this.couleur = couleur;
    }

    @Override
    public int nbClics() {
        return this.tab_mem.size();
    }

    @Override
    public void modifierPoints(ArrayList<Point> tab) {
        this.tab_mem = tab;
    }

    @Override
    public boolean contient(int x, int y) {
        return false;
    }

    @Override
    public void affiche(Graphics g) {
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

}
