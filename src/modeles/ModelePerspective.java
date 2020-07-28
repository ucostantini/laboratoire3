package modeles;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import vues.Observateur;

public class ModelePerspective implements Subject {

  private final List<Observateur> abonnes;


  //Zoom
  private boolean init = true;
  public int zoomLevel = 0;
  public Point pointZoom;
  public int zoomAncien;
  public LinkedList<Point> listPosition = new LinkedList<>();


  //translate
  public Point dragStartScreen;
  public Point dragEndScreen;
  public double dx,dy;
  public AffineTransform coordTransform = new AffineTransform();

  public ModelePerspective() {
    this.abonnes = new ArrayList<>();
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
      pointZoom = e.getPoint();

      double zoomMultiplicationFactor = 1.2;
      if (wheelRotation > 0) {
        int maxZoomLevel = -10;
        if (zoomLevel > maxZoomLevel) {
          zoomLevel--;
          Point2D p1 = transformPoint(pointZoom);
          coordTransform.scale(1 / zoomMultiplicationFactor, 1 / zoomMultiplicationFactor);
          Point2D p2 = transformPoint(pointZoom);
          coordTransform.translate(p2.getX() - p1.getX(), p2.getY() - p1.getY());

          this.notifyObservers();
        }
      } else {

        int minZoomLevel = 10;
        if (zoomLevel < minZoomLevel) {
          zoomLevel++;

          Point2D p1 = transformPoint(pointZoom);
          coordTransform.scale(zoomMultiplicationFactor, zoomMultiplicationFactor);
          Point2D p2 = transformPoint(pointZoom);
          coordTransform.translate(p2.getX() - p1.getX(), p2.getY() - p1.getY());
          this.notifyObservers();
        }
      }
    } catch (NoninvertibleTransformException ex) {
      ex.printStackTrace();
    }
  }


    public void setZoom(int zoom) {
    int zoomDif = zoom - zoomLevel;
    double zoomMultiplicationFactor = 1.2;
    if(zoomDif>0){
      for(int i = 0; i < zoomDif;i++){
        coordTransform.scale(zoomMultiplicationFactor, zoomMultiplicationFactor);
        this.notifyObservers();
      }
    }
    else if(zoomDif<0){
      for(int i = 0; i > zoomDif;i--){
        coordTransform.scale(1/zoomMultiplicationFactor, 1/zoomMultiplicationFactor);
        this.notifyObservers();
      }
    }
      zoomLevel = zoom;
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
      dragEndScreen = null;
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
