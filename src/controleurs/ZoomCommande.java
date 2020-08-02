package controleurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.NoninvertibleTransformException;
import modeles.ModelePerspective;

/**
 * Classe responsable de zoom sur une perspective
 */
public class ZoomCommande implements Commande {
	/**
	 * Classe responsable d'effectuer le zoom sur la perspective
	 * @param mp, la perspective
	 * @param e, la commande de la souris
	 */
	@Override
	public void execute(ModelePerspective mp, MouseEvent e) {
		try {
			mp.zoom((MouseWheelEvent) e);
		} catch (Exception noninvertibleTransformException) {
			noninvertibleTransformException.printStackTrace();
		}
	}

	/**
	 * Methode responsable d'annuler un zoom sur une perspective
	 * @param mp, la perspective
	 */
	@Override
	public void undo(ModelePerspective mp) {
		try {
			System.out.println(mp);
			mp.setZoom();
		} catch (NoninvertibleTransformException e) {
			e.printStackTrace();
		}

	}
}
