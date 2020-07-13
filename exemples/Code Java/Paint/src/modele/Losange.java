package modele;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Losange extends modele.Triangle implements Serializable {


    public Losange() {
        super();
    }


    @Override
    public void modifierPoints(ArrayList<Point> tabP) {

        if (tabP.size() == 3) {
            this.p = new Polygon();
            ArrayList<Point> points = new ArrayList<>();

            Point p1 = tabP.get(0);
            Point p2 = tabP.get(1);
            Point p3 = tabP.get(2);
            if (p1.rendreX() > p2.rendreX() || p1.rendreY() > p2.rendreY()) {
                Point ptmp = p1;
                p1 = p2;
                p2 = ptmp;
            } else if (p1.rendreX() > p3.rendreX() || p1.rendreY() > p3.rendreY()) {
                Point ptmp = p1;
                p1 = p3;
                p3 = ptmp;
            }

            points.add(new Point(p1.rendreX(), p1.rendreY()));
            double cote1 = p1.distance(p2);
            double cote2 = p2.distance(p3);
            this.p.addPoint(p1.rendreX(), p1.rendreY());

            Point p2Poly = new Point((int) (p1.rendreX() + cote1), (int) (p1.rendreY() + cote2));
            points.add(p2Poly);
            this.p.addPoint((int) (p1.rendreX() + cote1), (int) (p1.rendreY() + cote2));

            Point p3Poly = new Point((int) (p1.rendreX() + cote1 * 2), (p1.rendreY()));
            points.add(p3Poly);
            this.p.addPoint((int) (p1.rendreX() + cote1 * 2), (p1.rendreY()));

            Point p4Poly = new Point((int) (p1.rendreX() + cote1), (int) (p1.rendreY() - cote2));
            points.add(p4Poly);
            this.p.addPoint((int) (p1.rendreX() + cote1), (int) (p1.rendreY() - cote2));
            super.modifierPoints(points);

        } else {
            super.modifierPoints(this.tab_mem);
        }


    }
}
