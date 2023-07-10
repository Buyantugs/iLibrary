package com.iLibrary.views;

import com.iLibrary.controllers.SystemController;
import com.iLibrary.models.Auth;
import com.iLibrary.models.Book;
import com.iLibrary.utils.Util;
import com.iLibrary.views.table.CTable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShowAllBooksPanel extends JPanel {
    private CTable<Book> bookCTable;
    private UILauncher launcher;
    private CheckoutBookPanel checkoutBookPanel;
    private AddBookCopyPanel addBookCopyPanel;

    ShowAllBooksPanel(UILauncher launcher, CheckoutBookPanel checkoutBookPanel, AddBookCopyPanel addBookCopyPanel) {
        this.launcher = launcher;
        this.checkoutBookPanel = checkoutBookPanel;
        this.addBookCopyPanel = addBookCopyPanel;

        setName("ShowAllBooksPanel");
        setLayout(new BorderLayout());

        SystemController controller = new SystemController();
        bookCTable = new CTable<>(new String[]{"ISBN", "Title", "Max Checkout Length"}, controller.allBooks());
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
        checkoutMenuItem.addActionListener(e -> {
            launcher.navigateTo("CheckoutBookPanel");
            checkoutBookPanel.setISBN(bookCTable.getValueAt(bookCTable.getSelectedRow(), 0).toString());
        });
        menu.add(checkoutMenuItem);

        JMenuItem printCheckoutRecordsMenuItem = new JMenuItem("Print Checkout Records");
        printCheckoutRecordsMenuItem.addActionListener(e -> {

        });
        menu.add(printCheckoutRecordsMenuItem);

        if (SystemController.currentAuth == Auth.BOTH) {
            JMenuItem addBookCopyMenuItem = new JMenuItem("Add Book Copy");
            addBookCopyMenuItem.addActionListener(e -> {
                launcher.navigateTo("AddBookCopyPanel");
                addBookCopyPanel.setISBN(bookCTable.getValueAt(bookCTable.getSelectedRow(), 0).toString());
            });
            menu.add(addBookCopyMenuItem);
        }

        return menu;
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        bookCTable.setComponentPopupMenu(getPopupMenu());
    }
}
