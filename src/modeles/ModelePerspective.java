package modeles;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import vues.Observateur;

public class ModelePerspective implements Subject {

  private int zoom;
  private Point translation;
  private final List<Observateur> abonnes;
  private Point p;
  private int wheelRotation;


  //Zoom
  private boolean init = true;
  private int zoomLevel = 0;
  private final int minZoomLevel = -20;
  private final int maxZoomLevel = 10;
  private final double zoomMultiplicationFactor = 1.2;

  //translate
  private Point dragStartScreen;
  private Point dragEndScreen;
  private AffineTransform coordTransform = new AffineTransform();

  public ModelePerspective() {
    this.zoom = 0;
    this.translation = new Point(0, 0);
    this.abonnes = new ArrayList<>();
  }

  public int getZoom() {
    return zoom;
  }

  public void setZoom(int zoom) {
    this.zoom = zoom;
    this.notifyObservers();
  }

  public Point getTranslation() {
    return translation;
  }

  public void setTranslation(Point translation) {
    this.translation = translation;
    this.notifyObservers();
  }

  public void setPoint(Point p) {
    this.p = p;
  }

  public void setWheelRotation(int wheelRotation) {

    this.wheelRotation = wheelRotation;
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

  public void zoom(MouseWheelEvent e) {
    try {
      int wheelRotation = e.getWheelRotation();
      Point p = e.getPoint();
      this.setPoint(p);
      this.setWheelRotation(wheelRotation);

      if (wheelRotation > 0) {
        if (zoomLevel < maxZoomLevel) {
          zoomLevel++;
          Point2D p1 = transformPoint(p);
          coordTransform.scale(1 / zoomMultiplicationFactor, 1 / zoomMultiplicationFactor);
          Point2D p2 = transformPoint(p);
          coordTransform.translate(p2.getX() - p1.getX(), p2.getY() - p1.getY());
          this.notifyObservers();
        }
      } else {
        if (zoomLevel > minZoomLevel) {
          zoomLevel--;
          Point2D p1 = transformPoint(p);
          coordTransform.scale(zoomMultiplicationFactor, zoomMultiplicationFactor);
          Point2D p2 = transformPoint(p);
          coordTransform.translate(p2.getX() - p1.getX(), p2.getY() - p1.getY());
          this.notifyObservers();
        }
      }
    } catch (NoninvertibleTransformException ex) {
      ex.printStackTrace();
    }
  }

  public void translate(MouseEvent e) {
    try {
      this.setDragEndScreen(e.getPoint());
      Point2D.Float dragStart = transformPoint(dragStartScreen);
      Point2D.Float dragEnd = transformPoint(dragEndScreen);
      double dx = dragEnd.getX() - dragStart.getX();
      double dy = dragEnd.getY() - dragStart.getY();
      coordTransform.translate(dx, dy);
      dragStartScreen = dragEndScreen;
      dragEndScreen = null;
      this.notifyObservers();

    } catch (NoninvertibleTransformException ex) {
      ex.printStackTrace();
    }
  }

  private Point2D.Float transformPoint(Point p1) throws NoninvertibleTransformException {
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
