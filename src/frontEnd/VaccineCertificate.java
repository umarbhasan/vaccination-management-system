package frontEnd;

import javax.swing.*;

import backEnd.UserDataRetriever;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

class FileUserDataRetriever implements UserDataRetriever {
    @Override
    public String getUserInfo(String phoneNumber, String password) {
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
                    if(vaccination.equalsIgnoreCase("Yes")) {
                    	StringBuilder userInfoBuilder = new StringBuilder();
                        userInfoBuilder.append("                            COVID-19 Vaccination Certificate               \n");
                        userInfoBuilder.append("Name: ").append(name).append("\n");
                        userInfoBuilder.append("Age: ").append(age).append("\n");
                        userInfoBuilder.append("NSUer? Yes/No?: ").append(nsu).append("\n");
                        userInfoBuilder.append("NID Number: ").append(nidNumber).append("\n");
                        userInfoBuilder.append("Phone Number: ").append(filePhoneNumber).append("\n");
                        userInfoBuilder.append("First Time? Yes/No?: ").append(dose).append("\n");
                        userInfoBuilder.append("Center Name? NAC/SAC?: ").append(centerName).append("\n");
                        userInfoBuilder.append("Appointment Date: ").append(appointmentDate).append("\n");
                        userInfoBuilder.append("Vaccination: ").append(vaccination).append("\n");
                        
                        return userInfoBuilder.toString();
                    }
                    else {
                    	return "negative";	
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
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
}

public class VaccineCertificate extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField phoneNumberField;
    private JPasswordField passwordField;
    private JTextArea certificateArea;
    private JButton generateButton;
    private JButton backButton;

    public VaccineCertificate(UserDataRetriever userDataRetriever) {
        setTitle("Vaccine Certificate");
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
        inputPanel.add(backButton);       
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
            	JOptionPane.showMessageDialog(VaccineCertificate.this, "Do not forget your password!");
                new SystemMenu();
                dispose();
            }
        });
        
        generateButton = new JButton("Generate Certificate");
        generateButton.setBackground(nsuGreen);
        generateButton.setForeground(Color.WHITE);
        inputPanel.add(generateButton);
        generateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                generateButton.setBackground(nsuGold);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                generateButton.setBackground(nsuGreen);
            }
        });
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String phoneNumber = phoneNumberField.getText();
                String password = new String(passwordField.getPassword());
            	String certificateInfo = showInfo(phoneNumber, password, userDataRetriever);
                
            	if(!certificateInfo.equals("Ineligible")) {
            		String certificateFileName = generateCertificate(certificateInfo);
                    JOptionPane.showMessageDialog(VaccineCertificate.this, "Certificate generated successfully.\nFilename: " + certificateFileName);
                    new SystemMenu();
                    dispose();
            	}
                else {
                	JOptionPane.showMessageDialog(certificateArea, "You are not eligible for the certificate.");
                	new SystemMenu();
                    dispose();
                }
            }
        });
        add(inputPanel);
    }

    private String showInfo(String phoneNumber, String password, UserDataRetriever userDataRetriever) {
        // Retrieve user information based on phone number and password
        String userInfo = userDataRetriever.getUserInfo(phoneNumber, password);

        if (!userInfo.isEmpty() && !userInfo.equals("negative")) {
            StringBuilder certificateBuilder = new StringBuilder();
            certificateBuilder.append(userInfo);
            return certificateBuilder.toString();
        }
        
        return  "Ineligible";
    }

    private String generateCertificate(String certificateInfo) {
        String name = getValueByKey(certificateInfo.split("\n"), "Name");
        String certificateFileName = name + "_Certificate.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(certificateFileName))) {
            writer.write(certificateInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return certificateFileName;
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
                UserDataRetriever userDataRetriever = new FileUserDataRetriever();
                VaccineCertificate app = new VaccineCertificate(userDataRetriever);
                app.setVisible(true);
            }
        });
    }
}