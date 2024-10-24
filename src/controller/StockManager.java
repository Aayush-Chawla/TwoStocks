package controller;

import model.Stock;
import model.User;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockManager {
    private List<User> users = new ArrayList<>();
    private Map<String, Stock> stockDatabase = new HashMap<>();
    private Map<String, Integer> stockQuantities = new HashMap<>(); // To track quantities

    public StockManager() {
        // Sample stocks for demonstration
        stockDatabase.put("TATA", new Stock("TATA", "Tata Motors", 150.0));
        stockQuantities.put("TATA", 200);
        stockDatabase.put("INFY", new Stock("INFY", "Infosys", 900.0));
        stockQuantities.put("INFY", 100);
        stockDatabase.put("RELIANCE", new Stock("RELIANCE", "Reliance Industries", 2500.0));
        stockQuantities.put("RELIANCE", 50);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // No matching user found
    }

    public Stock getStockBySymbol(String symbol) {
        return stockDatabase.get(symbol);
    }

    public void addStock(Stock stock, int quantity) {
        stockDatabase.put(stock.getSymbol(), stock);
        stockQuantities.put(stock.getSymbol(), quantity); // Update stock quantity
    }

    public int getAvailableQuantity(String symbol) {
        return stockQuantities.getOrDefault(symbol, 0); // Return available quantity
    }

    public List<Stock> getAvailableStocks() {
        return new ArrayList<>(stockDatabase.values());
    }

    // Method to get all stocks
    public Map<String, Stock> getAllStocks() {
        return stockDatabase; // Return all stocks
    }

    public void importStocksFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Skip header
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String symbol = values[0].trim();
                String name = values[1].trim();
                double price = Double.parseDouble(values[2].trim());
                int quantity = Integer.parseInt(values[3].trim());

                // Check if the stock already exists
                if (stockDatabase.containsKey(symbol)) {
                    // Update existing stock quantity
                    stockQuantities.put(symbol, stockQuantities.get(symbol) + quantity);
                } else {
                    // Add new stock
                    Stock newStock = new Stock(symbol, name, price);
                    stockDatabase.put(symbol, newStock);
                    stockQuantities.put(symbol, quantity);
                }
            }
            JOptionPane.showMessageDialog(null, "Stocks imported successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading CSV file: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid data format in CSV: " + e.getMessage());
        }
    }

}
