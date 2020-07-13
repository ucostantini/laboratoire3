package tp_notePartie2;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class VueCommText extends JLabel implements Observer {

    @Override
    public void update(Observable observable, Object o) {
        ArrayList<Pizza> list = ((ModeleCommande)observable).getListePizza();
        String res="<html><h2>VOTRE COMMANDE :</h2><ul>";
        int i=1;
        for(Pizza p : list){
            res+="<li>Pizza 1 :"+String.format("%.2f",p.cout())+" euros" +
                    " <br>"+p.getDescription()+"</br></li>";
        }
        res+="</ul></html>";

        this.setText(res);
    }
}
