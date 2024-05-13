package frontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;


public class UserInfo extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField phoneNumberField;
    private JPasswordField passwordField;
    private JButton backButton;
    public UserInfo() {
        setTitle("User Info");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new BorderLayout());
        // Center the frame on the screen
        setLocationRelativeTo(null);
        
        Image titleIcon = new ImageIcon(this.getClass().getResource("/nsu-logo-small.png")).getImage();
        setIconImage(titleIcon);
        
        Color navyBlue = new Color(0, 43, 77);
        Color nsuGold = new Color(225,186,77);
        Color nsuGreen = new Color(56,132,56);
        
        // Panel for user input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.setBackground(navyBlue);
        inputPanel.add(new JLabel("Phone Number:"));
        JLabel input = (JLabel) inputPanel.getComponent(0);
        input.setForeground(Color.WHITE);
        phoneNumberField = new JTextField();
        inputPanel.add(phoneNumberField);
        inputPanel.add(new JLabel("Password:"));
        JLabel input1 = (JLabel) inputPanel.getComponent(2);
        input1.setForeground(Color.WHITE);
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);
        backButton = new JButton("Back");
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
        inputPanel.add(backButton);
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(nsuGreen);
        searchButton.setForeground(Color.WHITE);
        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                searchButton.setBackground(nsuGold);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                searchButton.setBackground(nsuGreen);
            }
        });
        inputPanel.add(searchButton);
        
        add(inputPanel);
      
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phoneNumber = phoneNumberField.getText();
                String password = new String(passwordField.getPassword());

                String userInfo = searchUserInfo(phoneNumber, password);
                JOptionPane.showMessageDialog(UserInfo.this, userInfo);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current page and open the landing page.
            	JOptionPane.showMessageDialog(UserInfo.this, "Do not forget your password!");
                new SystemMenu();
                dispose();
            }
        });
    }
        
    private String searchUserInfo(String phoneNumber, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("registration.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(";");

                String name = getValueByKey(userData, "Name");
                String age = getValueByKey(userData, "Age");
                String nsu = getValueByKey(userData, "NSUer? Yes/No?");
                String nidNumber = getValueByKey(userData, "NID Number");
                String filePhoneNumber = getValueByKey(userData, "Phone Number");
                String dose = getValueByKey(userData, "First Time? Yes/No?");
                String filePassword = getValueByKey(userData, "Password");
                String centerName = getValueByKey(userData,"Center Name? NAC/SAC?");
                String appointmentDate = getValueByKey(userData, "Appointment Date");
                String vaccination = getValueByKey(userData, "Vaccination");

                if (phoneNumber.equals(filePhoneNumber) && password.equals(filePassword)) {
                    StringBuilder userInfoBuilder = new StringBuilder();
                    userInfoBuilder.append("                    User Information               \n");
                    userInfoBuilder.append("Name: ").append(name).append("\n");
                    userInfoBuilder.append("Age: ").append(age).append("\n");
                    userInfoBuilder.append("NSUer?: ").append(nsu).append("\n");
                    userInfoBuilder.append("NID Number: ").append(nidNumber).append("\n");
                    userInfoBuilder.append("Phone Number: ").append(filePhoneNumber).append("\n");
                    userInfoBuilder.append("First Time?: ").append(dose).append("\n");
                    userInfoBuilder.append("Center Name: ").append(centerName).append("\n");
                    userInfoBuilder.append("Appointment Date: ").append(appointmentDate).append("\n");
                    userInfoBuilder.append("Vaccination: ").append(vaccination).append("\n");
                    
                    return userInfoBuilder.toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "User not found or incorrect credentials.";
    }
   

    private String getValueByKey(String[] userData, String key) {
        for (String data : userData) {
            String[] parts = data.split(": ");
            if (parts.length == 2 && parts[0].trim().equals(key)) {
                return parts[1].trim();
            }
        }
        return "";
    }
   



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UserInfo app = new UserInfo();
                app.setVisible(true);
            }
           
         });   
}
}