package controleurs;

import java.awt.event.MouseEvent;
import java.util.LinkedList;
import modeles.ModelePerspective;

public class GestionnaireCommandes{

  public static GestionnaireCommandes getInstance() {
    return new GestionnaireCommandes();
  }

  public LinkedList<Commande> listCommande = new LinkedList<>();


  public void executerCommande(Commande c, ModelePerspective m, MouseEvent e) {
    listCommande.add(c);
    c.execute(m, e);
  }

  public void undoCommande(ModelePerspective m) {
    listCommande.getLast().undo(m);
    listCommande.remove(listCommande.getLast());
  }


}
