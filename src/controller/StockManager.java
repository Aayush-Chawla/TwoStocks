package controller;

import model.Stock;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockManager {
    private List<User> users = new ArrayList<>();
    private Map<String, Stock> stockDatabase = new HashMap<>();

    public StockManager() {
        // Sample stocks for demonstration
        stockDatabase.put("TATA", new Stock("TATA", "Tata Motors", 150.0));
        stockDatabase.put("INFY", new Stock("INFY", "Infosys", 900.0));
        stockDatabase.put("RELIANCE", new Stock("RELIANCE", "Reliance Industries", 2500.0));
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

    public void addStock(Stock stock) {
        stockDatabase.put(stock.getSymbol(), stock);
    }

    public List<Stock> getAvailableStocks() {
        return new ArrayList<>(stockDatabase.values());
    }
}
