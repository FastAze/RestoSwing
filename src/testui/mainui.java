package testui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import testui.detail_commande;

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
        // table1.setRowSelectionAllowed();
        detail_commande dc = new detail_commande();


        String[] JsonColumnNames = {"idCommande", "dateHeureCom", "totalTTC", "idUtilisateur", "libEtat", "loginUtil", "emailUtil"};
        String[] ColumnNames = {"ID", "Date", "totalTTC", "idUtilisateur", "libEtat", "loginUtil", "emailUtil"};

        apiteract apiteract = new apiteract();

        Object[][] tableData = apiteract.getCommandesAsTableData(JsonColumnNames);

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
            dc.setVisible(true);
            }
        });
    }
}
