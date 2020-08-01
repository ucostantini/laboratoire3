package controleurs;

import java.awt.event.MouseEvent;
import java.util.Stack;
import modeles.ModelePerspective;

public class GestionnaireCommandes {


  public Stack<Commande> commandes = new Stack<>();

  private GestionnaireCommandes() {

  }

  public static GestionnaireCommandes getInstance() {
    return new GestionnaireCommandes();
  }


  public void executerCommande(Commande c, ModelePerspective m, MouseEvent e) {
    c.execute(m, e);
  }

  public void undoCommande(ModelePerspective m) {
    if (!commandes.isEmpty()) {
      if (commandes != null && !commandes.empty()) {
        commandes.pop().undo(m);
      }
    }
  }

  public void ajouterCommande(Commande c, ModelePerspective m) {
    commandes.push(c);
  }


}
