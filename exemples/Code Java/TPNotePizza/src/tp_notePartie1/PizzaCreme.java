package tp_notePartie1;

import myImage.MyImage;

public class PizzaCreme implements Pizza {
    private double prix;
    private String nomIm;
    private String description;

    public PizzaCreme() {
        this.prix=6;
        this.nomIm="fond_creme.png";
        this.description="base creme";
    }
    @Override
    public MyImage getPizzaIm() {
        return new MyImage("images/"+this.nomIm);
    }

    @Override
    public double cout() {
        return this.prix;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
