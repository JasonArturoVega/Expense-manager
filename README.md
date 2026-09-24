# Personal Expense Tracker in Java

A desktop application to record income and expenses, view totals, and analyze spending categories.

## Features

- Record income and expenses
- Categories: Food, Transport, Household, Salary, Others
- Transaction history in a table
- Delete transactions
- Automatic calculation of: current balance, total received, total spent, Expense percentage by category
- automatic saving and loading of transactions when closing and opening the program respectively

## Screenshot

(https://github.com/JasonArturoVega/Expense-manager/blob/master/src/Image/Screenshot.png)

## Technologies Used

- Java
- IntellJ IDEA
- Swing
- Enums
- ArrayList
- File handling (BufferedReader / BufferedWriter)
- LocalDate / LocalDateTime

## How to Use

1. Enter the amount
2. Choose the type (`Income` or `Expense`) and category
3. (Optional) Add a description
4. Click **Confirm**
5. Select a row and use **Remove** to delete it
6. Everything saves automatically when closing the window

## How to Run

1. Clone the repository
2. Open the project in IntelliJ IDEA (or your favorite IDE)
3. Run the `MainExecution` class

## Project Structure

- `Main` → entry point
- `Model` → `Transaction` class and enums
- `Logic` → `TransactionManager` (totals and list)
- `Ui` → window, form, history, and summary panel
- `FileManagement` → saving and loading to/from `Transactions.txt`

## Key Takeaways

- Modeling financial transactions using enums
- Separating UI, logic, and persistence
- Calculating totals and percentages
- Using `JComboBox` with enums
- Object persistence in text files
- Synchronizing the visual table with the data list
