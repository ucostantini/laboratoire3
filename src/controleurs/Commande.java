package controleurs;

import java.awt.event.MouseEvent;
import modeles.ModelePerspective;

public interface Commande {

  void execute(ModelePerspective mp, MouseEvent e);

}
