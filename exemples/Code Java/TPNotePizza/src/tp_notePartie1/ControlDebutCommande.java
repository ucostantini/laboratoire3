package tp_notePartie1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlDebutCommande implements ActionListener {
    private ModeleCommande model;

    public ControlDebutCommande(ModeleCommande model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.model.ajouterPizza(actionEvent.getActionCommand());
    }
}
