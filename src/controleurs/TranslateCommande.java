package controleurs;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

import modeles.ModelePerspective;

public class TranslateCommande implements Commande {


  @Override
  public void execute(ModelePerspective mp, MouseEvent e) {
    mp.translate(e);
  }


  @Override
  public void undo(ModelePerspective mp) {
    try {
      int sizelist = mp.listPosition.size();

      int index = sizelist - 1;
      int index2 = sizelist - 2;

      Point derElmList = mp.listPosition.get(index);
      Point2D.Float dragStart = mp.transformPoint(mp.listPosition.get(index2));
      Point2D.Float dragEnd = mp.transformPoint(derElmList);
      mp.listPosition.remove(derElmList);
      double dx = dragStart.getX() - dragEnd.getX();
      double dy = dragStart.getY() - dragEnd.getY();
      mp.coordTransform.translate(dx, dy);
      mp.notifyObservers();
    }catch (NoninvertibleTransformException ex) {
      ex.printStackTrace();
    }
  }


}
