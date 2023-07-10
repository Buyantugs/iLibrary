package com.iLibrary.views;

import com.iLibrary.controllers.SystemController;
import com.iLibrary.exceptions.LoginException;
import com.iLibrary.utils.Util;

import javax.swing.*;

public class CheckoutBookPanel extends JPanel {
    private JLabel checkoutBookLable;
    private JLabel isbnLabel;
    private JTextField isbnTextField;
    private JLabel passwordLabel;
    private JPasswordField passTextField;
    private JButton loginBtn;
    private SystemController controller;

    CheckoutBookPanel(UILauncher launcher, CMenuBar menuBar) {
        setName("CheckoutBookPanel");
        //construct components
        checkoutBookLable = new JLabel("Checkout Book");
        isbnLabel = new JLabel("ISBN:");
        isbnTextField = new JTextField(5);
        passwordLabel = new JLabel("Password:");
        passTextField = new JPasswordField(5);
        loginBtn = new JButton("Login");

        //adjust size and set layout
        setPreferredSize(Util.WINDOW_DIMENSION);
        setLayout(null);

        //add components
        add(checkoutBookLable);
        add(isbnLabel);
        add(isbnTextField);
        add(passwordLabel);
        add(passTextField);
        add(loginBtn);

        //set component bounds (only needed by Absolute Positioning)
        checkoutBookLable.setBounds(180, 40, 100, 25);
        isbnLabel.setBounds(180, 95, 100, 25);
        isbnTextField.setBounds(265, 95, 190, 20);
        passwordLabel.setBounds(180, 135, 100, 25);
        passTextField.setBounds(265, 135, 190, 20);
        loginBtn.setBounds(265, 185, 100, 25);

        loginBtn.addActionListener(e -> {
            controller = new SystemController();
            try {
                controller.login(isbnTextField.getText().trim(), String.valueOf(passTextField.getPassword()));
                System.out.println(SystemController.currentAuth);
                switch (SystemController.currentAuth) {
                    case LIBRARIAN:
                        launcher.navigateTo("ShowAllBooksPanel");
                        break;
                    case ADMIN, BOTH:
                        launcher.navigateTo("ShowAllMembersPanel");
                        break;
                }
                menuBar.setRole(SystemController.currentAuth);
            } catch (LoginException ex) {
                System.err.println(ex.getMessage());
            } finally {
                clearTextFields();
            }
        });
    }

    private void clearTextFields() {
        isbnTextField.setText("");
        passTextField.setText("");
    }
}
