package tests;

import modele.Point;

import static org.junit.Assert.assertEquals;

public class PointTest {
    @org.junit.Test
    public void distance() {
        Point p = new Point(0, 0);
        Point p2 = new Point(4, 4);

        double res = p.distance(p2);
        int arrondiRes = (int) (res);
        double attendu = Math.sqrt(32);

        assertEquals("Le resultat devrait etre le meme nombreExact", attendu, res, 0.01);
        assertEquals("Le resultat devrait etre le meme arrondi", 5, arrondiRes);

    }

    @org.junit.Test
    public void distanceNegative() {
        Point p = new Point(0, 0);
        Point p2 = new Point(-2, -2);

        double res = p.distance(p2);
        int arrondiRes = (int) (res);

        assertEquals("Le resultat devrait etre le meme nombreExact", 0, res, 0.01);
        assertEquals("Le resultat devrait etre le meme arrondi", 0, arrondiRes);

    }

    @org.junit.Test
    public void incrementerX() {
        Point p = new Point(0, 0);

        p.incrementerX(1);

        assertEquals("Le x devrait etre 1", 1, p.rendreX());
    }

    @org.junit.Test
    public void incrementerXNegatif() {
        Point p = new Point(0, 0);

        p.incrementerX(-1);

        assertEquals("Le x devrait etre 0", 0, p.rendreX());
    }

    @org.junit.Test
    public void incrementerY() {
        Point p = new Point(0, 0);

        p.incrementerY(1);

        assertEquals("Le y devrait etre 1", 1, p.rendreY());
    }

    @org.junit.Test
    public void incrementerYNegatif() {
        Point p = new Point(0, 0);

        p.incrementerY(-1);

        assertEquals("Le y devrait etre 0", 0, p.rendreY());
    }

    @org.junit.Test
    public void modifierX() {
        Point p = new Point(1, 0);

        p.modifierX(3);

        assertEquals("Le x devrait etre 3", 3, p.rendreX());
    }

    @org.junit.Test
    public void modifierXNegatif() {
        Point p = new Point(1, 0);

        p.modifierX(-3);

        assertEquals("Le x devrait etre 1", 1, p.rendreX());
    }

    @org.junit.Test
    public void modifierY() {
        Point p = new Point(1, 1);

        p.modifierY(3);

        assertEquals("Le y devrait etre 3", 3, p.rendreY());
    }

    @org.junit.Test
    public void modifierYNegatif() {
        Point p = new Point(1, 1);

        p.modifierY(-3);

        assertEquals("Le y devrait etre 1", 1, p.rendreY());
    }

    @org.junit.Test
    public void translation() {
        Point p = new Point(1, 1);

        p.translation(2, 2);

        assertEquals("Le x devrait etre 3", 3, p.rendreX());
        assertEquals("Le y devrait etre 3", 3, p.rendreY());

    }

    @org.junit.Test
    public void translationXNegatif() {
        Point p = new Point(1, 1);

        p.translation(-2, 2);

        assertEquals("Le x devrait etre :", -1, p.rendreX());
        assertEquals("Le y devrait etre 3", 3, p.rendreY());

    }

    @org.junit.Test
    public void translationYNegatif() {
        Point p = new Point(1, 1);

        p.translation(2, -2);

        assertEquals("Le x devrait etre 3", 3, p.rendreX());
        assertEquals("Le y devrait etre :", -1, p.rendreY());

    }

    @org.junit.Test
    public void translationBothNegatif() {
        Point p = new Point(1, 1);

        p.translation(-2, -2);

        assertEquals("Le x devrait etre :", -1, p.rendreX());
        assertEquals("Le y devrait etre :", -1, p.rendreY());

    }

}