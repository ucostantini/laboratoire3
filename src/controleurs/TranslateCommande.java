package controleurs;

import java.awt.event.MouseEvent;
import modeles.ModelePerspective;

public class TranslateCommande implements Commande {


  @Override
  public void execute(ModelePerspective mp, MouseEvent e) {
    mp.translate(e);
  }
}
