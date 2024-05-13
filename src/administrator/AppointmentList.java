package administrator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import frontEnd.SystemMenu;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

class AppointmentList extends JFrame {
    
	private static final long serialVersionUID = 1L;
	private JTable appointmentTable;
    private DefaultTableModel tableModel;

    public AppointmentList() {
    	
    	Color navyBlue = new Color(0, 43, 77);
        Color nsuGold = new Color(225,186,77);
        Color nsuGreen = new Color(56,132,56);
        
        setTitle("Appointment List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        // Center the frame on the screen
        setLocationRelativeTo(null);
        
        Image titleIcon = new ImageIcon(this.getClass().getResource("/nsu-logo-small.png")).getImage();
        setIconImage(titleIcon);

        String[] columnNames = {"Name", "Age", "NSUer?", "NID Number", "Phone Number", "First Time?", "Password", "Center Name", "Appointment Date", "Vaccination"};
        String[][] appointmentData = getAppointmentData("registration.txt");
        tableModel = new DefaultTableModel(appointmentData, columnNames);
        appointmentTable = new JTable(tableModel);
        appointmentTable.setBackground(navyBlue);
        appointmentTable.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(appointmentTable);
        add(scrollPane, BorderLayout.CENTER);
        
        JPanel adminPanel = new JPanel();
        adminPanel.setLayout(new GridLayout(2, 1));
        JButton completeButton = new JButton("Complete Vaccination");
        completeButton.setBackground(nsuGreen);
        completeButton.setForeground(Color.WHITE);
        completeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                completeButton.setBackground(nsuGold);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                completeButton.setBackground(nsuGreen);
            }
        });
        adminPanel.add(completeButton);
        
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
        adminPanel.add(backButton);

        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = appointmentTable.getSelectedRow();
                if (selectedRow != -1) {
                    String vaccinationStatus = tableModel.getValueAt(selectedRow, 9).toString();
                    if (vaccinationStatus.equals("No")) {
                        tableModel.setValueAt("Yes", selectedRow, 9);
                        updateVaccinationStatus(selectedRow);
                    }
                }
            }
        });
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current page and open the landing page.
            	JOptionPane.showMessageDialog(AppointmentList.this, "Do not forget your password!");
                new SystemMenu();
                dispose();
            }
        });
        
        add(adminPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private String[][] getAppointmentData(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder dataBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                dataBuilder.append(line).append("\n");
            }

            String[] appointments = dataBuilder.toString().split("\n");
            String[][] appointmentData = new String[appointments.length][];
            for (int i = 0; i < appointments.length; i++) {
                String[] appointmentFields = appointments[i].split(";");
                appointmentData[i] = new String[appointmentFields.length];
                for (int j = 0; j < appointmentFields.length; j++) {
                    appointmentData[i][j] = getValueByKey(appointmentFields[j].trim().split(": "), 1);
                }
            }
            return appointmentData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[0][0];
    }

    private String getValueByKey(String[] data, int index) {
        if (index < 0 || index >= data.length) {
            return "";
        }
        return data[index].trim();
    }

    private void updateVaccinationStatus(int rowIndex) {
        try (BufferedReader reader = new BufferedReader(new FileReader("registration.txt"))) {
            StringBuilder fileData = new StringBuilder();
            String line;
            int currentRow = 0;
            while ((line = reader.readLine()) != null) {
                if (currentRow == rowIndex) {
                    line = line.replace("Vaccination: No", "Vaccination: Yes");
                }
                fileData.append(line).append("\n");
                currentRow++;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("registration.txt"))) {
                writer.write(fileData.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                @SuppressWarnings("unused")
				AppointmentList appointmentListPage = new AppointmentList();
            }
        });
    }
}
