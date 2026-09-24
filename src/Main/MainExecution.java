package Main;

import FileManagement.FileManager;
import Logic.TransactionManager;
import Model.Transaction;
import Ui.MainWindow;
import Ui.TransactionEntryPanel;
import Ui.TransactionHistoryPanel;
import Ui.TransactionInfoPanel;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.List;

public class MainExecution {

    static void main(String[] args) {

        //Manager
        TransactionManager transactionManager = new TransactionManager();

        MainWindow window = new MainWindow(1000, 700);

        //Add the event of saving the list of transactions when the window close
        window.addWindowListener(new java.awt.event.WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e) {
                FileManager.SaveTransactions(transactionManager.getTransactions());
            }
        });

        //Close program when closing the window
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Ui
        TransactionHistoryPanel historyPanel = new TransactionHistoryPanel();
        TransactionInfoPanel infoPanel = new TransactionInfoPanel();
        TransactionEntryPanel entryPanel = new TransactionEntryPanel(infoPanel, historyPanel, transactionManager);

        window.add(infoPanel);
        window.add(historyPanel);
        window.add(entryPanel);

        //Load files if posible
        List<Transaction> loadedTransactionsFromFile = FileManager.LoadTransactions();

        for(Transaction loadedTransaction : loadedTransactionsFromFile)
        {
            transactionManager.addTransaction(loadedTransaction);

            //Read date and time
            LocalDate currentDate = loadedTransaction.getTransactionDate();
            int currentHours = loadedTransaction.getTransactionHour();
            int currentMinutes = loadedTransaction.getTransactionMinute();

            String currentMinutesInText;

            if(currentMinutes < 10)
                currentMinutesInText = "0" + Integer.toString(currentMinutes);
            else
                currentMinutesInText = Integer.toString(currentMinutes);

            String currentDateAndTimeInString = currentDate + " " + currentHours + ":" + currentMinutesInText;


            historyPanel.addTransactionToThePanel(Integer.toString(loadedTransaction.getAmount()), loadedTransaction.getTypeOfTransaction().toString(),
                                                  currentDateAndTimeInString, loadedTransaction.getCategoryOfTransaction().toString(),
                                                  loadedTransaction.getTransactionDescription());
        }

        //Update ui
        infoPanel.updateInfo(transactionManager.getTransactions(), transactionManager.getTotalExpense());
        infoPanel.showCurrentMoney(transactionManager.getCurrentAmountOfMoney(),
                                   transactionManager.getTotalIncome(), transactionManager.getTotalExpense());

        window.setVisible(true);
    }
}
