package com.iLibrary.views.table;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CTable<T extends ShowableOnCTable> extends JTable {
    private String[] columnNames;

    private List<T> rows;

    private class CModel extends AbstractTableModel {
        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        @Override
        public int getRowCount() {
            return rows.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            T eachRow = rows.get(rowIndex);
            return eachRow.showThisDataOnTable(rowIndex, columnIndex);
        }
    }

    public CTable(String[] columnNames, List<T> rows) {
        this.columnNames = columnNames;
        this.rows = rows;

        setModel(new CModel());
    }

    public void setRows(List<T> newRows) {
        this.rows = newRows;
        revalidate();
        repaint();
    }
}
