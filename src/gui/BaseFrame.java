package gui;

import model.User;

import javax.swing.*;

/**
 * @author BrownZombie 9/8/2024
 */
public abstract class BaseFrame extends JFrame {

    protected User user;

    public BaseFrame(String title, int width, int height) {
        initialize(title, width, height);
    }

    public BaseFrame(String title, int width, int height, User user) {
        this.user = user;

        initialize(title, width, height);
    }

    private void initialize(String title, int width, int height) {
        addGUIComponents();

        setTitle(title);

        setSize(width, height);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);

        setLayout(null);

        setLocationRelativeTo(null);
    }

    protected abstract void addGUIComponents();
}
