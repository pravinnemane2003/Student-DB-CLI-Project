# Student Management CLI Project

A simple **Java Command-Line Interface (CLI) project** with MySQL database integration for managing student records.

## 📌 Features
- Add new students
- View student details
- Update student records
- Delete student records

## 🛠️ Technologies Used
- **Java** (JDBC for database connectivity)
- **MySQL**
- **Maven** (optional, if using)
- **Git & GitHub** for version control

- Run the following SQL commands:
- CREATE DATABASE studentdb;
 USE studentdb;

 CREATE TABLE student (
    rollno INT,
    name VARCHAR(50),
    address VARCHAR(100)
   );


  ## 📂 Project Structure
CLI-DB-Project/
│
├── src/
│   └── Main.java
│
├── lib/
│   └── mysql-connector-j-8.x.x.jar
│
├── .gitignore
├── README.md
└── pom.xml   (optional if using Maven)


