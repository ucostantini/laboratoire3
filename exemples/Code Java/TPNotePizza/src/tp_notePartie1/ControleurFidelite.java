package tp_notePartie1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurFidelite implements ActionListener {
    private ModeleCommande model;

    public ControleurFidelite(ModeleCommande model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        String s = (String)((JComboBox)actionEvent.getSource()).getSelectedItem();
        if(s.contains("Nouveau"))
            this.model.setTaux(new ClientNouveau());
        else if(s.contains("carte"))
            this.model.setTaux(new ClientCarte());
        else
            this.model.setTaux(new ClientAbonnement());
    }
}
