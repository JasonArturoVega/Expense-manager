package Logic;

import Model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionManager {

    public TransactionManager()
    {
        transactions = new ArrayList<>();
    }

    private List<Transaction> transactions;

    private int currentAmountOfMoney;

    private int totalIncome;
    private int totalExpense;

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public int getCurrentAmountOfMoney() {
        return currentAmountOfMoney;
    }

    public int getTotalIncome() {
        return totalIncome;
    }

    public int getTotalExpense() {
        return totalExpense;
    }

    public void addTransaction(Transaction newTransaction)
    {
        //Add to the list
        transactions.add(newTransaction);

        switch(newTransaction.getTypeOfTransaction())
        {
            //Receiving money
            case Transaction.Type.INCOME:

                currentAmountOfMoney += newTransaction.getAmount();
                totalIncome += newTransaction.getAmount();

                break;

            //Expending money
            case Transaction.Type.EXPENSE:

                currentAmountOfMoney -= newTransaction.getAmount();
                totalExpense += newTransaction.getAmount();

                break;
        }
    }

    public void removeTransaction(int index)
    {
        //Get the selected transaction
        Transaction transactionToRemove = transactions.get(index);

        switch(transactionToRemove.getTypeOfTransaction())
        {
            //Revert receiving money
            case Transaction.Type.INCOME:

                currentAmountOfMoney -= transactionToRemove.getAmount();
                totalIncome -= transactionToRemove.getAmount();

                break;

            //Revert expending money
            case Transaction.Type.EXPENSE:

                currentAmountOfMoney += transactionToRemove.getAmount();
                totalExpense -= transactionToRemove.getAmount();

                break;
        }

        //Remove
        transactions.remove(index);
    }
}
