package tracker;
import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class TransactionManager {
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(String type, String category, double amount) {
        LocalDate date = LocalDate.now();
        transactions.add(new Transaction(type, category, amount, date));
    }

    public void showMonthlySummary() {
        Month currentMonth = LocalDate.now().getMonth();
        double totalIncome = 0;
        double totalExpense = 0;
        Map<String, Double> incomeSummary = new HashMap<>();
        Map<String, Double> expenseSummary = new HashMap<>();

        for (Transaction t : transactions) {
            if (t.date.getMonth() == currentMonth) {
                if (t.type.equals("income")) {
                    incomeSummary.put(t.category, incomeSummary.getOrDefault(t.category, 0.0) + t.amount);
                    totalIncome += t.amount;
                } else {
                    expenseSummary.put(t.category, expenseSummary.getOrDefault(t.category, 0.0) + t.amount);
                    totalExpense += t.amount;
                }
            }
        }

        System.out.println("\n--- Monthly Summary for " + currentMonth + " ---");
        System.out.println("Total Income: " + totalIncome);
        incomeSummary.forEach((cat, amt) -> System.out.println("  " + cat + ": " + amt));

        System.out.println("Total Expense: " + totalExpense);
        expenseSummary.forEach((cat, amt) -> System.out.println("  " + cat + ": " + amt));
    }

    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Transaction t : transactions) {
                writer.println(t.toFileFormat());
            }
            System.out.println("Transactions saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                transactions.add(Transaction.fromFileFormat(line));
            }
            System.out.println("Transactions loaded from " + filename);
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
}