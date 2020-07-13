package zeldiablo;

public class Case {
    /**
     Attribut boolean mur
     Si mur est a true c'est un mur sinon une case vide
     */
    private boolean mur;

    /**
     * Constructeur case
     * @param m permet de savoir si c'est un mur ou une case
     */
    public Case(boolean m){
        this.mur=m;
    }

    /**
     * Methode qui retourne la case est un mur ou non
     * @return
     */
    public boolean estMur(){
        return(mur);
    }
}
