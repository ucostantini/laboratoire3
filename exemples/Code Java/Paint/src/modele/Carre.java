package modele;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;


public class Carre extends Rectangle implements Serializable {


    public Carre() {
        super();
    }

    @Override
    public void modifierPoints(ArrayList<Point> tabP) {
        ;
        if (tabP.size() == 2) {
            this.p = new Polygon();
            ArrayList<Point> points = new ArrayList<>();

            Point p1 = tabP.get(0);
            Point p2 = tabP.get(1);
            if (p1.rendreX() > p2.rendreX() || p1.rendreY() > p2.rendreY()) {
                Point ptmp = p1;
                p1 = p2;
                p2 = ptmp;
            }
            points.add(new Point(p1.rendreX(), p1.rendreY()));
            double cote = p1.distance(p2);
            this.p.addPoint(p1.rendreX(), p1.rendreY());

            points.add(new Point((int) (p1.rendreX() + cote), p1.rendreY()));
            this.p.addPoint((int) (p1.rendreX() + cote), p1.rendreY());

            points.add(new Point((int) (p1.rendreX() + cote), (int) (p1.rendreY() + cote)));
            this.p.addPoint((int) (p1.rendreX() + cote), (int) (p1.rendreY() + cote));

            points.add(new Point(p1.rendreX(), (int) (p1.rendreY() + cote)));
            this.p.addPoint(p1.rendreX(), (int) (p1.rendreY() + cote));
            tab_mem = new ArrayList<>(points);


        } else {
            super.modifierPoints(this.tab_mem);
        }


    }
}
