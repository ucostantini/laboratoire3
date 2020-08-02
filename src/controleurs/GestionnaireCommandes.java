package controleurs;

import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import modeles.ModelePerspective;

/**
 * Classe responsable des commandes
 */
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

	/**
	 * Classe responsable d'effectuer une commande sur un perspective
	 * @param c, la commande
	 * @param m, la perspective
	 * @param e, l'action de la souris
	 */
	public void executerCommande(Commande c, ModelePerspective m, MouseEvent e) {
		c.execute(m, e);
	}

	/**
	 * Commande responsable d'annuler une commande
	 * @param m, la perspective
	 */
	public void undoCommande(ModelePerspective m) {
		if (!commandes.isEmpty()) {
			Stack<Commande> commande = commandes.get(m);
			if (commande != null && !commande.empty()) {
				commande.pop().undo(m);
			}
		}
	}

	/**
	 * Methode responsabke d'ajouter une commande
	 * @param c, la commande
	 * @param m, la perspective
	 */
	public void ajouterCommande(Commande c, ModelePerspective m) {
		if (!commandes.containsKey(m)) {
			commandes.put(m, new Stack<>());
		}
		commandes.get(m).push(c);
	}

}
