import ui.Commande_liste;

import javax.swing.*;

public class Restoswing {
    // Point d'entree de l'application.
    static void main(String[] args) {
        // Lance l'interface sur le thread graphique Swing (EDT).
        SwingUtilities.invokeLater(new Runnable() {
            // Cree puis affiche la fenetre principale.
            public void run() {
                Commande_liste mainui = new Commande_liste();
                mainui.setVisible(true);
            }
        });
    }
}
