package tp_notePartie2;

import myImage.MyImage;

public abstract class IngredientPizza implements Pizza {
    protected Pizza pizza;

    private double prix;
    private String description;
    private String nomImage;

    public IngredientPizza(Pizza pizza, double prix, String description, String nomImage) {
        this.pizza = pizza;
        this.prix = prix;
        this.description = description;
        this.nomImage = nomImage;
    }

    @Override
    public MyImage getPizzaIm() {
        MyImage m = this.pizza.getPizzaIm();
        m.superposer("images/"+this.nomImage);
        return m;
    }

    @Override
    public double cout() {
        return this.pizza.cout()+this.prix;
    }

    @Override
    public String getDescription() {
        return this.pizza.getDescription()+" - "+this.description;
    }
}
