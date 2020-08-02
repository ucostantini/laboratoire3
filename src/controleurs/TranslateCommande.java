package controleurs;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.AbstractMap;
import modeles.ModelePerspective;

/**
 * Classe responsable de translate une perspective
 */
public class TranslateCommande implements Commande {
	/**
	 * Methode qui execute la translation
	 * @param mp, la perspective
	 * @param e, la commande de la souris
	 */
	@Override
	public void execute(ModelePerspective mp, MouseEvent e) {
		mp.translate(e);
	}

	/**
	 * Methode qui permet d'anuler un translate
	 * @param mp, la perspective
	 */
	@Override
	public void undo(ModelePerspective mp) {
		try {
			AbstractMap.SimpleEntry<Point, Point> entry = mp.getSauvegardePositions().pop();

			Point2D.Float dragStart = mp.transformPoint(entry.getKey());
			Point2D.Float dragEnd = mp.transformPoint(entry.getValue());

			double dx = dragStart.getX() - dragEnd.getX();
			double dy = dragStart.getY() - dragEnd.getY();
			mp.getTransformationCoordonnees().translate(dx, dy);
			mp.notifyObservers();
		} catch (NoninvertibleTransformException ex) {
			ex.printStackTrace();
		}
	}

}
