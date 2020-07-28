package controleurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import modeles.ModelePerspective;

public class ZoomCommande implements Commande {



  @Override
  public void execute(ModelePerspective mp, MouseEvent e) {
    mp.zoom((MouseWheelEvent) e);
  }

  @Override
  public void undo(ModelePerspective mp) {

  }

}
