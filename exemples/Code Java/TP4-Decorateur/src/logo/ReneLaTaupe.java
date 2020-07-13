package logo;


public class ReneLaTaupe extends Personnage{
    private String nomIm;
    private double prix;

    public ReneLaTaupe() {
        this.nomIm = "img/Taupe.jpg";
        this.prix = 3.65;
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
