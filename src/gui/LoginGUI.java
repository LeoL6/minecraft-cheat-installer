package gui;

import connection.DatabaseConnection;
import model.User;

import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginGUI extends BaseFrame {
    public LoginGUI() {
        super("Login", 300, 220);
    }

    private static JTextField userText;
    private static JPasswordField passText;
    private static JButton loginButton;

    @Override
    protected void addGUIComponents() {

        // ICON AND TITLE

        ImageIcon icon = new ImageIcon(this.getClass().getResource("/assets/icon.png"));
        JLabel iconLabel = new JLabel("", icon, SwingConstants.CENTER);
        iconLabel.setBounds(75, 0, 150, 65);
        add(iconLabel);

        // USERNAME AND PASSWORD

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(10, 80, 80, 25);
        add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 80, 120, 25);
        add(userText);

        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(10, 110, 80, 25);
        add(passLabel);

        passText = new JPasswordField(20);
        passText.setBounds(100, 110, 120, 25);
        add(passText);

        // LOGIN BUTTON

        ActionListener loginEvent = e -> login();

        loginButton = new JButton("Login");
        loginButton.setBounds(120, 140, 80, 25);
        loginButton.addActionListener(loginEvent);
        add(loginButton);
    }

    private void login() {
        String username = userText.getText();
        String password = String.valueOf(passText.getPassword());

        User user = DatabaseConnection.validateLogin(username, password);

        // if user is null that means could not log in, else log into installer panel
        if (user != null) {
            LoginGUI.this.dispose();

            InstallerGUI installerGUI = new InstallerGUI(user);
            installerGUI.setVisible(true);

            JOptionPane.showMessageDialog(installerGUI, "Login Successful");
        } else {
            JOptionPane.showMessageDialog(LoginGUI.this, "Login Unsuccessful");
        }
    }
}
