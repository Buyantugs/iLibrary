package com.iLibrary.views;

import com.iLibrary.controllers.SystemController;
import com.iLibrary.utils.Util;

import javax.swing.*;

public class AddBookCopyPanel extends JPanel {
    private JLabel addBookCopyLable;
    private JLabel isbnLabel;
    private JTextField isbnTextField;
    private JLabel copyNumberLabel;
    private JTextField copyNumberTextField;
    private JButton saveBtn;
    private SystemController controller;

    AddBookCopyPanel(UILauncher launcher) {
        setName("AddBookCopyPanel");
        //construct components
        addBookCopyLable = new JLabel("Add Book Copy");
        isbnLabel = new JLabel("ISBN:");
        isbnTextField = new JTextField(5);
        copyNumberLabel = new JLabel("Copy Number:");
        copyNumberTextField = new JTextField(5);
        saveBtn = new JButton("Save");

        //adjust size and set layout
        setPreferredSize(Util.WINDOW_DIMENSION);
        setLayout(null);

        //add components
        add(addBookCopyLable);
        add(isbnLabel);
        add(isbnTextField);
        add(copyNumberLabel);
        add(copyNumberTextField);
        add(saveBtn);

        //set component bounds (only needed by Absolute Positioning)
        addBookCopyLable.setBounds(180, 40, 100, 25);
        isbnLabel.setBounds(180, 95, 100, 25);
        isbnTextField.setBounds(265, 95, 190, 20);
        copyNumberLabel.setBounds(180, 135, 100, 25);
        copyNumberTextField.setBounds(265, 135, 190, 20);
        saveBtn.setBounds(265, 185, 100, 25);

        saveBtn.addActionListener(e -> {
            controller = new SystemController();
            String isbn = isbnTextField.getText();
            String copyNumberString = copyNumberTextField.getText();


            int noOfBookCopy = 0;

            try {
                noOfBookCopy = Integer.parseInt(copyNumberString);
            } catch (Exception exception) {
                noOfBookCopy = 0;
            }

            try {
                controller.addBookCopy(isbn, noOfBookCopy);
                launcher.navigateTo("ShowAllBooksPanel");
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
        copyNumberTextField.setText("");
    }
}
