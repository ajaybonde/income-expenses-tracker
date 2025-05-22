package tracker;

import java.util.Scanner;

public class ExpenseTracker {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TransactionManager manager = new TransactionManager();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Expense Tracker ---");
            System.out.println("1. Adding Income");
            System.out.println("2. Adding Expense");
            System.out.println("3. Show Monthly Summary");
            System.out.println("4. Load Transactions from File");
            System.out.println("5. Save Transactions to File");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> handleTransaction("income");
                case 2 -> handleTransaction("expense");
                case 3 -> manager.showMonthlySummary();
                case 4 -> {
                    System.out.print("Enter filename: ");
                    manager.loadFromFile(scanner.nextLine());
                }
                case 5 -> {
                    System.out.print("Enter filename: ");
                    manager.saveToFile(scanner.nextLine());
                }
                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void handleTransaction(String type) {
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        String categories = type.equals("income") ? "salary/business" : "food/rent/travel";
        System.out.print("Enter category (" + categories + "): ");
        String category = scanner.nextLine();

        manager.addTransaction(type, category, amount);
        System.out.println(type + " recorded.");
    }
}
