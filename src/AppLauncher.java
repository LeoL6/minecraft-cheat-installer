import com.formdev.flatlaf.FlatDarculaLaf;
import gui.LoginGUI;

import javax.swing.*;

public class AppLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlatDarculaLaf.setup();
            new LoginGUI().setVisible(true);
        });
    }
}
