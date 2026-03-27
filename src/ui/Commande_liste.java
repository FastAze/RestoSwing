package ui;

import DAO.Commande;
import api.api_liste_commandes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Commande_liste extends JFrame {
    private JPanel main_menu;
    private JTable table1;
    private JScrollPane scrTbl;
    private JButton detailsButton;
    private JButton quitterButton;
    private JButton refreshButton;

    public Commande_liste(int idc) {

        setTitle("RestoSwing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(main_menu);
        setSize(1000, 600);
        table1.getTableHeader().setReorderingAllowed(false);
        setLocationRelativeTo(null); //Mettre le jframe au mileu de l'écran

        String[] ColumnNames = {"ID commande", "Date et heure", "Etat d'avencement", "Nombre de plat", "Total TTC"};

        api_liste_commandes apiteract = new api_liste_commandes();

        Object[][] tableData = apiteract.recupererCommandes();

        DefaultTableModel dataModel = new DefaultTableModel(tableData, ColumnNames){
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

                String[] ColumnNames = {"Produit", "Nom Produit", "Quantite"};


            }
        });

        //Bouton raffraichire
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[][] tableData = apiteract.recupererCommandes();
                DefaultTableModel dataModel = new DefaultTableModel(tableData, ColumnNames){
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
