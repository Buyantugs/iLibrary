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

    ShowAllMembersPanel() {
        setName("ShowAllMembersPanel");
        setLayout(new BorderLayout());
        controller = new SystemController();
        memberCTable = new CTable<>(new String[]{"Id", "Firstname", "Lastname"}, controller.allLibraryMembers());
        memberCTable.setComponentPopupMenu(getPopupMenu());

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

    private JPopupMenu getPopupMenu() {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem editMemberMenuItem = new JMenuItem("Edit Member");
        menu.add(editMemberMenuItem);

        return menu;
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        memberCTable.setRows(controller.allLibraryMembers());
    }
}
