package com.iLibrary.views;

import com.iLibrary.models.Auth;

import javax.swing.*;

public class CMenuBar extends JMenuBar {
    private Auth role;
    private JMenu menu = new JMenu("Options");

    CMenuBar() {
        setRole(null);
    }

    private void setLibrarianMenuItems() {
        JMenuItem addMemberMenuItem = new JMenuItem("Add Member");
        JMenuItem editMemberMenuItem = new JMenuItem("Edit Member");
        JMenuItem addBookItem = new JMenuItem("Add Book");

        menu.add(addMemberMenuItem);
        menu.add(editMemberMenuItem);
        menu.add(addBookItem);
    }

    private void setAdminMenuItems() {
        JMenuItem checkoutBookMenuItem = new JMenuItem("Checkout Book");
        JMenuItem searchBookByIdMenuItem = new JMenuItem("Search Book By Id");
        JMenuItem showCheckoutRecordsMenuItem = new JMenuItem("Show Checkout Records");

        menu.add(checkoutBookMenuItem);
        menu.add(searchBookByIdMenuItem);
        menu.add(showCheckoutRecordsMenuItem);
    }

    public void setRole(Auth role) {
        if (role == Auth.LIBRARIAN) {
            setLibrarianMenuItems();
            add(menu);
        } else if (role == Auth.ADMIN) {
            setAdminMenuItems();
            add(menu);
        } else if (role == Auth.BOTH) {
            setLibrarianMenuItems();
            setAdminMenuItems();
            add(menu);
        }
        revalidate();
        repaint();
    }
}
