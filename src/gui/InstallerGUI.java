package gui;

import model.User;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class InstallerGUI extends BaseFrame{
    public InstallerGUI(User user) {
        super("Installer", 300, 250, user);
    }

    @Override
    protected void addGUIComponents() {
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/assets/icon.png"));
        JLabel iconLabel = new JLabel("", icon, SwingConstants.CENTER);
        iconLabel.setBounds(75, 0, 150, 65);
        add(iconLabel);

        // WELCOME MESSAGE

        String welcomeMessage = "<html>" +
                "<body style='text-align:center'>" +
                "<b>Welcome back <span style='color:rgb(255, 43, 163);'>" + user.getUsername() + "</span></b></body></html>";
        JLabel welcomeMessageLabel = new JLabel(welcomeMessage);
        welcomeMessageLabel.setBounds(75, 70, 150, 60);
        add(welcomeMessageLabel);

        // DIRECTORY CHOOSER BUTTON

        String defaultPath = System.getProperty("user.home") + "\\AppData\\Roaming\\.minecraft";

        final JLabel filePathLabel = new JLabel(defaultPath);
        filePathLabel.setBounds(20, 120, 250, 24);
        Border blackLineBorder = BorderFactory.createLineBorder(new Color(146, 146, 146));
        filePathLabel.setBorder(blackLineBorder);

        JButton selectPathButton = new JButton("Select Folder");
        selectPathButton.setBounds(150, 150, 120, 24);

        // DIRECTORY CHOOSER

        selectPathButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(defaultPath);
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int option = fileChooser.showOpenDialog(this);
            if(option == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                filePathLabel.setText(file.getPath());
            }
        });

        add(selectPathButton);
        add(filePathLabel);

        // VERSION DROPDOWN

        ArrayList<String> versionList = new ArrayList<>();

        File[] files = new File(defaultPath + "\\versions").listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                if (file.getName().contains("1.12.2") || file.getName().contains("1.20.2")) {
                    versionList.add(file.getName());
                }
            }
        }

        JComboBox versionListComboBox = new JComboBox(versionList.toArray());
        versionListComboBox.setBounds(20, 150, 120, 24);
        add(versionListComboBox);

//        String versionPath = defaultPath + "\\versions\\";

        JButton installButton = new JButton("Install");
        installButton.setBounds(20, 180, 120, 24);
        installButton.addActionListener(e -> {
            String version = String.valueOf(versionListComboBox.getSelectedItem());
            System.out.println("Installing: " + version);
        });
        add(installButton);

        JButton uninstallButton = new JButton("Uninstall");
        uninstallButton.setBounds(150, 180, 120, 24);
        uninstallButton.addActionListener(e -> {
            String version = String.valueOf(versionListComboBox.getSelectedItem());
            System.out.println("Uninstalling: " + version);
        });
        add(uninstallButton);
    }
}
