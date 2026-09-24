package Model;

import java.time.LocalDate;

public class Transaction {

    public Transaction(int amount, Type typeOfTransaction, Category categoryOfTransaction,
                       LocalDate transactionDate, int transactionHour, int transactionMinute, String transactionDescription)
    {
        this.amount = amount;

        this.typeOfTransaction = typeOfTransaction;
        this.categoryOfTransaction = categoryOfTransaction;

        this.transactionDate = transactionDate;
        this.transactionMinute = transactionMinute;
        this.transactionHour = transactionHour;

        this.transactionDescription = transactionDescription;
    }

    public enum Type {INCOME, EXPENSE;

        @Override
        public String toString() {
            return switch (this)
            {
                case INCOME -> "Income";
                case EXPENSE -> "Expense";
            };
        }
    };
    private final Type typeOfTransaction;

    public enum Category{FOOD, TRANSPORT, HOUSEHOLD, SALARY, OTHERS;

        @Override
        public String toString() {
            return switch (this)
            {
                case FOOD -> "Food";
                case TRANSPORT -> "Transport";
                case HOUSEHOLD -> "Household";
                case SALARY -> "Salary";
                case OTHERS -> "Others";
            };
        }
    };
    private final Category categoryOfTransaction;

    private final int amount;
    private final LocalDate transactionDate;
    private final int transactionMinute;
    private final int transactionHour;
    private final String transactionDescription;

    public Type getTypeOfTransaction() {
        return typeOfTransaction;
    }

    public Category getCategoryOfTransaction() {
        return categoryOfTransaction;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public int getTransactionMinute() {
        return transactionMinute;
    }

    public int getTransactionHour() {
        return transactionHour;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }
}
