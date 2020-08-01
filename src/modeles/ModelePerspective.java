package modeles;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import vues.Observateur;

public class ModelePerspective implements Subject {

  private final List<Observateur> abonnes;


  //Zoom
  private boolean init = true;
  public int zoomLevel;
  public Point pointZoom;
  public Stack<Integer> sauvegardeNiveauxZoom = new Stack<>();
  public Stack<Point> sauvegardePositionsZoom = new Stack<>();


  //translate
  public Point dragStartScreen;
  public Point dragEndScreen;
  public double dx, dy;
  public AffineTransform coordTransform = new AffineTransform();

  public Stack<AbstractMap.SimpleEntry<Point, Point>> sauvegardePositions = new Stack<>();

  public ModelePerspective() {
    this.abonnes = new ArrayList<>();
    this.zoomLevel = 0;
    sauvegardeNiveauxZoom.add(0);
  }


  public void setDragEndScreen(Point p) {
    this.dragEndScreen = p;
  }

  public void setDragStartScreen(Point dragStartScreen) {
    this.dragStartScreen = dragStartScreen;
  }

  @Override
  public void notifyObservers() {
    for (Observateur abonne : this.abonnes) {
      abonne.update();
    }
  }

  public void zoom(MouseWheelEvent e) throws NoninvertibleTransformException {

    int wheelRotation = e.getWheelRotation();
    pointZoom = e.getPoint();

    double zoomMultiplicationFactor = 1.2;
    if (wheelRotation > 0) {
      //zoom out
      zoomLevel--;
      Point2D p1 = transformPoint(pointZoom);
      coordTransform.scale(1 / zoomMultiplicationFactor, 1 / zoomMultiplicationFactor);
      Point2D p2 = transformPoint(pointZoom);
      coordTransform.translate(p2.getX() - p1.getX(), p2.getY() - p1.getY());

    } else {
      //zoom in
      zoomLevel++;

      Point2D p1 = transformPoint(pointZoom);
      coordTransform.scale(zoomMultiplicationFactor, zoomMultiplicationFactor);
      Point2D p2 = transformPoint(pointZoom);
      coordTransform.translate(p2.getX() - p1.getX(), p2.getY() - p1.getY());


    }
    this.notifyObservers();

  }


  public void setZoom() throws NoninvertibleTransformException {
    int zoomAncien = sauvegardeNiveauxZoom.pop();
    int zoomDif = zoomAncien - zoomLevel;
    double zoomMultiplicationFactor = 1.2;
    Point pointZoom = sauvegardePositionsZoom.pop();

    if (zoomDif > 0) {
      for (int i = 0; i < zoomDif; i++) {
        Point2D p1 = transformPoint(pointZoom);
        coordTransform.scale(zoomMultiplicationFactor, zoomMultiplicationFactor);
        Point2D p2 = transformPoint(pointZoom);
        coordTransform.translate(p2.getX() - p1.getX(), p2.getY() - p1.getY());
      }
      this.notifyObservers();
    } else if (zoomDif < 0) {
      for (int i = 0; i > zoomDif; i--) {
        Point2D p1 = transformPoint(pointZoom);
        coordTransform.scale(1 / zoomMultiplicationFactor, 1 / zoomMultiplicationFactor);
        Point2D p2 = transformPoint(pointZoom);
        coordTransform.translate(p2.getX() - p1.getX(), p2.getY() - p1.getY());
      }
      this.notifyObservers();
    }
    zoomLevel = zoomAncien;
  }

  public void setZoom2() throws NoninvertibleTransformException {
    int zoomAncien = sauvegardeNiveauxZoom.pop();
    int zoomDif = zoomLevel - zoomAncien;
    double zoomMultiplicationFactor = 1.2;
    //Point pointZoom = sauvegardePositionsZoom.pop();

    if (zoomDif > 0) {
      for (int i = 0; i < zoomDif; i++) {
        //Point2D p1 = transformPoint(pointZoom);
        coordTransform.scale(zoomMultiplicationFactor, zoomMultiplicationFactor);
        //Point2D p2 = transformPoint(pointZoom);
        //coordTransform.translate(p2.getX() - p1.getX(), p2.getY() - p1.getY());
      }
      this.notifyObservers();
    } else if (zoomDif < 0) {
      for (int i = 0; i > zoomDif; i--) {
        //Point2D p1 = transformPoint(pointZoom);
        coordTransform.scale(1 / zoomMultiplicationFactor, 1 / zoomMultiplicationFactor);
        //Point2D p2 = transformPoint(pointZoom);
        //coordTransform.translate(p2.getX() - p1.getX(), p2.getY() - p1.getY());
      }
      this.notifyObservers();
    }
    sauvegardeNiveauxZoom.add(zoomLevel);
    //zoomLevel = zoomAncien;
  }


  public void translate(MouseEvent e) {
    try {
      this.setDragEndScreen(e.getPoint());
      Point2D.Float dragStart = transformPoint(dragStartScreen);
      Point2D.Float dragEnd = transformPoint(dragEndScreen);
      this.dx = dragEnd.getX() - dragStart.getX();
      this.dy = dragEnd.getY() - dragStart.getY();
      coordTransform.translate(dx, dy);
      dragStartScreen = dragEndScreen;
      this.notifyObservers();

    } catch (NoninvertibleTransformException ex) {
      ex.printStackTrace();
    }
  }


  public Point2D.Float transformPoint(Point p1) throws NoninvertibleTransformException {
    AffineTransform inverse = coordTransform.createInverse();
    Point2D.Float p2 = new Point2D.Float();
    inverse.transform(p1, p2);
    return p2;
  }

  @Override
  public void addObserver(Observateur observer) {
    this.abonnes.add(observer);
  }

  public boolean isInit() {
    return init;
  }

  public void setInit(boolean init) {
    this.init = init;
  }

  public AffineTransform getCoordTransform() {
    return coordTransform;
  }

  public void setCoordTransform(AffineTransform coordTransform) {
    this.coordTransform = coordTransform;
  }
}
