package tp_notePartie2;

import myImage.MyImage;

public class PizzaTomate implements Pizza {
    private double prix;
    private String nomIm;
    private String description;

    public PizzaTomate() {
        this.prix=6;
        this.nomIm="fond_tomate.png";
        this.description="base tomate";
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
