package com.iLibrary.views;

import com.iLibrary.models.Auth;

import javax.swing.*;

public class CMenuBar extends JMenuBar {
    private Auth role;
    private UILauncher launcher;
    private JMenu menu = new JMenu("Options");

    CMenuBar(UILauncher launcher) {
        this.launcher = launcher;
        setRole(null);
    }

    private void setLibrarianMenuItems() {
        JMenuItem showAllBooksMenuItem = new JMenuItem("Show All Books");
        JMenuItem checkoutBooksBooksMenuItem = new JMenuItem("Checkout Books");
        JMenuItem showCheckoutRecordsMenuItem = new JMenuItem("Show Checkout Records");
        JMenuItem addBookItem = new JMenuItem("Add Book Copy");

        showAllBooksMenuItem.addActionListener(e -> {
            launcher.navigateTo("ShowAllBooksPanel");
        });

        checkoutBooksBooksMenuItem.addActionListener(e -> {
        });

        showCheckoutRecordsMenuItem.addActionListener(e -> {
        });

        addBookItem.addActionListener(e -> {
        });

        menu.add(showAllBooksMenuItem);
        menu.add(checkoutBooksBooksMenuItem);
        menu.add(showCheckoutRecordsMenuItem);
        menu.add(addBookItem);
    }

    private void setAdminMenuItems() {
        JMenuItem showAllMembersMenuItem = new JMenuItem("Show All Members");
        JMenuItem addMemberMenuItem = new JMenuItem("Add Member");

        showAllMembersMenuItem.addActionListener(e -> {
            launcher.navigateTo("ShowAllMembersPanel");
        });

        addMemberMenuItem.addActionListener(e -> {
            launcher.navigateTo("AddMemberPanel");
        });

        menu.add(showAllMembersMenuItem);
        menu.add(addMemberMenuItem);
    }

    public void setRole(Auth role) {
        if (role == Auth.LIBRARIAN) {
            setLibrarianMenuItems();
        } else if (role == Auth.ADMIN) {
            setAdminMenuItems();
        } else if (role == Auth.BOTH) {
            setLibrarianMenuItems();
            setAdminMenuItems();
        }

        if (role != null) {
            JMenuItem logoutMenuItem = new JMenuItem("Logout");
            logoutMenuItem.addActionListener(e -> {
                menu.removeAll();
                removeAll();
                launcher.navigateTo("LoginPanel");
            });
            menu.add(logoutMenuItem);
            add(menu);
        }

        revalidate();
        repaint();
    }
}
