package views;

import javax.swing.*;

import controller.StockManager;
import model.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupScreen extends JFrame {
    private StockManager stockManager;

    public SignupScreen(StockManager stockManager) {
        this.stockManager = stockManager;
        setTitle("Sign Up");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        JButton signupButton = new JButton("Sign Up");
        signupButton.setBounds(10, 80, 80, 25);
        panel.add(signupButton);

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());

                // Validate input
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Username and Password cannot be blank.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Check if username already exists
                if (stockManager.userExists(username)) {
                    JOptionPane.showMessageDialog(null, "Username already exists. Please choose another.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validate password strength
                if (!isValidPassword(password)) {
                    JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, one digit, and one special character.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Create and add new user
                User newUser = new User(username, password);
                stockManager.addUser(newUser);

                JOptionPane.showMessageDialog(null, "User registered: " + username);
                new LoginScreen(stockManager);
                dispose(); // Close signup screen
            }
        });
    }

    private boolean isValidPassword(String password) {
        // Check for minimum length of 8 characters, at least one uppercase, one lowercase, one digit, and one special character
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$";
        return password.matches(passwordPattern);
    }

}

