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


        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
