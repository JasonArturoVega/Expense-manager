package Ui;

import Model.Transaction;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TransactionInfoPanel extends JPanel {

    public TransactionInfoPanel() {
        setBounds(7, 205, 265, 450);
        setLayout(null);

        //Create fonts
        Font moneyFont = new Font("Arial", Font.BOLD, 30);
        Font statsFont = new Font("Arial", Font.BOLD, 15);

        //Place texts of money
        amountOfMoneyinfo.setBounds(10, 10, 250, 35);
        amountOfMoneyinfo.setEditable(false);
        amountOfMoneyinfo.setFont(moneyFont);
        amountOfMoneyinfo.setText("Current Amount");

        amountOfMoneyLeftText.setBounds(10, 45, 250, 35);
        amountOfMoneyLeftText.setEditable(false);
        amountOfMoneyLeftText.setFont(moneyFont);
        amountOfMoneyLeftText.setText("$0");

        amountReceivedinfo.setBounds(10, 90, 250, 35);
        amountReceivedinfo.setEditable(false);
        amountReceivedinfo.setFont(moneyFont);
        amountReceivedinfo.setText("Total received");

        amountReceivedText.setBounds(10, 125, 250, 35);
        amountReceivedText.setEditable(false);
        amountReceivedText.setFont(moneyFont);
        amountReceivedText.setText("$0");
        amountReceivedText.setForeground(new Color(2, 176, 38));

        amountSpendinfo.setBounds(10, 170, 250, 35);
        amountSpendinfo.setEditable(false);
        amountSpendinfo.setFont(moneyFont);
        amountSpendinfo.setText("Total spent");

        amountSpendText.setBounds(10, 205, 250, 35);
        amountSpendText.setEditable(false);
        amountSpendText.setFont(moneyFont);
        amountSpendText.setText("$0");
        amountSpendText.setForeground(Color.red);

        //Place texts of percentage
        foodText.setBounds(20, 290, 200, 20);
        foodText.setEditable(false);
        foodText.setFont(statsFont);
        foodText.setText("Food: 0.0%");

        transportText.setBounds(20, 330, 200, 20);
        transportText.setEditable(false);
        transportText.setFont(statsFont);
        transportText.setText("Transport: 0.0%");

        householdText.setBounds(20, 370, 200, 20);
        householdText.setEditable(false);
        householdText.setFont(statsFont);
        householdText.setText("Household: 0.0%");

        othersText.setBounds(20, 410, 200, 20);
        othersText.setEditable(false);
        othersText.setFont(statsFont);
        othersText.setText("Others: 0.0%");

        add(amountOfMoneyinfo);
        add(amountOfMoneyLeftText);

        add(amountReceivedinfo);
        add(amountReceivedText);

        add(amountSpendinfo);
        add(amountSpendText);

        add(foodText);
        add(transportText);
        add(householdText);
        add(othersText);

        setBackground(new Color(101, 205, 247));
    }

    private TextField amountOfMoneyinfo = new TextField();
    private TextField amountOfMoneyLeftText = new TextField();

    private TextField amountSpendinfo = new TextField();
    private TextField amountSpendText = new TextField();

    private TextField amountReceivedinfo = new TextField();
    private TextField amountReceivedText = new TextField();

    private TextField foodText = new TextField();
    private TextField transportText = new TextField();
    private TextField householdText = new TextField();
    private TextField othersText = new TextField();

    public void showCurrentMoney(int currentMoney, int receivedMoney, int spentMoney)
    {
        amountOfMoneyLeftText.setText("$" + Integer.toString(currentMoney));
        amountReceivedText.setText("$" + Integer.toString(receivedMoney));
        amountSpendText.setText("$" + Integer.toString(spentMoney));
    }

    public void updateInfo(List<Transaction> listOfTransactions, int totalExpense)
    {
        int spendOnFood = 0;
        int spendOnTransport = 0;
        int spendOnHousehold = 0;
        int spendOnOthers = 0;

        //Check all transactions to see where the money was expended
        for (Transaction transactionChecked : listOfTransactions)
        {
            //If the transaction was an income then is not valid
            if(transactionChecked.getTypeOfTransaction() == Transaction.Type.INCOME)
                continue;

            switch (transactionChecked.getCategoryOfTransaction())
            {
                case FOOD: spendOnFood += transactionChecked.getAmount(); break;
                case TRANSPORT: spendOnTransport += transactionChecked.getAmount(); break;
                case HOUSEHOLD: spendOnHousehold += transactionChecked.getAmount(); break;
                case OTHERS: spendOnOthers += transactionChecked.getAmount(); break;
            }
        }

        //Only calculate if some money was used
        if(totalExpense == 0)
            return;

        //Calculate
        float porcentajeInFood = (float)(spendOnFood * 100) / totalExpense;
        float porcentajeInTransport = (float)(spendOnTransport * 100) / totalExpense;
        float porcentajeInHousehold = (float)(spendOnHousehold * 100) / totalExpense;
        float porcentajeInOthers = (float)(spendOnOthers * 100) / totalExpense;

        //show in text, only one decimal
        foodText.setText("Food: " + String.format("%.1f", porcentajeInFood)  + "%");
        transportText.setText("Transport: " + String.format("%.1f",porcentajeInTransport) + "%");
        householdText.setText("Household: " + String.format("%.1f",porcentajeInHousehold) + "%");
        othersText.setText("Others: " + String.format("%.1f",porcentajeInOthers) + "%");
    }
}
