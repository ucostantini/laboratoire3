package controleurs;

import java.awt.event.MouseEvent;
import modeles.ModelePerspective;

public class GestionnaireCommandes {

  public static GestionnaireCommandes getInstance() {
    return new GestionnaireCommandes();
  }

  public void executerCommande(Commande c, ModelePerspective m, MouseEvent e) {
    c.execute(m, e);
  }

}
