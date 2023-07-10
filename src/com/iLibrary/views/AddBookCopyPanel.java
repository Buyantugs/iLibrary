package com.iLibrary.views;

import com.iLibrary.controllers.SystemController;
import com.iLibrary.utils.Util;

import javax.swing.*;

public class CheckoutBookPanel extends JPanel {
    private JLabel checkoutBookLable;
    private JLabel isbnLabel;
    private JTextField isbnTextField;
    private JLabel memberIdLabel;
    private JTextField memberIdTextField;
    private JButton saveBtn;
    private SystemController controller;

    CheckoutBookPanel(UILauncher launcher) {
        setName("CheckoutBookPanel");
        //construct components
        checkoutBookLable = new JLabel("Checkout Book");
        isbnLabel = new JLabel("ISBN:");
        isbnTextField = new JTextField(5);
        memberIdLabel = new JLabel("Member Id:");
        memberIdTextField = new JTextField(5);
        saveBtn = new JButton("Save");

        //adjust size and set layout
        setPreferredSize(Util.WINDOW_DIMENSION);
        setLayout(null);

        //add components
        add(checkoutBookLable);
        add(isbnLabel);
        add(isbnTextField);
        add(memberIdLabel);
        add(memberIdTextField);
        add(saveBtn);

        //set component bounds (only needed by Absolute Positioning)
        checkoutBookLable.setBounds(180, 40, 100, 25);
        isbnLabel.setBounds(180, 95, 100, 25);
        isbnTextField.setBounds(265, 95, 190, 20);
        memberIdLabel.setBounds(180, 135, 100, 25);
        memberIdTextField.setBounds(265, 135, 190, 20);
        saveBtn.setBounds(265, 185, 100, 25);

        saveBtn.addActionListener(e -> {
            controller = new SystemController();
            String isbn = isbnTextField.getText();
            String memberId = memberIdTextField.getText();

            try {
                controller.checkoutBook(memberId, isbn);
                launcher.navigateTo("ShowAllMembersPanel");
            } catch (Exception exc) {
                exc.printStackTrace();
            } finally {
                clearTextFields();
            }
        });
    }

    public void setISBN(String isbn) {
        isbnTextField.setText(isbn);
        isbnTextField.setEditable(false);
    }

    private void clearTextFields() {
        isbnTextField.setText("");
        memberIdTextField.setText("");
    }
}
