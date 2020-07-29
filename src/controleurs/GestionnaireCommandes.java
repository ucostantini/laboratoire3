package controleurs;

import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import modeles.ModelePerspective;

public class GestionnaireCommandes {

  private static GestionnaireCommandes gc;
  public Map<ModelePerspective, Stack<Commande>> commandes = new HashMap<>();


  private GestionnaireCommandes() {

  }

  public static GestionnaireCommandes getInstance() {
    if (GestionnaireCommandes.gc == null) {
      GestionnaireCommandes.gc = new GestionnaireCommandes();
    }
    return GestionnaireCommandes.gc;
  }


  public void executerCommande(Commande c, ModelePerspective m, MouseEvent e) {
    c.execute(m, e);
  }

  public void undoCommande(ModelePerspective m) {
    if (!commandes.isEmpty()) {
      Stack<Commande> commande = commandes.get(m);
      if (commande != null && !commande.empty()) {
        commande.pop().undo(m);
      }
    }
  }

  public void ajouterCommande(Commande c, ModelePerspective m) {
    if (!commandes.containsKey(m)) {
      commandes.put(m, new Stack<>());
    }
    commandes.get(m).push(c);
  }


}
