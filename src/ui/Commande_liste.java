package ui;
import DAO.Ligne;
import api.api_liste_commandes;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Commande_liste extends JFrame {
    private JPanel main_menu;
    private JTable table1;
    private JButton detailsButton;
    private JButton quitterButton;
    private JButton refreshButton;
    private Object[][] currentTableData;

    public Commande_liste() {
        // Configuration de base de la fenetre principale.
        setTitle("RestoSwing"); // Configure le titre affiche dans la barre de la fenetre.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ferme toute l'application quand la fenetre principale est fermee.
        setContentPane(main_menu); // Definit le panneau principal de la fenetre.
        setSize(1000, 600); // Defini la taille initiale de la fenetre (largeur, hauteur).
        table1.getTableHeader().setReorderingAllowed(false); // Empeche le deplacement manuel des colonnes du tableau.
        setLocationRelativeTo(null); // Centre la fenetre au milieu de l'ecran.

        // Colonnes affichees dans la liste des commandes.
        String[] ColumnNames = {"ID commande", "Date et heure", "Etat d'avencement", "Nombre de plat", "Total TTC"};

        // Service API pour recuperer les commandes depuis le backend.
        api_liste_commandes apiteract = new api_liste_commandes();

        // Chargement initial des donnees.
        currentTableData = apiteract.recupererCommandes();

        // Tableau non editable pour eviter les modifications directes dans l'UI.
        DefaultTableModel dataModel = new DefaultTableModel(currentTableData, ColumnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.setModel(dataModel);


        quitterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ferme completement l'application.
                System.exit(0);
            }
        });

        detailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Recupere la commande selectionnee dans le tableau principal.
                int selectedRow = table1.getSelectedRow();
                int idc = (int) currentTableData[selectedRow][0];
                String idEtat = currentTableData[selectedRow][2].toString();

                // Ouvre la fenetre de details de la commande selectionnee.
                Commande_details CommandeDetails = new Commande_details(idc, idEtat);
                ArrayList<Ligne> lignes = (ArrayList<Ligne>) currentTableData[selectedRow][5];

                // Colonnes et donnees de la table des lignes de commande.
                String[] ColumnNames = {"ID Produit", "Nom Produit", "Quantité"};
                Object[][] lignesData = new Object[lignes.size()][3];

                // Affichage des informations generales de la commande.
                CommandeDetails.IDcommandLabel.setText(currentTableData[selectedRow][0].toString());
                CommandeDetails.DATELabel.setText(currentTableData[selectedRow][1].toString());
                CommandeDetails.LOGINLabel.setText(currentTableData[selectedRow][6].toString());

                // Remplit la table de details avec les produits de la commande.
                for (int i = 0; i < lignes.size(); i++) {
                    lignesData[i][0] = lignes.get(i).idProduit;
                    lignesData[i][1] = lignes.get(i).libProduit;
                    lignesData[i][2] = lignes.get(i).quantite;
                }

                DefaultTableModel detailModel = new DefaultTableModel(lignesData, ColumnNames);
                CommandeDetails.table2.setModel(detailModel);
                CommandeDetails.setVisible(true);
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Recharge les commandes depuis l'API puis met a jour la table.
                currentTableData = apiteract.recupererCommandes();
                DefaultTableModel dataModel = new DefaultTableModel(currentTableData, ColumnNames){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                table1.setModel(dataModel);
            }
        });
    }
}
