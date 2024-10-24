import controller.StockManager;
import model.Stock;
import views.LoginScreen;

import javax.swing.*;


public class StockManagementApp {
    public static void main(String[] args) {
        StockManager stockManager = new StockManager();
        new LoginScreen(stockManager);
    }
}
