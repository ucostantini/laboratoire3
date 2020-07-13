package tp_notePartie1;

import com.sun.media.sound.ModelChannelMixer;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class VueCommPrix extends JLabel implements Observer {

    @Override
    public void update(Observable observable, Object o) {
        ModeleCommande m = ((ModeleCommande)observable);
        m.calculPrixCommande();
        this.setText("<html><h3>Prix total de votre commande : "+
                String.format("%.2f",m.getPrixCommande())+" euros</h3></html>");
    }
}
