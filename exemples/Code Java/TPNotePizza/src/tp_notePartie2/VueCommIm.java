package tp_notePartie2;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class VueCommIm extends JPanel implements Observer {
    private ModeleCommande model;

    public VueCommIm(ModeleCommande model) {
        this.model = model;
    }

    //TODO gerer affichages ingredients
    public void paintComponent(Graphics g){
        super.paintComponent(g);


        int i=0;
        for(Pizza p : this.model.getListePizza()){
            p.getPizzaIm().superposer("images/ing_fromage.png");
                    p.getPizzaIm().dessinerDansComposant(g, (i+1)*25+ i*200,25);
            i++;
        }
        if(this.model.getNumPizzaCourante()!=-1)
            g.drawRect((this.model.getNumPizzaCourante()+1)*25+ this.model.getNumPizzaCourante()*200,25, 200,200);
    }


    @Override
    public void update(Observable observable, Object o) {
        this.repaint();
    }
}
