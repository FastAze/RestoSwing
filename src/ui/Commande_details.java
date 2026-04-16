package ui;
import api.api_commande_accepter;
import api.api_commande_refuser;
import api.api_commande_terminer;
import javax.swing.*;
import java.awt.event.*;

public class Commande_details extends JDialog {
    JPanel contentPane;
    JTable table2;
    JLabel DATELabel;
    JLabel LOGINLabel;
    JLabel IDcommandLabel;
    private JButton accepterButton;
    private JButton refuserButton;
    private JButton prêteButton;
    private JButton revenirButton;

    public Commande_details(int idc, String idEtat) {
        // Configuration de base de la fenetre de details.
        setContentPane(contentPane); // Définit le panneau de contenu principal
        setModal(true); // Configure la fenêtre comme modale (bloque les interactions avec d'autres fenêtres)
        getRootPane().setDefaultButton(revenirButton); // Définit le bouton par défaut de la fenêtre
        setSize(800, 400); // Définit la taille de la fenêtre (largeur: 800px, hauteur: 400px)
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran

        revenirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ferme la fenetre de details et revient a la liste.
                dispose();
            }
        });

        accepterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Appelle l'API pour accepter la commande courante.
                api_commande_accepter apiPrete = new api_commande_accepter();
                apiPrete.accepterCommande(idc);
            }
        });

        refuserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Appelle l'API pour refuser la commande courante.
                api_commande_refuser apiRefus = new api_commande_refuser();
                apiRefus.commandeRefuser(idc);
            }
        });

        prêteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Passage en terminee autorise uniquement si l'etat est "en preparation".
                if (idEtat.equals("en préparation")) {
                    api_commande_terminer apiTerm = new api_commande_terminer();
                    apiTerm.accepterTerminer(idc);
                }
            }
        });
    }
}
