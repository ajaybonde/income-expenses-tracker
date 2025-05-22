package tracker;

import java.time.LocalDate;

public class Transaction {
    public String type; 
    public String category;
    public double amount;
    public LocalDate date;

    public Transaction(String type, String category, double amount, LocalDate date) {
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public String toFileFormat() {
        return String.format("%s,%s,%.2f,%s", type, category, amount, date.toString());
    }

    public static Transaction fromFileFormat(String line) {
        String[] parts = line.split(",");
        return new Transaction(parts[0], parts[1], Double.parseDouble(parts[2]), LocalDate.parse(parts[3]));
    }
}