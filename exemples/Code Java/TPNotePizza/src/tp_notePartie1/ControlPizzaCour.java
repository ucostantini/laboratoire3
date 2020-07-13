package tp_notePartie1;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControlPizzaCour implements MouseListener {
    private ModeleCommande model;

    public ControlPizzaCour(ModeleCommande model) {
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        int x = mouseEvent.getX();
        int numpizzaSelec = x/225;
        if(numpizzaSelec<this.model.getNbPizza())
            this.model.setNumPizzaCourante(numpizzaSelec);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
