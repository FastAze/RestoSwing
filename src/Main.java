import ui.ui_main;

import javax.swing.*;

public class Main {
    static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ui_main mainui = new ui_main();
                mainui.setVisible(true);
            }
        });
    }
}
