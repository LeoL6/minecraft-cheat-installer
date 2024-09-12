package gui;

import model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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

        File[] files = {};

        try {
            files = new File(defaultPath + "\\versions").listFiles();
        } catch (Exception e) {
            e.printStackTrace();
        }

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
            // ADD "name": "com.etomi:loader:1.0" to the json

            // ADD com/etomi/loader/1.0/loader-1.0.jar to libraries folder in .minecraft

            // Then when minecraft runs it will load the loader as a library and if I have a line of code that
            // System.out.printlines then I can show that hte mod is loaded

            // C:\Users\Administrator\AppData\Roaming\.minecraft\libraries

            String version = String.valueOf(versionListComboBox.getSelectedItem());

            // ADD TO JSON
//            JSONParser parser = new JSONParser();
//            File versionJsonFile = new File(defaultPath + "\\versions\\" + version + "\\" + version + ".json");
//            System.out.println(versionJsonFile);
//            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(versionJsonFile));
//
//            JSONObject versionObject = new JSONObject();
//            versionObject.put("name", "cc.squidhack:loader:1.0");
//
//            jsonArray.add(versionObject);
//
//            FileWriter fileWriter = new FileWriter(versionJsonFile);
//            fileWriter.write(jsonArray.toJSONString());
//            fileWriter.flush();

            // ADD LOADER TO LIBRARIES

            String libraryPath = defaultPath + "/libraries/cc/squidhack/loader";

            File libraryDir = new File(libraryPath);
            if (!libraryDir.exists()){
                libraryDir.mkdirs();
            }

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
