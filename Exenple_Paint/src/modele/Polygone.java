package modele;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Polygone, superclasse
 */
public abstract class Polygone extends FigureColoree implements Serializable {

    protected Polygon p;

    /**
     * Constructeur d un polygone
     */
    public Polygone() {
        super();
        this.p = new Polygon();
    }

    /**
     * Permet d afficher la figure
     *
     * @param g Graphics
     */
    public void affiche(Graphics g) {
        g.setColor(this.couleur);
        g.fillPolygon(this.p);
        if (this.selected) {
            g.setColor(Color.RED);
            g.drawPolygon(this.p);
        }
    }

    /**
     * Permet de modifier les points d un polygone et de les attribuer a l attibut Polygon p
     *
     * @param tabP la nouvelle liste de Point
     */
    public void modifierPoints(ArrayList<Point> tabP) {
        this.p = new Polygon();
        this.tab_mem = new ArrayList<>(tabP);
        for (Point pcourant : tabP) {
            this.p.addPoint(pcourant.rendreX(), pcourant.rendreY());
        }
    }

    /**
     * Methode permettant de retourner si les coordonnees sont dans le polygone
     *
     * @param x coordoonees sur xdu clic
     * @param y coordoonees sur y du clic
     * @return boolean
     */
    public boolean contient(int x, int y) {
        return this.p.contains(x, y);
    }

}
