import com.formdev.flatlaf.FlatDarculaLaf;
import gui.LoginGUI;

import javax.swing.*;

/**
 * @author BrownZombie 9/4/2024
 */
public class AppLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlatDarculaLaf.setup();
            new LoginGUI().setVisible(true);
        });
    }
}