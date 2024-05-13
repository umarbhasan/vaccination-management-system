package backEnd;

import javax.swing.*;

import frontEnd.SystemMenu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UserRegistrationBase {
	// Declare components
    protected JFrame frame;
    protected JTextField nameTextField;
    protected JButton backButton;
    protected JTextField ageTextField;
    protected JTextField nsuTextField;
    protected JTextField nidTextField;
    protected JTextField phoneTextField;
    protected JTextField doseTextField;
    protected JPasswordField passwordTextField;
    protected JButton registerButton;
    protected JLabel appointmentDateLabel;
    protected JTextArea textArea1;
    protected JTextField centerTextField;
    protected JLabel emptyLabel;

    @SuppressWarnings("deprecation")
	protected int getNumberOfAppointments(Date date) {
        int count = 0;
        try {
            File file = new File("registration.txt");
            Scanner scanner = new Scanner(file);
            DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("Appointment Date: ")) {
                    String dateString = line.substring("Appointment Date: ".length());
                    Date appointmentDate = dateFormat.parse(dateString);
                    if (appointmentDate.getDate() == date.getDate() &&
                            appointmentDate.getMonth() == date.getMonth() &&
                            appointmentDate.getYear() == date.getYear()) {
                        count++;
                    }
                }
            }
            scanner.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return count;
    }

    protected void initializeUI() {
        frame = new JFrame("Registration Form");
        frame.setLayout(new GridLayout(0, 2, 0, 1));
        
        // Set the frame icon
        Image titleIcon = new ImageIcon(this.getClass().getResource("/nsu-logo-small.png")).getImage();
        frame.setIconImage(titleIcon);

        // Define colors
        Color navyBlue = new Color(0, 43, 77);
        Color nsuGold = new Color(225,186,77);
        Color nsuGreen = new Color(56,132,56);
        
        // Initialize and configure text fields
        nameTextField = new JTextField();
        nameTextField.setPreferredSize(new Dimension(200,25));
        nameTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Create a panel for the "Name" field with a navy blue background
        JPanel name = new JPanel(new FlowLayout());
        name.add(new JLabel("Name:"));
        name.setBackground(navyBlue);
        frame.add(name);
        frame.add(nameTextField);
        
        // Set the foreground (text) color of the "Name" label to white
        JLabel nameLabel = (JLabel) name.getComponent(0);
        nameLabel.setForeground(Color.WHITE);

        // Initialize and configure text fields
        ageTextField = new JTextField();
        ageTextField.setPreferredSize(new Dimension(200,25));
        ageTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
     
        // Create a panel for the "Age" field with a navy blue background
        JPanel age = new JPanel(new FlowLayout());
        age.add(new JLabel("Age:"));
        age.setBackground(navyBlue);
        frame.add(age);
        frame.add(ageTextField);
        
        // Set the foreground (text) color of the "Age" label to white
        JLabel ageLabel = (JLabel) age.getComponent(0);
        ageLabel.setForeground(Color.WHITE);
        
        // Initialize and configure text fields
        nsuTextField = new JTextField();
        nsuTextField.setPreferredSize(new Dimension(200,25));
        nsuTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Create a panel for the "NSUer? Yes/No?" field with a navy blue background        
        JPanel nsu = new JPanel(new FlowLayout());
        nsu.add(new JLabel("NSUer? Yes/No?:"));
        nsu.setBackground(navyBlue);
        frame.add(nsu);
        frame.add(nsuTextField);
        
        // Set the foreground (text) color of the "NSUer? Yes/No?" label to white
        JLabel nsuLabel = (JLabel) nsu.getComponent(0);
        nsuLabel.setForeground(Color.WHITE);
        
        // Initialize and configure text fields
        nidTextField = new JTextField();
        nidTextField.setPreferredSize(new Dimension(200,25));
        nidTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Create a panel for the "NID Number" field with a navy blue background                
        JPanel nid = new JPanel(new FlowLayout());
        nid.add(new JLabel("NID Number:"));
        nid.setBackground(navyBlue);
        frame.add(nid);
        frame.add(nidTextField);
        
        // Set the foreground (text) color of the "NID Number" label to white
        JLabel nidLabel = (JLabel) nid.getComponent(0);
        nidLabel.setForeground(Color.WHITE);

        // Initialize and configure text fields
        phoneTextField = new JTextField();
        phoneTextField.setPreferredSize(new Dimension(200,25));
        phoneTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Create a panel for the "Phone Number" field with a navy blue background                
        JPanel phone = new JPanel(new FlowLayout());
        phone.add(new JLabel("Phone Number:"));
        phone.setBackground(navyBlue);
        frame.add(phone);
        frame.add(phoneTextField);

        // Set the foreground (text) color of the "Phone Number" label to white
        JLabel phoneLabel = (JLabel) phone.getComponent(0);
        phoneLabel.setForeground(Color.WHITE);
        
        // Initialize and configure text fields
        doseTextField = new JTextField();
        doseTextField.setPreferredSize(new Dimension(200,25));
        doseTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
     
        // Create a panel for the "First Time? Yes/No?" field with a navy blue background
        JPanel dose = new JPanel(new FlowLayout());
        dose.add(new JLabel("First Time? Yes/No?:"));
        dose.setBackground(navyBlue);
        frame.add(dose);
        frame.add(doseTextField);

        // Set the foreground (text) color of the "First Time? Yes/No?" label to white
        JLabel doseLabel = (JLabel) dose.getComponent(0);
        doseLabel.setForeground(Color.WHITE);
        
        // Initialize and configure text fields
        centerTextField = new JTextField();
        centerTextField.setPreferredSize(new Dimension(200,25));
        centerTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Create a panel for the "Center Name? NAC/SAC?" field with a navy blue background
        JPanel center = new JPanel(new FlowLayout());
        center.add(new JLabel("Center Name? NAC/SAC?:"));
        center.setBackground(navyBlue);
        frame.add(center);
        frame.add(centerTextField);
        
        // Set the foreground (text) color of the "Center Name? NAC/SAC?" label to white
        JLabel centerLabel = (JLabel) center.getComponent(0);
        centerLabel.setForeground(Color.WHITE);

        // Initialize and configure text fields
        passwordTextField = new JPasswordField();
        passwordTextField.setPreferredSize(new Dimension(200,25));
        passwordTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Create a panel for the "Password" field with a navy blue background
        JPanel password = new JPanel(new FlowLayout());
        password.add(new JLabel("Password:"));
        password.setBackground(navyBlue);
        frame.add(password);
        frame.add(passwordTextField);
        
        // Set the foreground (text) color of the "Password" label to white
        JLabel passwordLabel = (JLabel) password.getComponent(0);
        passwordLabel.setForeground(Color.WHITE);
        
        // Create a panel for the "Appointment Date" field with a navy blue background
        JPanel appointmentDatePanel = new JPanel(new FlowLayout());
        appointmentDatePanel.add(new JLabel("Appointment Date:"));
        appointmentDatePanel.setBackground(navyBlue);
        frame.add(appointmentDatePanel);
        
        // Set the foreground (text) color of the "Appointment Date" label to white
        JLabel appointmentDateLabel = (JLabel) appointmentDatePanel.getComponent(0);
        appointmentDateLabel.setForeground(Color.WHITE);
        
        // Create an empty label for spacing purposes and to display the generated appointment date
        emptyLabel = new JLabel();
        emptyLabel.setPreferredSize(new Dimension(200, 25));
        emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emptyLabel.setBackground(Color.WHITE);
        emptyLabel.setForeground(Color.BLACK);
        frame.add(emptyLabel);
           
        // Configure the back button
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current page and open the landing page.
                frame.setVisible(false);
                new SystemMenu();
            }
        });
        backButton.setPreferredSize(new Dimension(200, 25));
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
        frame.add(backButton);
        
        // Configure the register button
        registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(200, 25));
        registerButton.setBackground(nsuGreen);
        registerButton.setForeground(Color.WHITE);
        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                registerButton.setBackground(nsuGold);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                registerButton.setBackground(nsuGreen);
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementation in child class
            }
        });

        frame.add(registerButton);

        // Configure frame properties
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Align the text fields and buttons in the middle of the frame
        nameTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
