package com.iLibrary.views;

import com.iLibrary.models.Auth;
import com.iLibrary.utils.Util;

import java.awt.*;
import javax.swing.*;

public class LoginPanel extends JPanel {
    private JLabel loginLable;
    private JLabel usernameLabel;
    private JTextField usernameTextField;
    private JLabel passwordLabel;
    private JPasswordField passTextField;
    private JButton loginBtn;

    LoginPanel(UILauncher launcher, CMenuBar menuBar) {
        setName("LoginPanel");
        //construct components
        loginLable = new JLabel("Login");
        usernameLabel = new JLabel("Username:");
        usernameTextField = new JTextField(5);
        passwordLabel = new JLabel("Password:");
        passTextField = new JPasswordField(5);
        loginBtn = new JButton("Login");

        //adjust size and set layout
        setPreferredSize(Util.WINDOW_DIMENSION);
        setLayout(null);

        //add components
        add(loginLable);
        add(usernameLabel);
        add(usernameTextField);
        add(passwordLabel);
        add(passTextField);
        add(loginBtn);

        //set component bounds (only needed by Absolute Positioning)
        loginLable.setBounds(180, 40, 100, 25);
        usernameLabel.setBounds(180, 95, 100, 25);
        usernameTextField.setBounds(265, 95, 190, 20);
        passwordLabel.setBounds(180, 135, 100, 25);
        passTextField.setBounds(265, 135, 190, 20);
        loginBtn.setBounds(265, 185, 100, 25);

        loginBtn.addActionListener(e -> {
            launcher.navigateTo("LoginPanel", "AddMemberPanel");
            menuBar.setRole(Auth.LIBRARIAN);
        });
    }
}
