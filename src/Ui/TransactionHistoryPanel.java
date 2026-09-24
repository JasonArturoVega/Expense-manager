package Ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TransactionHistoryPanel extends JScrollPane {

    public TransactionHistoryPanel()
    {
        //Texts in the columns
        String[] columns = new String[]{"Amount", "Type", "Date", "Category", "Description"};

        mainModel = new DefaultTableModel(columns, 0)
        {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        mainTable = new JTable(mainModel);

        //Table inside the Scroll
        setViewportView(mainTable);

        setBounds(280, 205, 700, 450);

        //Set size of each column
        mainTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        mainTable.getColumnModel().getColumn(1).setPreferredWidth(80);
        mainTable.getColumnModel().getColumn(2).setPreferredWidth(140);
        mainTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        mainTable.getColumnModel().getColumn(4).setPreferredWidth(250);
    }

    private final JTable mainTable;
    private final DefaultTableModel mainModel;

    public void addTransactionToThePanel(String amount, String type, String date, String category, String description)
    {
        mainModel.addRow(new Object[]{amount, type, date, category, description});
    }

    public void removeTransactionFromThePanel(int index)
    {
        mainModel.removeRow(index);
    }

    public JTable getMainTable()
    {
        return  mainTable;
    }
}
