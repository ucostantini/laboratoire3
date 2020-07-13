package logo;

public class CrazyFrog extends Personnage {
    private String nomIm;
    private double prix;

    public CrazyFrog() {
        this.nomIm = "img/CrazyFrog.jpg";
        this.prix = 3.8;
    }

    /**
     * @return l'objet de MyImage correspondant a nomIm
     */
    public MyImage getLogo(){
        return new MyImage(this.nomIm);
    }

    /**
     * @return le prix du logo
     */
    public double combienCaCoute(){
        return this.prix;
    }
}
