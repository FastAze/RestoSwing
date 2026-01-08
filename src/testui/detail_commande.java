package testui;

import javax.swing.*;
import java.awt.event.*;

public class detail_commande extends JDialog {
    private JPanel contentPane;
    private JButton accepterButton;
    private JButton refuserButton;
    private JButton prêteButton;
    private JTable table1;
    private JButton revenirButton;
    private JButton buttonCancel;

    public detail_commande() {
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
    }

}
