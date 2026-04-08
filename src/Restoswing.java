import ui.Commande_liste;

import javax.swing.*;

public class Restoswing {
    static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Commande_liste mainui = new Commande_liste();
                mainui.setVisible(true);
            }
        });
    }
}
