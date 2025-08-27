Contact Manager (Java + Oracle SQL)
===================================

A simple console-based Contact Manager application built using Java and Oracle Database (SQL Plus).
This project allows users to add, view, and delete contacts with persistent storage in an Oracle database.

Features
--------
- Save new contacts (Name, Email, Phone)
- Delete existing contacts
- View all saved contacts
- Oracle Database integration using JDBC

Technologies Used
-----------------
- Java (JDK 17 or above recommended)
- Oracle Database (SQL Plus / XE / 19c)
- JDBC (Oracle Thin Driver)
- Eclipse IDE / IntelliJ IDEA / VS Code

Database Setup
--------------
1. Login to Oracle SQL Plus:
   sqlplus system@localhost:1521/orcl

2. Create the contacts table:
   CREATE TABLE contacts (
       id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
       name VARCHAR2(100) NOT NULL,
       email VARCHAR2(150) UNIQUE,
       phone VARCHAR2(20) UNIQUE
   );

How to Run
----------
1. Clone this repository:
   git clone https://github.com/YourUsername/ContactManager.git

2. Open project in Eclipse or your preferred IDE.

3. Make sure the Oracle JDBC driver (ojdbc8.jar) is added to your project’s classpath.

4. Update database credentials in ContactManager.java:
   String url = "jdbc:oracle:thin:@localhost:1521:orcl";
   String user = "system";
   String password = "your_password";

5. Run the program.

Example Usage
-------------
===== Contact Manager =====
1. Save Contact
2. Delete Contact
3. View Contacts
4. Exit
Enter your choice: 1
Enter Name: John Doe
Enter Email: john@example.com
Enter Phone: 9876543210
Contact saved successfully!

Project Structure
-----------------
ContactManager/
│── src/
│   └── contactmanager/
│       └── ContactManager.java
│
│── database.sql
│── README.txt


License
-------
This project is open-source and available under the MIT License.