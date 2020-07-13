package cafe;

/**
 * Classe permettant l'ajout de chantilly à une boisson 
 */
public class BoissonChantilly extends cafe.DecorateurIngredient {
	
	/**
	 * Constructeur
	 * 
	 *  @param boisson à décorer
	 */
	public BoissonChantilly(cafe.Boisson boisson) {
		super(0.5, " Chantilly", boisson); //prix = 0.5 description="Chantilly"
	}
	
	
}
