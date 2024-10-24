package views;

import javax.swing.*;
import controller.StockManager;
import model.User;
import model.Stock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StockManagementScreen extends JFrame {
    private User user;
    private StockManager stockManager;

    public StockManagementScreen(User user, StockManager stockManager) {
        this.user = user;
        this.stockManager = stockManager;
        setTitle("Stock Tracker");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set the background color for the entire frame
        getContentPane().setBackground(new Color(230, 230, 250));  // Light lavender color

        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 248, 255));  // Alice blue background for panel
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(new GridBagLayout()); // Use GridBagLayout for better centering
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE; // Positioning vertically
        gbc.insets = new Insets(10, 0, 10, 0); // Add some padding between buttons
        gbc.anchor = GridBagConstraints.CENTER; // Center alignment

        // Custom Font
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        // Create Buttons with more attractive design
        JButton addStockButton = createStyledButton("Add Stock", buttonFont, new Color(102, 204, 255));
        JButton buyButton = createStyledButton("Buy Stock", buttonFont, new Color(102, 255, 102));
        JButton sellButton = createStyledButton("Sell Stock", buttonFont, new Color(255, 102, 102));
        JButton viewPortfolioButton = createStyledButton("View Portfolio", buttonFont, new Color(102, 204, 255));
        JButton importStocksButton = createStyledButton("Import Stocks from CSV", buttonFont, new Color(102, 204, 255));
        JButton showAllStocksButton = createStyledButton("All Stocks", buttonFont, new Color(153, 204, 255));
        JButton logoutButton = createStyledButton("Logout", buttonFont, new Color(192, 192, 192));

        // Action Listeners for buttons
        addStockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStock();
            }
        });

        buyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buyStock();
            }
        });

        sellButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sellStock();
            }
        });

        viewPortfolioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewPortfolio();
            }
        });

        importStocksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                importStocks();
            }
        });

        showAllStocksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAllStocks();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginScreen(stockManager);
                dispose();
            }
        });

        // Add buttons to the panel with layout constraints
        panel.add(addStockButton, gbc);
        panel.add(buyButton, gbc);
        panel.add(sellButton, gbc);
        panel.add(viewPortfolioButton, gbc);
        panel.add(importStocksButton, gbc);
        panel.add(showAllStocksButton, gbc);
        panel.add(logoutButton, gbc);
    }

    // Method to create a styled button
    private JButton createStyledButton(String text, Font font, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setBackground(backgroundColor);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false); // Remove the focus outline
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),  // Outer border
                BorderFactory.createEmptyBorder(5, 15, 5, 15)   // Padding inside the button
        ));
        return button;
    }

    private void addStock() {
        String symbol = JOptionPane.showInputDialog("Enter Stock Symbol:");
        String name = JOptionPane.showInputDialog("Enter Stock Name:");
        String priceStr = JOptionPane.showInputDialog("Enter Stock Price:");
        double price = Double.parseDouble(priceStr);
        String quantityStr = JOptionPane.showInputDialog("Enter Quantity:");
        int quantity = Integer.parseInt(quantityStr);

        Stock newStock = new Stock(symbol, name, price);
        stockManager.addStock(newStock, quantity);

        JOptionPane.showMessageDialog(this, "Successfully added stock: " + name + " with quantity: " + quantity);
    }

    private void buyStock() {
        String symbol = JOptionPane.showInputDialog("Enter Stock Symbol:");
        String quantityStr = JOptionPane.showInputDialog("Enter Quantity to Buy:");
        int quantity = Integer.parseInt(quantityStr);

        Stock stock = stockManager.getStockBySymbol(symbol);
        if (stock != null) {
            int availableQuantity = stockManager.getAvailableQuantity(symbol);
            if (quantity <= availableQuantity) {
                user.getPortfolio().addStock(symbol, quantity);
                JOptionPane.showMessageDialog(this, "Successfully bought " + quantity + " shares of " + stock.getName());
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient stock available. You can buy up to " + availableQuantity + " shares.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Stock not found!");
        }
    }

    private void sellStock() {
        String symbol = JOptionPane.showInputDialog("Enter Stock Symbol to Sell:");
        String quantityStr = JOptionPane.showInputDialog("Enter Quantity to Sell:");
        int quantity = Integer.parseInt(quantityStr);

        boolean success = user.getPortfolio().sellStock(symbol, quantity);
        if (success) {
            JOptionPane.showMessageDialog(this, "Successfully sold " + quantity + " shares of " + symbol);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to sell stock. Check symbol and quantity.");
        }
    }

    private void viewPortfolio() {
        Map<String, Integer> stocks = user.getPortfolio().getStocks();
        List<String> portfolioDetails = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : stocks.entrySet()) {
            String stockSymbol = entry.getKey();
            int quantity = entry.getValue();
            Stock stock = stockManager.getStockBySymbol(stockSymbol);
            if (stock != null) {
                portfolioDetails.add(stock.getName() + " - " + stock.getPrice() + " - " + quantity + " shares");
            }
        }

        JList<String> portfolioList = new JList<>(portfolioDetails.toArray(new String[0]));
        JOptionPane.showMessageDialog(this, new JScrollPane(portfolioList), "Your Portfolio", JOptionPane.INFORMATION_MESSAGE);
    }

    private void importStocks() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select CSV File");

        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToImport = fileChooser.getSelectedFile();
            String filePath = fileToImport.getAbsolutePath();
            stockManager.importStocksFromCSV(filePath);
        } else {
            JOptionPane.showMessageDialog(this, "No file selected.");
        }
    }

    // New method to show all stocks
    private void showAllStocks() {
        List<String> stockDetails = new ArrayList<>();
        for (Map.Entry<String, Stock> entry : stockManager.getAllStocks().entrySet()) {
            String symbol = entry.getKey();
            Stock stock = entry.getValue();
            int quantity = stockManager.getAvailableQuantity(symbol);
            stockDetails.add(stock.getName() + " (" + symbol + ") - Price: $" + stock.getPrice() + " - Available: " + quantity + " shares");
        }

        JList<String> stockList = new JList<>(stockDetails.toArray(new String[0]));
        JOptionPane.showMessageDialog(this, new JScrollPane(stockList), "All Stocks", JOptionPane.INFORMATION_MESSAGE);
    }
}
