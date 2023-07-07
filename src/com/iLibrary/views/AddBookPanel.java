package com.iLibrary.views;

import com.iLibrary.utils.Util;

import javax.swing.*;

public class AddBookPanel extends JPanel {
    private JLabel addBookLabel;
    private JLabel isbnLabel;
    private JTextField firstNameTextField;
    private JLabel titleLabel;
    private JTextField lastNameTextField;
    private JLabel maxCheckoutLabel;
    private JTextField jcomp7;
    private JButton saveButton;
    private JLabel idLable;
    private JLabel idValueLable;

    AddBookPanel(UILauncher launcher) {
        setName("AddBookPanel");
        //construct components
        addBookLabel = new JLabel("Add Book");
        isbnLabel = new JLabel("ISBN :");
        firstNameTextField = new JTextField(5);
        titleLabel = new JLabel("Title :");
        lastNameTextField = new JTextField(5);
        maxCheckoutLabel = new JLabel("Max Checkout :");
        jcomp7 = new JTextField(5);
        saveButton = new JButton("Save");
        idLable = new JLabel("ID :");
        idValueLable = new JLabel("-");

        //adjust size and set layout
        setPreferredSize(Util.WINDOW_DIMENSION);
        setLayout(null);

        //add components
        add(addBookLabel);
        add(isbnLabel);
        add(firstNameTextField);
        add(titleLabel);
        add(lastNameTextField);
        add(maxCheckoutLabel);
        add(jcomp7);
        add(saveButton);
        add(idLable);
        add(idValueLable);

        //set component bounds (only needed by Absolute Positioning)
        addBookLabel.setBounds(130, 50, 100, 25);
        isbnLabel.setBounds(130, 140, 100, 25);
        firstNameTextField.setBounds(265, 140, 215, 30);
        titleLabel.setBounds(130, 195, 100, 25);
        lastNameTextField.setBounds(265, 195, 215, 30);
        maxCheckoutLabel.setBounds(130, 245, 100, 25);
        jcomp7.setBounds(265, 245, 215, 30);
        saveButton.setBounds(265, 320, 125, 25);
        idLable.setBounds(130, 95, 100, 25);
        idValueLable.setBounds(270, 90, 100, 25);

        saveButton.addActionListener(e -> {

        });
    }
}
