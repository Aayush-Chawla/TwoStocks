////package views;
////
////import javax.swing.*;
////import controller.StockManager;
////import model.User;
////import model.Stock;
////import java.awt.*;
////import java.awt.event.ActionEvent;
////import java.awt.event.ActionListener;
////import java.util.List;
////import java.util.Map;
////
////public class StockManagementScreen extends JFrame {
////    private User user;
////    private StockManager stockManager;
////
////    public StockManagementScreen(User user, StockManager stockManager) {
////        this.user = user;
////        this.stockManager = stockManager;
////        setTitle("Stock Management");
////        setSize(400, 300);
////        setDefaultCloseOperation(EXIT_ON_CLOSE);
////        setLocationRelativeTo(null);
////
////        JPanel panel = new JPanel();
////        add(panel);
////        placeComponents(panel);
////
////        setVisible(true);
////    }
////
////    private void placeComponents(JPanel panel) {
////        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
////
////        JButton addStockButton = new JButton("Add Stock");
////        JButton buyButton = new JButton("Buy Stock");
////        JButton sellButton = new JButton("Sell Stock");
////        JButton viewPortfolioButton = new JButton("View Portfolio");
////        JButton logoutButton = new JButton("Logout");
////
////        addStockButton.addActionListener(new ActionListener() {
////            public void actionPerformed(ActionEvent e) {
////                addStock();
////            }
////        });
////
////        buyButton.addActionListener(new ActionListener() {
////            public void actionPerformed(ActionEvent e) {
////                buyStock();
////            }
////        });
////
////        sellButton.addActionListener(new ActionListener() {
////            public void actionPerformed(ActionEvent e) {
////                sellStock();
////            }
////        });
////
////        viewPortfolioButton.addActionListener(new ActionListener() {
////            public void actionPerformed(ActionEvent e) {
////                viewPortfolio();
////            }
////        });
////
////        logoutButton.addActionListener(new ActionListener() {
////            public void actionPerformed(ActionEvent e) {
////                new LoginScreen(stockManager);
////                dispose(); // Close stock management screen
////            }
////        });
////
////        panel.add(addStockButton);
////        panel.add(buyButton);
////        panel.add(sellButton);
////        panel.add(viewPortfolioButton);
////        panel.add(logoutButton);
////    }
////
////    private void addStock() {
////        String symbol = JOptionPane.showInputDialog("Enter Stock Symbol:");
////        String name = JOptionPane.showInputDialog("Enter Stock Name:");
////        String priceStr = JOptionPane.showInputDialog("Enter Stock Price:");
////        double price = Double.parseDouble(priceStr);
////        String quantityStr = JOptionPane.showInputDialog("Enter Quantity:");
////        int quantity = Integer.parseInt(quantityStr);
////
////        Stock newStock = new Stock(symbol, name, price);
////        stockManager.addStock(newStock, quantity);
////
////        JOptionPane.showMessageDialog(this, "Successfully added stock: " + name + " with quantity: " + quantity);
////    }
////
////    private void buyStock() {
////        String symbol = JOptionPane.showInputDialog("Enter Stock Symbol:");
////        String quantityStr = JOptionPane.showInputDialog("Enter Quantity to Buy:");
////        int quantity = Integer.parseInt(quantityStr);
////
////        Stock stock = stockManager.getStockBySymbol(symbol);
////        if (stock != null) {
////            int availableQuantity = stockManager.getAvailableQuantity(symbol);
////            if (quantity <= availableQuantity) {
////                user.getPortfolio().addStock(symbol, quantity);
////                JOptionPane.showMessageDialog(this, "Successfully bought " + quantity + " shares of " + stock.getName());
////            } else {
////                JOptionPane.showMessageDialog(this, "Insufficient stock available. You can buy up to " + availableQuantity + " shares.");
////            }
////        } else {
////            JOptionPane.showMessageDialog(this, "Stock not found!");
////        }
////    }
////
////    private void sellStock() {
////        String symbol = JOptionPane.showInputDialog("Enter Stock Symbol to Sell:");
////        String quantityStr = JOptionPane.showInputDialog("Enter Quantity to Sell:");
////        int quantity = Integer.parseInt(quantityStr);
////
////        boolean success = user.getPortfolio().sellStock(symbol, quantity);
////        if (success) {
////            JOptionPane.showMessageDialog(this, "Successfully sold " + quantity + " shares of " + symbol);
////        } else {
////            JOptionPane.showMessageDialog(this, "Failed to sell stock. Check symbol and quantity.");
////        }
////    }
////
////    private void viewPortfolio() {
////        Map<String, Integer> stocks = user.getPortfolio().getStocks();
////        StringBuilder portfolioDetails = new StringBuilder("Your Portfolio:\n");
////
////        for (Map.Entry<String, Integer> entry : stocks.entrySet()) {
////            String stockSymbol = entry.getKey();
////            int quantity = entry.getValue();
////            Stock stock = stockManager.getStockBySymbol(stockSymbol);
////            if (stock != null) {
////                portfolioDetails.append(stock.getName())
////                        .append(" - ")
////                        .append(stock.getPrice())
////                        .append(" - ")
////                        .append(quantity)
////                        .append(" shares\n");
////            }
////        }
////
////        JOptionPane.showMessageDialog(this, portfolioDetails.toString(), "Portfolio", JOptionPane.INFORMATION_MESSAGE);
////    }
////}
//
//
//package views;
//
//import javax.swing.*;
//import controller.StockManager;
//import model.User;
//import model.Stock;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.Map;
//import java.util.List;
//import java.util.ArrayList;
//
//public class StockManagementScreen extends JFrame {
//    private User user;
//    private StockManager stockManager;
//
//    public StockManagementScreen(User user, StockManager stockManager) {
//        this.user = user;
//        this.stockManager = stockManager;
//        setTitle("Stock Management");
//        setSize(400, 300);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        JPanel panel = new JPanel();
//        add(panel);
//        placeComponents(panel);
//
//        setVisible(true);
//    }
//
//    private void placeComponents(JPanel panel) {
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//
//        JButton addStockButton = new JButton("Add Stock");
//        JButton buyButton = new JButton("Buy Stock");
//        JButton sellButton = new JButton("Sell Stock");
//        JButton viewPortfolioButton = new JButton("View Portfolio");
//        JButton logoutButton = new JButton("Logout");
//
//        addStockButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                addStock();
//            }
//        });
//
//        buyButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                buyStock();
//            }
//        });
//
//        sellButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                sellStock();
//            }
//        });
//
//        viewPortfolioButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                viewPortfolio();
//            }
//        });
//
//        logoutButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                new LoginScreen(stockManager);
//                dispose(); // Close stock management screen
//            }
//        });
//
//        panel.add(addStockButton);
//        panel.add(buyButton);
//        panel.add(sellButton);
//        panel.add(viewPortfolioButton);
//        panel.add(logoutButton);
//    }
//
//    private void addStock() {
//        String symbol = JOptionPane.showInputDialog("Enter Stock Symbol:");
//        String name = JOptionPane.showInputDialog("Enter Stock Name:");
//        String priceStr = JOptionPane.showInputDialog("Enter Stock Price:");
//        double price = Double.parseDouble(priceStr);
//        String quantityStr = JOptionPane.showInputDialog("Enter Quantity:");
//        int quantity = Integer.parseInt(quantityStr);
//
//        Stock newStock = new Stock(symbol, name, price);
//        stockManager.addStock(newStock, quantity);
//
//        JOptionPane.showMessageDialog(this, "Successfully added stock: " + name + " with quantity: " + quantity);
//    }
//
//    private void buyStock() {
//        String symbol = JOptionPane.showInputDialog("Enter Stock Symbol:");
//        String quantityStr = JOptionPane.showInputDialog("Enter Quantity to Buy:");
//        int quantity = Integer.parseInt(quantityStr);
//
//        Stock stock = stockManager.getStockBySymbol(symbol);
//        if (stock != null) {
//            int availableQuantity = stockManager.getAvailableQuantity(symbol);
//            if (quantity <= availableQuantity) {
//                user.getPortfolio().addStock(symbol, quantity);
//                JOptionPane.showMessageDialog(this, "Successfully bought " + quantity + " shares of " + stock.getName());
//            } else {
//                JOptionPane.showMessageDialog(this, "Insufficient stock available. You can buy up to " + availableQuantity + " shares.");
//            }
//
//        } else {
//            JOptionPane.showMessageDialog(this, "Stock not found!");
//        }
//    }
//
//    private void sellStock() {
//        String symbol = JOptionPane.showInputDialog("Enter Stock Symbol to Sell:");
//        String quantityStr = JOptionPane.showInputDialog("Enter Quantity to Sell:");
//        int quantity = Integer.parseInt(quantityStr);
//
//        boolean success = user.getPortfolio().sellStock(symbol, quantity);
//        if (success) {
//            JOptionPane.showMessageDialog(this, "Successfully sold " + quantity + " shares of " + symbol);
//        } else {
//            JOptionPane.showMessageDialog(this, "Failed to sell stock. Check symbol and quantity.");
//        }
//    }
//
//    private void viewPortfolio() {
//        Map<String, Integer> stocks = user.getPortfolio().getStocks();
//        List<String> portfolioDetails = new ArrayList<>();
//
//        for (Map.Entry<String, Integer> entry : stocks.entrySet()) {
//            String stockSymbol = entry.getKey();
//            int quantity = entry.getValue();
//            Stock stock = stockManager.getStockBySymbol(stockSymbol);
//            if (stock != null) {
//                portfolioDetails.add(stock.getName() + " - " + stock.getPrice() + " - " + quantity + " shares");
//            }
//        }
//
//        JList<String> portfolioList = new JList<>(portfolioDetails.toArray(new String[0]));
//        JOptionPane.showMessageDialog(this, new JScrollPane(portfolioList), "Your Portfolio", JOptionPane.INFORMATION_MESSAGE);
//    }
//}


package views;

import javax.swing.*;
import controller.StockManager;
import model.User;
import model.Stock;
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
        setTitle("Stock Management");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton addStockButton = new JButton("Add Stock");
        JButton buyButton = new JButton("Buy Stock");
        JButton sellButton = new JButton("Sell Stock");
        JButton viewPortfolioButton = new JButton("View Portfolio");
        JButton importStocksButton = new JButton("Import Stocks from CSV");
        JButton logoutButton = new JButton("Logout");

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

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginScreen(stockManager);
                dispose(); // Close stock management screen
            }
        });

        panel.add(addStockButton);
        panel.add(buyButton);
        panel.add(sellButton);
        panel.add(viewPortfolioButton);
        panel.add(importStocksButton);
        panel.add(logoutButton);
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

    public Map<String, Stock> getAllStocks() {
        return stockDatabase;
    }
}
