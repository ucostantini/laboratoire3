package exercice_mvc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controler  extends JTextField implements ActionListener {
    private Model m;

    public Controler(int i, Model m) {
        super(i);
        this.m = m;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.m.ajouterMot(this.getText());
        this.setText("");
    }
}
