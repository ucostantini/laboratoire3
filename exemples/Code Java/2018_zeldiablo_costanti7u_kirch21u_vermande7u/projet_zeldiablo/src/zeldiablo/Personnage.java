package zeldiablo;

public class Personnage {

    /**
     * coordonnee sur axe x
     */
    private int x;

    /**
     * coordonnee sur y
     */
    private int y;

    /**
     * Constructeur zeldiablo.Personnage
     *
     * @param x coordonnee x
     * @param y coordonnee y
     */
    public Personnage(int x, int y){

        this.x = x;
        this.y = y;
    }

    /**
     * Deplace Le personnage
     *
     * @param d zeldiablo.Direction
     */
    public void seDeplacer(Direction d){

        switch (d)
        {
            case NORD:
                this.y--;
                break;

            case SUD:
                this.y++;
                break;

            case EST:
                this.x++;
                break;

            case OUEST:
                this.x--;
                break;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
