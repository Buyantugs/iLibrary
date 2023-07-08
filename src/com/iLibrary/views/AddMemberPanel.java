package com.iLibrary.views;

import com.iLibrary.controllers.SystemController;
import com.iLibrary.models.Address;
import com.iLibrary.models.LibraryMember;
import com.iLibrary.utils.Util;

import javax.swing.*;

public class AddMemberPanel extends JPanel {
    private JLabel addMemberLabel;
    private JLabel firstNameLabel;
    private JTextField firstNameTextField;
    private JLabel lastNameLabel;
    private JTextField lastNameTextField;
    private JLabel phNoLabel;
    private JTextField jcomp7;
    private JLabel streetLabel;
    private JTextField streetTextField;
    private JLabel cityLabel;
    private JTextField cityTextField;
    private JLabel stateLabel;
    private JTextField jcomp13;
    private JLabel zipLabel;
    private JTextField zipTextField;
    private JButton saveButton;
    private JLabel idLable;
    private JLabel idValueLable;

    AddMemberPanel(UILauncher launcher) {
        setName("AddMemberPanel");
        //construct components
        addMemberLabel = new JLabel("Add Member");
        firstNameLabel = new JLabel("FirstName :");
        firstNameTextField = new JTextField(5);
        lastNameLabel = new JLabel("LastName :");
        lastNameTextField = new JTextField(5);
        phNoLabel = new JLabel("Phone Number :");
        jcomp7 = new JTextField(5);
        streetLabel = new JLabel("Street :");
        streetTextField = new JTextField(5);
        cityLabel = new JLabel("City :");
        cityTextField = new JTextField(5);
        stateLabel = new JLabel("State :");
        jcomp13 = new JTextField(5);
        zipLabel = new JLabel("Zip :");
        zipTextField = new JTextField(5);
        saveButton = new JButton("Save");
        idLable = new JLabel("ID :");
        idValueLable = new JLabel("-");

        //adjust size and set layout
        setPreferredSize(Util.WINDOW_DIMENSION);
        setLayout(null);

        //add components
        add(addMemberLabel);
        add(firstNameLabel);
        add(firstNameTextField);
        add(lastNameLabel);
        add(lastNameTextField);
        add(phNoLabel);
        add(jcomp7);
        add(streetLabel);
        add(streetTextField);
        add(cityLabel);
        add(cityTextField);
        add(stateLabel);
        add(jcomp13);
        add(zipLabel);
        add(zipTextField);
        add(saveButton);
        add(idLable);
        add(idValueLable);

        //set component bounds (only needed by Absolute Positioning)
        addMemberLabel.setBounds(130, 5, 100, 25);
        firstNameLabel.setBounds(130, 65, 100, 25);
        firstNameTextField.setBounds(265, 65, 215, 30);
        lastNameLabel.setBounds(130, 105, 100, 25);
        lastNameTextField.setBounds(265, 105, 215, 30);
        phNoLabel.setBounds(130, 150, 100, 25);
        jcomp7.setBounds(265, 150, 215, 30);
        streetLabel.setBounds(130, 195, 100, 25);
        streetTextField.setBounds(265, 195, 215, 30);
        cityLabel.setBounds(130, 240, 100, 25);
        cityTextField.setBounds(265, 240, 215, 30);
        stateLabel.setBounds(130, 285, 100, 25);
        jcomp13.setBounds(265, 285, 215, 30);
        zipLabel.setBounds(130, 330, 100, 25);
        zipTextField.setBounds(265, 330, 215, 30);
        saveButton.setBounds(260, 385, 125, 25);
        idLable.setBounds(130, 35, 100, 25);
        idValueLable.setBounds(269, 30, 100, 25);

        saveButton.addActionListener(e -> {
            SystemController sc = new SystemController();

            String memberId = Util.generateRandomString();
            String firstname = firstNameTextField.getText().trim();
            String lastname = lastNameTextField.getText().trim();
            String phno = jcomp7.getText().trim();
            String street = streetTextField.getText().trim();
            String city = cityTextField.getText().trim();
            String state = jcomp13.getText().trim();
            String zip = zipTextField.getText().trim();

            LibraryMember newMember = new LibraryMember(memberId, firstname, lastname, phno, new Address(street, city, state, zip));

            try {
                sc.saveLibraryMember(newMember);
                launcher.navigateTo("ShowAllMembersPanel");
            } catch (Exception err) {
                System.err.println(err.getMessage());
            } finally {
                clearTextFields();
            }

        });
    }

    private void clearTextFields() {
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        jcomp7.setText("");
        streetTextField.setText("");
        cityTextField.setText("");
        jcomp13.setText("");
        zipTextField.setText("");
    }

}
