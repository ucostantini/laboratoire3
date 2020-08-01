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

	// Zoom
	private boolean init = true;
	public int niveauZoom;
	public Point pointZoom;
	public Stack<Integer> sauvegardeNiveauxZoom = new Stack<>();
	public Stack<Point> sauvegardePositionsZoom = new Stack<>();
	public double puissanceZoom = 1.2;

	// translate
	public Point ecranDebutSouris;
	public Point ecranFinSouris;
	public AffineTransform coordTransform = new AffineTransform();

	public Stack<AbstractMap.SimpleEntry<Point, Point>> sauvegardePositions = new Stack<>();

	public ModelePerspective() {
		this.abonnes = new ArrayList<>();
		this.niveauZoom = 0;
		sauvegardeNiveauxZoom.add(0);
	}

	public void setEcranFinSouris(Point p) {
		this.ecranFinSouris = p;
	}

	public void setEcranDebutSouris(Point dragStartScreen) {
		this.ecranDebutSouris = dragStartScreen;
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

		if (wheelRotation > 0) {
			// zoom out
			niveauZoom--;
			zoomOut(pointZoom);
		} else {
			// zoom in
			niveauZoom++;
			zoomIn(pointZoom);
		}
		this.notifyObservers();
	}

	public void setZoom() throws NoninvertibleTransformException {
		int zoomAncien = sauvegardeNiveauxZoom.pop();
		int zoomDif = zoomAncien - niveauZoom;
		Point pointZoom = sauvegardePositionsZoom.pop();

		if (zoomDif > 0) {
			for (int i = 0; i < zoomDif; i++) {
				zoomIn(pointZoom);
			}
		} else if (zoomDif < 0) {
			for (int i = 0; i > zoomDif; i--) {
				zoomOut(pointZoom);
			}
		}
		this.notifyObservers();
		niveauZoom = zoomAncien;
	}

	private void zoomIn(Point pointZoom) throws NoninvertibleTransformException {
		Point2D pointAvant = transformPoint(pointZoom);
		coordTransform.scale(puissanceZoom, puissanceZoom);
		Point2D pointApres = transformPoint(pointZoom);
		coordTransform.translate(pointApres.getX() - pointAvant.getX(), pointApres.getY() - pointAvant.getY());
	}

	private void zoomOut(Point pointZoom) throws NoninvertibleTransformException {
		Point2D pointAvant = transformPoint(pointZoom);
		coordTransform.scale(1 / puissanceZoom, 1 / puissanceZoom);
		Point2D pointApres = transformPoint(pointZoom);
		coordTransform.translate(pointApres.getX() - pointAvant.getX(), pointApres.getY() - pointAvant.getY());
	}

	public void setZoomNoTranslate() throws NoninvertibleTransformException {
		int zoomAncien = sauvegardeNiveauxZoom.pop();
		int zoomDif = niveauZoom - zoomAncien;

		if (zoomDif > 0) {
			for (int i = 0; i < zoomDif; i++) {
				coordTransform.scale(puissanceZoom, puissanceZoom);
			}
			this.notifyObservers();
		} else if (zoomDif < 0) {
			for (int i = 0; i > zoomDif; i--) {
				coordTransform.scale(1 / puissanceZoom, 1 / puissanceZoom);
			}
		}
		sauvegardeNiveauxZoom.add(niveauZoom);
		this.notifyObservers();
	}

	public void translate(MouseEvent e) {
		try {
			this.setEcranFinSouris(e.getPoint());
			Point2D.Float debutSouris = transformPoint(ecranDebutSouris);
			Point2D.Float finSouris = transformPoint(ecranFinSouris);
			coordTransform.translate(finSouris.getX() - debutSouris.getX(), finSouris.getY() - debutSouris.getY());
			ecranDebutSouris = ecranFinSouris;
			this.notifyObservers();
		} catch (NoninvertibleTransformException e1) {
			e1.printStackTrace();
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
