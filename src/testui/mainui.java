package testui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainui extends JFrame {
    private JPanel main_menu;
    private JTable table1;
    private JScrollPane scrTbl;
    private JButton detailsButton;
    private JButton quitterButton;

    public mainui() {

        setTitle("RestoSwing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(main_menu);
        setSize(1000, 600);
        table1.getTableHeader().setReorderingAllowed(false);

        detail_commande dc = new detail_commande();

        String[] ColumnNames = {"ID", "Date", "Etat", "Nombre de plat", "total TTC"};

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
            int column = 0;
            int row = table1.getSelectedRow();
            int idc = (int) table1.getModel().getValueAt(row, column);
                api_detail_commande apiteract2 = new api_detail_commande();

                String[] ColumnNames = {"Produit", "Nom Produit", "Quantite"};
                Object[][] tableData = apiteract2.recupererDetailCommande(idc);

                DefaultTableModel datamodel2 = new DefaultTableModel(tableData, ColumnNames);
                dc.table1.setModel(datamodel2);
                dc.setVisible(true);

            }
        });
    }
}
