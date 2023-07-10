package com.iLibrary.views;

import com.iLibrary.utils.Util;

import javax.swing.*;
import java.awt.*;

public class UILauncher extends JFrame {
    private JPanel mainPanel;
    private LoginPanel loginPanel;
    private ShowAllBooksPanel showAllBooksPanel;
    private AddOrEditMemberPanel addOrEditMemberPanel;
    private ShowAllMembersPanel showAllMembersPanel;
    private AddBookPanel addBookPanel;
    private CheckoutBookPanel checkoutBookPanel;
    private CMenuBar menuBar;

    public UILauncher() {
        setTitle("iLibrary");
        mainPanel = new JPanel();
        menuBar = new CMenuBar(this);

        loginPanel = new LoginPanel(this, menuBar);
        addOrEditMemberPanel = new AddOrEditMemberPanel(this);
        showAllMembersPanel = new ShowAllMembersPanel(this, addOrEditMemberPanel);
        checkoutBookPanel = new CheckoutBookPanel(this);
        showAllBooksPanel = new ShowAllBooksPanel(this, checkoutBookPanel);
        addBookPanel = new AddBookPanel(this);

        mainPanel.add(loginPanel);
        mainPanel.add(showAllBooksPanel);
        mainPanel.add(showAllMembersPanel);
        mainPanel.add(checkoutBookPanel);
        mainPanel.add(addOrEditMemberPanel);
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
