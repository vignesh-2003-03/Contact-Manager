

import java.awt.*;
import java.awt.event.*;

public class ContactManger extends Frame {
    private TextField nameField = new TextField();
    private TextField emailField = new TextField();
    private TextField phoneField = new TextField();
    private TextArea contactDisplay = new TextArea();
    private Button saveButton = new Button("Save");
    private Button deleteButton = new Button("Delete");
    private Button viewButton = new Button("View");
    private String[] contacts = new String[150]; 
    private int contactCount = 0;

    public ContactManger() {
        setTitle("Contact Manager");
        setSize(500, 600);
        setLayout(new GridLayout(7, 2, 5, 5));

        add(new Label("Name:"));
        add(nameField);

        add(new Label("Email:"));
        add(emailField);

        add(new Label("Phone Number:"));
        add(phoneField);
        
        add(saveButton);
        add(deleteButton);
        add(viewButton);
        add(contactDisplay);

        saveButton.addActionListener(e -> saveContact());
        deleteButton.addActionListener(e -> deleteContact());
        viewButton.addActionListener(e -> viewContacts());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    private void saveContact() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        
        if (contactCount < 50) {
            contacts[contactCount++] = name;
            contacts[contactCount++] = email;
            contacts[contactCount++] = phone;
            contactDisplay.setText("Contact saved successfully.");
        } else {
            contactDisplay.setText("Contact list is full.");
        }
    }

    private void deleteContact() {
        String nameToDelete = nameField.getText();
        boolean found = false;

        for (int i = 0; i < contactCount; i += 3) {
            if (contacts[i].equals(nameToDelete)) {
               
                for (int j = i; j < contactCount - 3; j++) {
                    contacts[j] = contacts[j + 3];
                }
                contactCount -= 3;
                contactDisplay.setText("Contact deleted successfully.");
                found = true;
                break;
            }
        }
        if (!found) {
            contactDisplay.setText("Contact not found.");
        }
    }

    private void viewContacts() {
        contactDisplay.setText("");
        for (int i = 0; i < contactCount; i += 3) {
            contactDisplay.append("Name: " + contacts[i] + "\nEmail: " + contacts[i + 1] + "\nPhone Number: " + contacts[i + 2] + "\n\n");
        }
        if (contactCount == 0) {
            contactDisplay.setText("No contacts to display.");
        }
    }

    public static void main(String[] args) {
        new ContactManger().setVisible(true);
    }
}
