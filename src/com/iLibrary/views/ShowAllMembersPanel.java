package com.iLibrary.views;

import com.iLibrary.controllers.SystemController;
import com.iLibrary.models.LibraryMember;
import com.iLibrary.utils.Util;
import com.iLibrary.views.table.CTable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShowAllMembersPanel extends JPanel {
    private CTable<LibraryMember> memberCTable;
    private SystemController controller;

    ShowAllMembersPanel(UILauncher launcher, AddOrEditMemberPanel addOrEditMemberPanel) {
        setName("ShowAllMembersPanel");
        setLayout(new BorderLayout());
        controller = new SystemController();
        memberCTable = new CTable<>(new String[]{"Id", "Firstname", "Lastname"}, controller.allLibraryMembers());
        memberCTable.setComponentPopupMenu(getPopupMenu(launcher, addOrEditMemberPanel));

        setPreferredSize(new Dimension(Util.WINDOW_DIMENSION.width - 50, Util.WINDOW_DIMENSION.height - 65));
        add(new JScrollPane(memberCTable));

        JPanel searchBarPanel = new JPanel();

        JTextField searchTextField = new JTextField(10);
        TextPrompt placeholderText = new TextPrompt("Search by id", searchTextField);
        placeholderText.setForeground(Color.GRAY);
        placeholderText.changeAlpha(0.5f);
        placeholderText.changeStyle(Font.ITALIC);

        JButton searchButton = new JButton("Search");

        searchBarPanel.add(searchTextField);
        searchBarPanel.add(searchButton);
        add(searchBarPanel, BorderLayout.NORTH);

        searchButton.addActionListener(e -> {
            List<LibraryMember> searchedResult = controller.searchMembersById(searchTextField.getText().trim());
            memberCTable.setRows(searchedResult);
        });
    }

    private JPopupMenu getPopupMenu(UILauncher launcher, AddOrEditMemberPanel addOrEditMemberPanel) {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem editMemberMenuItem = new JMenuItem("Edit Member");
        editMemberMenuItem.addActionListener(e -> {
            launcher.navigateTo("AddMemberPanel");
            addOrEditMemberPanel.showLibraryMember(controller.allLibraryMembers().get(memberCTable.getSelectedRow()));

        });
        menu.add(editMemberMenuItem);

        JMenuItem deleteMemberMenuItem = new JMenuItem("Delete Member");
        deleteMemberMenuItem.addActionListener(e -> {
            controller.deleteLibraryMember(memberCTable.getValueAt(memberCTable.getSelectedRow(), 0).toString());
            refetchData();
        });
        menu.add(deleteMemberMenuItem);

        return menu;
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        memberCTable.setRows(controller.allLibraryMembers());
        refetchData();
    }

    private void refetchData() {
        memberCTable.setRows(controller.allLibraryMembers());
    }
}
