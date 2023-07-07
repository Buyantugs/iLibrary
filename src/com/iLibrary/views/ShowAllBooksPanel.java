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

    ShowAllBooksPanel(UILauncher launcher) {
        launcher.setTitle("iLibrary: Show All Books");
        setName("ShowAllBooksPanel");
        setLayout(new BorderLayout());
        controller = new SystemController();
        bookCTable = new CTable<>(new String[]{"ISBN", "Title", "Max Checkout"}, controller.allBooks());

        setPreferredSize(new Dimension(Util.WINDOW_DIMENSION.width - 50, Util.WINDOW_DIMENSION.height - 65));
        add(new JScrollPane(bookCTable));

        JPanel searchBarPanel = new JPanel();
        JTextField searchTextField = new JTextField(10);
        JButton searchButton = new JButton("Search");

        searchBarPanel.add(searchTextField);
        searchBarPanel.add(searchButton);
        add(searchBarPanel, BorderLayout.NORTH);

        searchButton.addActionListener(e -> {
            List<Book> searchedResult = controller.searchBooksByISBN(searchTextField.getText().trim());
            bookCTable.setRows(searchedResult);
        });
    }
}
