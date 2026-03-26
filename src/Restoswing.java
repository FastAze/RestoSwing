import ui.Commande_details;

import javax.swing.*;

public class Restoswing {
    static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Commande_details mainui = new Commande_details();
                mainui.setVisible(true);
            }
        });
    }
}
