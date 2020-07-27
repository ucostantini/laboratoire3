package modele;

import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.util.ArrayList;


public class Cercle extends Ellipse implements Serializable {


    public Cercle() {
        super();
    }

    @Override
    public int nbClics() {
        return 2;
    }

    @Override
    public void modifierPoints(ArrayList<Point> tab) {
        Point p1 = tab.get(0);
        Point p2 = tab.get(1);
        if (p1.rendreX() > p2.rendreX() || p1.rendreY() > p2.rendreY()) {
            Point ptmp = p1;
            p1 = p2;
            p2 = ptmp;
        }

        this.largeur = p1.distance(p2);
        this.cercle = new Ellipse2D.Double(p1.rendreX(), p1.rendreY(), this.largeur, this.largeur);
        this.tab_mem = new ArrayList<>(tab);
    }

}
