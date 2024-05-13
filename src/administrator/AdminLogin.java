package administrator;

import javax.swing.*;
import frontEnd.SystemMenu;
import java.awt.*;
import java.awt.event.*;

public class AdminLogin extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
    private JPasswordField passwordField;

    public AdminLogin() {
    	
    	Color navyBlue = new Color(0, 43, 77);
        Color nsuGold = new Color(225,186,77);
        Color nsuGreen = new Color(56,132,56);
        
        setTitle("Admin Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        // Center the frame on the screen
        setLocationRelativeTo(null);
        
        Image titleIcon = new ImageIcon(this.getClass().getResource("/nsu-logo-small.png")).getImage();
        setIconImage(titleIcon);
        
        // Panel for user input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.setBackground(navyBlue);
        inputPanel.add(new JLabel("Username:"));
        JLabel input = (JLabel) inputPanel.getComponent(0);
        input.setForeground(Color.WHITE);
        usernameField = new JTextField();
        inputPanel.add(usernameField);
        inputPanel.add(new JLabel("Password:"));
        JLabel input1 = (JLabel) inputPanel.getComponent(2);
        input1.setForeground(Color.WHITE);
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);
        
        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setBackground(nsuGold);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setBackground(Color.RED);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current page and open the landing page.
            	JOptionPane.showMessageDialog(AdminLogin.this, "Do not forget your username and password!");
                new SystemMenu();
                dispose();
            }
        });
        inputPanel.add(backButton);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(nsuGreen);
        loginButton.setForeground(Color.WHITE);
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setBackground(nsuGold);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setBackground(nsuGreen);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (authenticate(username, password)) {
                    JOptionPane.showMessageDialog(AdminLogin.this, "Login successful!");
                    new AppointmentList();
                    dispose();
                } else {
                    // Login failed, display error message
                    JOptionPane.showMessageDialog(AdminLogin.this, "Invalid credentials. Please try again.");
                }
            }
        });
        inputPanel.add(loginButton);
        
        add(inputPanel);
    }

    private boolean authenticate(String username, String password) {
        // Perform authentication logic here
        
        String adminUsername = "admin";
        String adminPassword = "admin123";

        return username.equals(adminUsername) && password.equals(adminPassword);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AdminLogin adminLoginPage = new AdminLogin();
                adminLoginPage.setVisible(true);
            }
        });
    }
}
