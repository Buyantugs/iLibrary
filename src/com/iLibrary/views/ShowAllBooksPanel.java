package com.iLibrary.views;

import com.iLibrary.controllers.SystemController;
import com.iLibrary.models.Book;
import com.iLibrary.utils.Util;
import com.iLibrary.views.table.CTable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShowAllBooksPanel extends JPanel {
    private CTable<Book> bookCTable;
    private SystemController controller;

    ShowAllBooksPanel() {
        setName("ShowAllBooksPanel");
        setLayout(new BorderLayout());
        controller = new SystemController();
        bookCTable = new CTable<>(new String[]{"ISBN", "Title", "Max Checkout"}, controller.allBooks());
        bookCTable.setComponentPopupMenu(getPopupMenu());

        setPreferredSize(new Dimension(Util.WINDOW_DIMENSION.width - 50, Util.WINDOW_DIMENSION.height - 65));
        add(new JScrollPane(bookCTable));

        JPanel searchBarPanel = new JPanel();

        JTextField searchTextField = new JTextField(10);
        TextPrompt placeholderText = new TextPrompt("Search by isbn", searchTextField);
        placeholderText.setForeground(Color.GRAY);
        placeholderText.changeAlpha(0.5f);
        placeholderText.changeStyle(Font.ITALIC);

        JButton searchButton = new JButton("Search");

        searchBarPanel.add(searchTextField);
        searchBarPanel.add(searchButton);
        add(searchBarPanel, BorderLayout.NORTH);

        searchButton.addActionListener(e -> {
            List<Book> searchedResult = controller.searchBooksByISBN(searchTextField.getText().trim());
            bookCTable.setRows(searchedResult);
        });
    }

    private JPopupMenu getPopupMenu() {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem checkoutMenuItem = new JMenuItem("Checkout Book");
        menu.add(checkoutMenuItem);

        return menu;
    }
}
