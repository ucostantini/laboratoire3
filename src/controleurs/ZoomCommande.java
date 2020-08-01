package controleurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.NoninvertibleTransformException;
import modeles.ModelePerspective;

public class ZoomCommande implements Commande {



  @Override
  public void execute(ModelePerspective mp, MouseEvent e) {
    try {
      mp.zoom((MouseWheelEvent) e);
    } catch (Exception noninvertibleTransformException) {
      noninvertibleTransformException.printStackTrace();
    }
  }

  @Override
  public void undo(ModelePerspective mp) {
    try {
      System.out.println(mp);
      mp.setZoom2();
    } catch (NoninvertibleTransformException e) {
      e.printStackTrace();
    }

  }
}
