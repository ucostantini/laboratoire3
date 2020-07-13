import javax.swing.*;
import java.awt.*;

public class Principale {

    public static void main(String[] args) {

        JFrame j = new JFrame();
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ScribbleGUI jp = new ScribbleGUI();
        jp.setPreferredSize(new Dimension(700, 800));
        j.setContentPane(jp);
        j.pack();
        j.setVisible(true);
        jp.getSb().requestFocusInWindow();
        jp.getSb().requestFocus();

    }

}
