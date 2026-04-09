package ui;

import api.api_commande_accepter;
import api.api_commande_refuser;
import api.api_commande_terminer;

import javax.swing.*;
import java.awt.event.*;

public class Commande_details extends JDialog {
    private JPanel contentPane;
    private JButton accepterButton;
    private JButton refuserButton;
    private JButton prêteButton;
    JTable table2;
    private JButton revenirButton;
    JLabel DATELabel;
    JLabel LOGINLabel;
    JLabel IDcommandLabel;
    private JButton buttonCancel;
    public Commande_details(int idc, String idEtat) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(revenirButton);
        setSize(800, 400);
        setLocationRelativeTo(null);

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
        refuserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                api_commande_refuser apiRefus = new api_commande_refuser();
                apiRefus.commandeRefuser(idc);
            }
        });
        prêteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idEtat.equals("en préparation")) {
                    api_commande_terminer apiTerm = new api_commande_terminer();
                    apiTerm.accepterTerminer(idc);
                }
            }
        });
    }
}
