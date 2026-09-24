package Ui;

import Logic.TransactionManager;
import Model.Transaction;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransactionEntryPanel extends JPanel {

    public TransactionEntryPanel(TransactionInfoPanel infoPanel, TransactionHistoryPanel historyPanel, TransactionManager transactionManager)
    {
        //Get other classes
        this.infoPanel = infoPanel;
        this.historyPanel = historyPanel;
        this.transactionManager = transactionManager;

        setBounds(7, 10, 970, 180);
        setLayout(null);

        Font panelFont = new Font("Arial", Font.BOLD, 20);

        //Place texts

        //Amount
        transactionEnterText.setBounds(10,10, 150, 30);
        transactionEnterText.setEditable(false);
        transactionEnterText.setFont(panelFont);
        transactionEnterText.setText("Transaction:");

        transactionEnterField.setBounds(170, 10, 200, 30);
        transactionEnterField.setFont(panelFont);
        transactionEnterField.setText("");

        //Description
        transactionDescripcionText.setBounds(10, 50, 150, 30);
        transactionDescripcionText.setEditable(false);
        transactionDescripcionText.setFont(panelFont);
        transactionDescripcionText.setText("Description:");

        transactionDescripcionField.setBounds(170, 50, 500, 30);
        transactionDescripcionField.setFont(panelFont);
        transactionDescripcionField.setText("");

        //Category
        transactionCategoryText.setBounds(10, 90, 150, 30);
        transactionCategoryText.setEditable(false);
        transactionCategoryText.setFont(panelFont);
        transactionCategoryText.setText("Category:");

        categoryBox = new JComboBox<>(Transaction.Category.values());
        categoryBox.setBounds(170, 90, 200, 30);
        categoryBox.setFont(panelFont);
        categoryBox.setEditable(false);

        //Type
        transactionTypeText.setBounds(10, 130, 210, 30);
        transactionTypeText.setEditable(false);
        transactionTypeText.setFont(panelFont);
        transactionTypeText.setText("Type of Transaction:");

        typeBox = new JComboBox<>(Transaction.Type.values());
        typeBox.setBounds(230, 130, 200, 30);
        typeBox.setFont(panelFont);
        typeBox.setEditable(false);

        //Buttons
        confirmTransactionButton.setBounds(750,50, 200, 30);
        confirmTransactionButton.setText("Confirm");
        confirmTransactionButton.setFont(panelFont);
        confirmTransactionButton.setBackground(new Color(158, 255, 171));
        confirmTransactionButton.addActionListener(e -> createTransaction());

        removeTransactionButton.setBounds(750,90, 200, 30);
        removeTransactionButton.setText("Remove");
        removeTransactionButton.setFont(panelFont);
        removeTransactionButton.setBackground(new Color(250, 166, 145));
        removeTransactionButton.addActionListener(e -> removeTransaction());

        //Add components to the panel
        add(transactionEnterText);
        add(transactionEnterField);

        add(transactionDescripcionText);
        add(transactionDescripcionField);

        add(transactionCategoryText);
        add(categoryBox);

        add(transactionTypeText);
        add(typeBox);

        add(confirmTransactionButton);
        add(removeTransactionButton);

        setBackground(new Color(255, 242, 158));
    }

    private TransactionManager transactionManager;

    private TransactionInfoPanel infoPanel;
    private TransactionHistoryPanel historyPanel;

    private JButton confirmTransactionButton = new JButton();
    private JButton removeTransactionButton = new JButton();

    private TextField transactionEnterField = new TextField();
    private TextField transactionEnterText = new TextField();

    private TextField transactionDescripcionText = new TextField();
    private TextField transactionDescripcionField = new TextField();

    private TextField transactionCategoryText = new TextField();
    private JComboBox categoryBox;

    private TextField transactionTypeText = new TextField();
    private JComboBox typeBox;

    public void createTransaction()
    {
        //You can only type numbers in the amount of money
        String textSaved = transactionEnterField.getText().trim();

        if(textSaved.isEmpty())
            return;

        try {
            int money = Integer.parseInt(textSaved);

            if(money <= 0)
                return;
        }

        catch (NumberFormatException exception)
        {
            return;
        }

        //Get the text and transform it to numbers
        int transactionMoney = Integer.parseInt(transactionEnterField.getText());

        //Get other info
        Transaction.Type currentType = (Transaction.Type)typeBox.getSelectedItem();
        Transaction.Category currentCategory = (Transaction.Category)categoryBox.getSelectedItem();
        String currentDescription = transactionDescripcionField.getText().replace("|", "-");

        //Save date and time
        LocalDate currentDate = LocalDate.now();
        int currentHours = LocalDateTime.now().getHour();
        int currentMinutes = LocalDateTime.now().getMinute();

        String currentMinutesInText;

        if(currentMinutes < 10)
            currentMinutesInText = "0" + Integer.toString(currentMinutes);
        else
            currentMinutesInText = Integer.toString(currentMinutes);

        String currentDateAndTimeInString = currentDate + " " + currentHours + ":" + currentMinutesInText;

        //Create the new transaction
        Transaction currentTransaction = new Transaction(transactionMoney, currentType, currentCategory,
                                                         currentDate, currentHours, currentMinutes,
                                                         currentDescription);

        //Add the transaction to the list and panel
        transactionManager.addTransaction(currentTransaction);
        historyPanel.addTransactionToThePanel(transactionEnterField.getText(), typeBox.getSelectedItem().toString(),
                                              currentDateAndTimeInString, categoryBox.getSelectedItem().toString(),
                                              currentDescription);

        //Show money and data on screen
        infoPanel.showCurrentMoney(transactionManager.getCurrentAmountOfMoney(),
                                   transactionManager.getTotalIncome(), transactionManager.getTotalExpense());
        infoPanel.updateInfo(transactionManager.getTransactions(), transactionManager.getTotalExpense());

        //Reset text
        transactionEnterField.setText("");
        transactionDescripcionField.setText("");
    }

    public void removeTransaction()
    {
        int currentTransactionSelected = historyPanel.getMainTable().getSelectedRow();

        //Only remove if one it's selected
        if(currentTransactionSelected <= -1)
            return;

        //Remove from the list
        transactionManager.removeTransaction(currentTransactionSelected);
        historyPanel.removeTransactionFromThePanel(currentTransactionSelected);

        //Show money and data on screen
        infoPanel.showCurrentMoney(transactionManager.getCurrentAmountOfMoney(),
                                   transactionManager.getTotalIncome(), transactionManager.getTotalExpense());
        infoPanel.updateInfo(transactionManager.getTransactions(), transactionManager.getTotalExpense());
    }
}
