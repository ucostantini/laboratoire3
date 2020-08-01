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

	private boolean initialisationApplication = true;

	// Zoom
	private int niveauZoom;
	private Point pointZoom;
	private Stack<Integer> sauvegardeNiveauxZoom = new Stack<>();
	private Stack<Point> sauvegardePositionsZoom = new Stack<>();
	private final static double PUISSANCE_ZOOM = 1.2;

	// translate
	private Point ecranDebutSouris;
	private Point ecranFinSouris;
	private AffineTransform transformationCoordonnees = new AffineTransform();


	private Stack<AbstractMap.SimpleEntry<Point, Point>> sauvegardePositions = new Stack<>();

	public ModelePerspective() {
		this.abonnes = new ArrayList<>();
		this.niveauZoom = 0;
		sauvegardeNiveauxZoom.add(0);
	}

	public Stack<AbstractMap.SimpleEntry<Point, Point>> getSauvegardePositions() {
		return sauvegardePositions;
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
		transformationCoordonnees.scale(PUISSANCE_ZOOM, PUISSANCE_ZOOM);
		Point2D pointApres = transformPoint(pointZoom);
		transformationCoordonnees.translate(pointApres.getX() - pointAvant.getX(), pointApres.getY() - pointAvant.getY());
	}

	private void zoomOut(Point pointZoom) throws NoninvertibleTransformException {
		Point2D pointAvant = transformPoint(pointZoom);
		transformationCoordonnees.scale(1 / PUISSANCE_ZOOM, 1 / PUISSANCE_ZOOM);
		Point2D pointApres = transformPoint(pointZoom);
		transformationCoordonnees.translate(pointApres.getX() - pointAvant.getX(), pointApres.getY() - pointAvant.getY());
	}

	public void setZoomNoTranslate() {
		int zoomAncien = sauvegardeNiveauxZoom.pop();
		int zoomDif = niveauZoom - zoomAncien;

		if (zoomDif > 0) {
			for (int i = 0; i < zoomDif; i++) {
				transformationCoordonnees.scale(PUISSANCE_ZOOM, PUISSANCE_ZOOM);
			}
			this.notifyObservers();
		} else if (zoomDif < 0) {
			for (int i = 0; i > zoomDif; i--) {
				transformationCoordonnees.scale(1 / PUISSANCE_ZOOM, 1 / PUISSANCE_ZOOM);
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
			transformationCoordonnees.translate(finSouris.getX() - debutSouris.getX(), finSouris.getY() - debutSouris.getY());
			ecranDebutSouris = ecranFinSouris;
			this.notifyObservers();
		} catch (NoninvertibleTransformException e1) {
			e1.printStackTrace();
		}
	}

	public Point2D.Float transformPoint(Point p1) throws NoninvertibleTransformException {
		AffineTransform inverse = transformationCoordonnees.createInverse();
		Point2D.Float p2 = new Point2D.Float();
		inverse.transform(p1, p2);
		return p2;
	}

	@Override
	public void addObserver(Observateur observer) {
		this.abonnes.add(observer);
	}

	public boolean isInitialisationApplication() {
		return initialisationApplication;
	}

	public void setInitialisationApplication(boolean initialisationApplication) {
		this.initialisationApplication = initialisationApplication;
	}

	public AffineTransform getTransformationCoordonnees() {
		return transformationCoordonnees;
	}

	public void setTransformationCoordonnees(AffineTransform transformationCoordonnees) {
		this.transformationCoordonnees = transformationCoordonnees;
	}

	public int getNiveauZoom() {
		return niveauZoom;
	}

	public void setNiveauZoom(int niveauZoom) {
		this.niveauZoom = niveauZoom;
	}

	public Stack<Integer> getSauvegardeNiveauxZoom() {
		return sauvegardeNiveauxZoom;
	}

	public Stack<Point> getSauvegardePositionsZoom() {
		return sauvegardePositionsZoom;
	}
}
