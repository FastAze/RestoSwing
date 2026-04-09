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
    private JScrollPane scrTbl;
    private JButton detailsButton;
    private JButton quitterButton;
    private JButton refreshButton;
    private Object[][] currentTableData;

    public Commande_liste() {

        setTitle("RestoSwing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(main_menu);
        setSize(1000, 600);
        table1.getTableHeader().setReorderingAllowed(false);
        setLocationRelativeTo(null); //Mettre le jframe au mileu de l'écran

        String[] ColumnNames = {"ID commande", "Date et heure", "Etat d'avencement", "Nombre de plat", "Total TTC"};

        api_liste_commandes apiteract = new api_liste_commandes();

        currentTableData = apiteract.recupererCommandes();

        DefaultTableModel dataModel = new DefaultTableModel(currentTableData, ColumnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.setModel(dataModel);


        // Bouton quitter
        quitterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //Bouton détail
        detailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow();
                int idc = (int) currentTableData[selectedRow][0];
                String idEtat = currentTableData[selectedRow][2].toString();

                Commande_details CommandeDetails = new Commande_details(idc, idEtat);
                ArrayList<Ligne> lignes = (ArrayList<Ligne>) currentTableData[selectedRow][5];

                String[] ColumnNames = {"ID Produit", "Nom Produit", "Quantité"};
                Object[][] lignesData = new Object[lignes.size()][3];

                CommandeDetails.IDcommandLabel.setText(currentTableData[selectedRow][0].toString());
                CommandeDetails.DATELabel.setText(currentTableData[selectedRow][1].toString());
                CommandeDetails.LOGINLabel.setText(currentTableData[selectedRow][6].toString());

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

        //Bouton raffraichire
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
