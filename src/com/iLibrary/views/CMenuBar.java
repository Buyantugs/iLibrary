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
        JMenuItem addMemberMenuItem = new JMenuItem("Add Member");
        JMenuItem addBookItem = new JMenuItem("Add Book Copy");

        showAllBooksMenuItem.addActionListener(e -> {
            launcher.navigateTo("ShowAllBooksPanel");
        });

        addMemberMenuItem.addActionListener(e -> {
            launcher.navigateTo("AddMemberPanel");
        });

        addBookItem.addActionListener(e -> {
        });

        menu.add(showAllBooksMenuItem);
        menu.add(addMemberMenuItem);
        menu.add(addBookItem);
    }

    private void setAdminMenuItems() {
        JMenuItem showAllMembersMenuItem = new JMenuItem("Show All Members");
        JMenuItem showCheckoutRecordsMenuItem = new JMenuItem("Show Checkout Records");

        showAllMembersMenuItem.addActionListener(e -> {
            launcher.navigateTo("ShowAllMembersPanel");
        });

        showCheckoutRecordsMenuItem.addActionListener(e -> {
        });

        menu.add(showAllMembersMenuItem);
        menu.add(showCheckoutRecordsMenuItem);
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
