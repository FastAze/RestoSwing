package testui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class detail_commande extends JDialog {
    private JPanel contentPane;
    private JButton accepterButton;
    private JButton refuserButton;
    private JButton prêteButton;
    JTable table1;
    private JButton revenirButton;
    private JButton buttonCancel;
    public detail_commande(int idc) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(revenirButton);
        setSize(800, 400);

        revenirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        accepterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                api_commande_accepter apiPrete = new api_commande_accepter();
                apiPrete.accepterCommande(idc);
            }
        });
    }

}
