package modele;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Rectangle.
 */
public class Rectangle extends Quadrilatere implements Serializable {

    /**
     * Instantiates a new Rectangle.
     */
    public Rectangle() {
        super();
    }

    @Override
    public int nbClics() {
        return 2;
    }

    @Override
    public void modifierPoints(ArrayList<Point> tabP) {

        ArrayList<Point> points;
        if (tabP.size() == 2) {
            this.p = new Polygon();
            points = new ArrayList<>();

            Point p1 = tabP.get(0);
            Point p2 = tabP.get(1);

            if (p1.rendreX() > p2.rendreX() || p1.rendreY() > p2.rendreY()) {
                Point ptmp = p1;
                p1 = p2;
                p2 = ptmp;
            }

            points.add(new Point(p1.rendreX(), p1.rendreY()));
            double longueur = p2.rendreY() - p1.rendreY();
            double hauteur = p2.rendreX() - p1.rendreX();

            this.p.addPoint(p1.rendreX(), p1.rendreY());

            points.add(new Point((int) (p1.rendreX() + hauteur), p1.rendreY()));
            this.p.addPoint((int) (p1.rendreX() + hauteur), p1.rendreY());

            points.add(new Point((int) (p1.rendreX() + hauteur), (int) (p1.rendreY() + longueur)));
            this.p.addPoint((int) (p1.rendreX() + hauteur), (int) (p1.rendreY() + longueur));

            points.add(new Point(p1.rendreX(), (int) (p1.rendreY() + longueur)));
            this.p.addPoint(p1.rendreX(), (int) (p1.rendreY() + longueur));
            super.modifierPoints(points);
        } else {
            super.modifierPoints(this.tab_mem);
        }


    }
}
