package model;

import controller.StockManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Portfolio {
    private Map<String, Integer> stocks; // Key: Stock Symbol, Value: Quantity

    public Portfolio() {
        stocks = new HashMap<>();
    }

    public void addStock(String symbol, int quantity) {
        stocks.put(symbol, stocks.getOrDefault(symbol, 0) + quantity);
    }

    public boolean sellStock(String symbol, int quantity) {
        if (!stocks.containsKey(symbol) || stocks.get(symbol) < quantity) {
            return false; // Not enough stock to sell
        }

        // Reduce the quantity of stocks
        stocks.put(symbol, stocks.get(symbol) - quantity);

        // Remove stock entry if quantity reaches zero
        if (stocks.get(symbol) == 0) {
            stocks.remove(symbol);
        }

        return true; // Sale successful
    }

    public Map<String, Integer> getStocks() {
        return stocks;
    }

    public int getQuantity(String symbol) {
        return stocks.getOrDefault(symbol, 0);
    }

    public List<Stock> getBoughtStocks(StockManager stockManager) {
        List<Stock> boughtStocks = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : stocks.entrySet()) {
            String symbol = entry.getKey();
            int quantity = entry.getValue();
            // Retrieve the stock details from the stockManager
            Stock stock = stockManager.getStockBySymbol(symbol);
            if (stock != null) {
                // Add the stock to the list for the quantity owned
                for (int i = 0; i < quantity; i++) {
                    boughtStocks.add(new Stock(stock.getSymbol(), stock.getName(), stock.getPrice()));
                }
            }
        }
        return boughtStocks;
    }
}
