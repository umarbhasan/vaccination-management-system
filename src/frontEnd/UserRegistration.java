package frontEnd;

import java.awt.event.*;
import java.io.*;
import java.util.Date;

import backEnd.UserRegistrationBase;

class UserRegistration extends UserRegistrationBase {
    public UserRegistration() {
        initializeUI();
        
        registerButton.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
			@Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                int age = Integer.parseInt(ageTextField.getText());
                String nsu = String.valueOf(nsuTextField.getText());
                String nidNumber = nidTextField.getText();
                String phoneNumber = phoneTextField.getText();
                String dose = String.valueOf(doseTextField.getText());
                String password = String.valueOf(passwordTextField.getPassword());
                String centerName = String.valueOf(centerTextField.getText());

                // Generate the vaccine appointment date.

                if(age >= 18 && nsu.equalsIgnoreCase("Yes") && dose.equalsIgnoreCase("Yes") && (centerName.equalsIgnoreCase("NAC") || centerName.equalsIgnoreCase("SAC"))) {
                	Date appointmentDate = new Date();
                	appointmentDate.setDate(appointmentDate.getDate() + 10);

                    // Check if there are already 20 appointments on the appointment date
                    int numberOfAppointments = getNumberOfAppointments(appointmentDate);
                    while (numberOfAppointments >= 20) {
                        appointmentDate.setDate(appointmentDate.getDate() + 1);
                        numberOfAppointments = getNumberOfAppointments(appointmentDate);
                    }

                    try {
                        File file = new File("registration.txt");
                        FileWriter fileWriter = new FileWriter(file, true);
                        fileWriter.write("Name: " + name + ";");
                        fileWriter.write("Age: " + age + ";");
                        fileWriter.write("NSUer? Yes/No?: " + nsu + ";");
                        fileWriter.write("NID Number: " + nidNumber + ";");
                        fileWriter.write("Phone Number: " + phoneNumber + ";");
                        fileWriter.write("First Time? Yes/No?: " + dose + ";");
                        fileWriter.write("Password: " + password + ";");
                        fileWriter.write("Center Name? NAC/SAC?: " + centerName + ";");
                        fileWriter.write("Appointment Date: " + appointmentDate +  ";");
                        fileWriter.write("Vaccination: " + "No" +  ";");
                        fileWriter.write("\n");
                        fileWriter.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    // Update the appointment date label.
                    emptyLabel.setText(" " + appointmentDate);
                }
                else {
                	@SuppressWarnings("unused")
					Date appointmentDate = new Date();
                	// Update the appointment date label.
                	emptyLabel.setText("Sorry! You Are Not Eligible For This Vaccine.");
                }
                

                }
        });
    }

    public static void main(String[] args) {
        new UserRegistration();
    }
}