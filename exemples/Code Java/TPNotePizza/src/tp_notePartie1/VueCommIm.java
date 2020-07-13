package tp_notePartie1;

import myImage.MyImage;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class VueCommIm extends JPanel implements Observer {
    private ModeleCommande model;

    public VueCommIm(ModeleCommande model) {
        this.model = model;
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);

        int i=0;
        for(Pizza p : this.model.getListePizza()){
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
