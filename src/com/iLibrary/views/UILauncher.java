package com.iLibrary.views;

import com.iLibrary.utils.Util;

import javax.swing.*;
import java.awt.*;

public class UILauncher extends JFrame {
    private JPanel mainPanel;
    private LoginPanel loginPanel;
    private ShowAllBooksPanel showAllBooksPanel;
    private AddMemberPanel addMemberPanel;
    private ShowAllMembersPanel showAllMembersPanel;
    private AddBookPanel addBookPanel;
    private CMenuBar menuBar;

    public UILauncher() {
        setTitle("iLibrary");
        mainPanel = new JPanel();
        menuBar = new CMenuBar(this);

        loginPanel = new LoginPanel(this, menuBar);
        showAllBooksPanel = new ShowAllBooksPanel();
        showAllMembersPanel = new ShowAllMembersPanel();
        addMemberPanel = new AddMemberPanel(this);
        addBookPanel = new AddBookPanel(this);

        mainPanel.add(loginPanel);
        mainPanel.add(showAllBooksPanel);
        mainPanel.add(showAllMembersPanel);
        mainPanel.add(addMemberPanel);
        mainPanel.add(addBookPanel);

        setResizable(false);
        add(mainPanel);
        setJMenuBar(menuBar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Util.WINDOW_DIMENSION);
        centerFrameOnDesktop(this);
    }

    private void centerFrameOnDesktop(Component f) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int height = toolkit.getScreenSize().height;
        int width = toolkit.getScreenSize().width;
        int frameHeight = f.getSize().height;
        int frameWidth = f.getSize().width;
        f.setLocation(((width - frameWidth) / 2), (height - frameHeight) / 3);
    }

    public void navigateTo(String from, String to) {
        for (Component component : mainPanel.getComponents()) {
            if (component instanceof JPanel panel) {
                if (panel.getName() != null) {
                    if (panel.getName().equals(from)) {
                        panel.setVisible(false);
                    } else if (panel.getName().equals(to)) {
                        panel.setVisible(true);
                    }
                }
            }
        }
    }

    public void navigateTo(String to) {
        for (Component component : mainPanel.getComponents()) {
            if (component instanceof JPanel panel) {
                if (panel.getName() != null) {
                    panel.setVisible(panel.getName().equals(to));
                }
            }
        }
    }
}
