package FileManagement;

import Model.Transaction;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private final static String FILE_NAME = "Transactions.txt";

    public static void SaveTransactions(List<Transaction> listOfTransactions)
    {
        //Tries to write the file
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME)))
        {
            //Go for each transaction in the list
            for (Transaction transaction: listOfTransactions)
            {
                //Save transactions in lines
                String line = transaction.getAmount() + "|" + transaction.getTypeOfTransaction().name() + "|" +
                              transaction.getCategoryOfTransaction().name() + "|" + transaction.getTransactionDate() + "|" +
                              transaction.getTransactionHour() + "|" + transaction.getTransactionMinute() + "|" +
                              transaction.getTransactionDescription();

                writer.write(line);
                writer.newLine();
            }
        }
        catch (IOException exception)
        {
            System.out.println("Error while saving contacts: " + exception.getMessage());
        }
    }

    public static List<Transaction> LoadTransactions()
    {
        //Load file
        List<Transaction> newListOfTransactions = new ArrayList<>();
        File loadedFile = new File(FILE_NAME);

        if(!loadedFile.exists())
            return newListOfTransactions;

        //Tries to read the file
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                //Split the line in parts
                String[] lineParts = line.split("\\|");

                //The line has 7 parts
                if(lineParts.length < 7) continue;

                int transactionAmount = Integer.parseInt(lineParts[0]);
                Transaction.Type transactionType = Transaction.Type.valueOf(lineParts[1]);
                Transaction.Category transactionCategory = Transaction.Category.valueOf(lineParts[2]);
                LocalDate transactionDate = LocalDate.parse(lineParts[3]);
                int transactionHour = Integer.parseInt(lineParts[4]);
                int transactionMinute = Integer.parseInt(lineParts[5]);
                String transactionDescription = lineParts[6];

                //Reconstruct transactions
                Transaction loadedTransaction = new Transaction(transactionAmount, transactionType, transactionCategory,
                                                                transactionDate, transactionHour, transactionMinute,
                                                                transactionDescription);

                //Load transaction to the list
                newListOfTransactions.add(loadedTransaction);
            }
        }
        catch (IOException exception)
        {
            System.out.println("Error loading the file: " + exception.getMessage());
        }

        return newListOfTransactions;
    }
}
