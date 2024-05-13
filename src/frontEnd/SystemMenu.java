package frontEnd;

import backEnd.AbstractSystemMenu;
import javax.swing.*;
import administrator.AdminLogin;
import java.awt.*;
import java.awt.event.*;

public class SystemMenu extends AbstractSystemMenu {

    // Define buttons
    private JButton registerButton;
    private JButton vaccineCardButton;
    private JButton vaccineCertificateButton;
    private JButton adminLoginButton;

    public SystemMenu() {
        super("NSU COVID-19 Vaccination Management System"); // Call the constructor of the superclass

        // Set the frame icon
        Image titleIcon = new ImageIcon(this.getClass().getResource("/nsu-logo-small.png")).getImage();
        frame.setIconImage(titleIcon);

        // Set the layout manager for the frame
        frame.setLayout(new GridLayout(3, 0, 0, 1));

        // Create a panel for the first row with a navy blue background
        JPanel firstRowPanel = new JPanel(new FlowLayout());
        Color navyBlue = new Color(0, 43, 77);
        firstRowPanel.setBackground(navyBlue);
        frame.add(firstRowPanel);

        // Create and add an image component to the first row panel
        JLabel imageLabel1 = new JLabel(new ImageIcon(this.getClass().getResource("/phr-logo.png")));
        firstRowPanel.add(imageLabel1);

        // Create a panel for the second row with a navy blue background
        JPanel firstRowPanel1 = new JPanel(new FlowLayout());
        firstRowPanel1.setBackground(navyBlue);
        frame.add(firstRowPanel1);

        // Create and add an image component to the second row panel
        JLabel imageLabel2 = new JLabel(new ImageIcon(this.getClass().getResource("/shls-logo.png")));
        firstRowPanel1.add(imageLabel2);

        Color nsuCyan = new Color(51,143,190);

        // Create and configure the "User Registration" button
        registerButton = new JButton("User Registration");
        registerButton.setBackground(navyBlue);
        registerButton.setForeground(Color.WHITE);
        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                registerButton.setBackground(nsuCyan);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                registerButton.setBackground(navyBlue);
            }
        });
        frame.add(registerButton);

        // Create and configure the "User Info" button
        vaccineCardButton = new JButton("User Info");
        vaccineCardButton.setBackground(navyBlue);
        vaccineCardButton.setForeground(Color.WHITE);
        vaccineCardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                vaccineCardButton.setBackground(nsuCyan);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                vaccineCardButton.setBackground(navyBlue);
            }
        });
        frame.add(vaccineCardButton);

        // Create and configure the "Vaccine Certificate" button
        vaccineCertificateButton = new JButton("Vaccine Certificate");
        vaccineCertificateButton.setBackground(navyBlue);
        vaccineCertificateButton.setForeground(Color.WHITE);
        vaccineCertificateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                vaccineCertificateButton.setBackground(nsuCyan);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                vaccineCertificateButton.setBackground(navyBlue);
            }
        });
        frame.add(vaccineCertificateButton);

        Color nsuGold = new Color(225,186,77);
        Color nsuGreen = new Color(56,132,56);

        // Create and configure the "Admin Login" button
        adminLoginButton = new JButton("Admin Login");
        adminLoginButton.setBackground(nsuGold);
        adminLoginButton.setForeground(navyBlue);
        adminLoginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                adminLoginButton.setBackground(nsuGreen);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                adminLoginButton.setBackground(nsuGold);
            }
        });
        frame.add(adminLoginButton);

        // Add action listeners to the buttons

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserRegistration();
                disposeFrame();
            }
        });

        vaccineCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserInfo registrantInfo = new UserInfo();
                registrantInfo.setVisible(true);
                disposeFrame();
            }
        });

        vaccineCertificateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VaccineCertificate vaccineCertificate = new VaccineCertificate(new FileUserDataRetriever());
                vaccineCertificate.setVisible(true);
                disposeFrame();
            }
        });

        adminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminLogin adminLoginPage = new AdminLogin();
                adminLoginPage.setVisible(true);
                disposeFrame();
            }
        });

        // Set the background color of the frame to the color of the first row panel
        frame.setBackground(navyBlue);

        showFrame(); // Display the frame
    }

    public static void main(String[] args) {
        new SystemMenu(); // Create an instance of the SystemMenu class
    }
}
