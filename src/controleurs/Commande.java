package controleurs;

import java.awt.event.MouseEvent;
import modeles.ModelePerspective;

/**
 * Interface du patron commande
 */
public interface Commande {
  /**
   * Signature de la methode qui va executer une commande, sur une perpective
   * @param mp, la perspective
   * @param e, la commande de la souris
   */
  void execute(ModelePerspective mp, MouseEvent e);

  /**
   * Signature de la methode qui undo une commande
   * @param mp, la perspective
   */
  void undo(ModelePerspective mp);
}

