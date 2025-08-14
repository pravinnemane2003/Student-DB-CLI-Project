package com.student.demo;

import java.sql.*;
import java.util.Scanner;

public class StudentDBApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // 1. Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Connect to DB
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/school", 
                    "root", 
                    "root" 
            );

            while (true) {
                System.out.println("\n===== Student Management System =====");
                System.out.println("1. Add Student");
                System.out.println("2. View Student");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1: // INSERT
                        System.out.print("Enter Roll No: ");
                        int roll = sc.nextInt();
                        sc.nextLine(); // consume newline
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Address: ");
                        String address = sc.nextLine();

                        PreparedStatement insert = con.prepareStatement(
                                "INSERT INTO student (RollNo, Name, Address) VALUES (?, ?, ?)");
                        insert.setInt(1, roll);
                        insert.setString(2, name);
                        insert.setString(3, address);
                        int rowsInserted = insert.executeUpdate();
                        if (rowsInserted > 0) {
                            System.out.println("Student added successfully!");
                        }
                        break;

                    case 2: // SELECT
                        System.out.print("Enter Roll No to view: ");
                        int viewRoll = sc.nextInt();

                        PreparedStatement view = con.prepareStatement(
                                "SELECT * FROM student WHERE RollNo = ?");
                        view.setInt(1, viewRoll);
                        ResultSet rs = view.executeQuery();
                        if (rs.next()) {
                            System.out.println("Roll No: " + rs.getInt("RollNo"));
                            System.out.println("Name: " + rs.getString("Name"));
                            System.out.println("Address: " + rs.getString("Address"));
                        } else {
                            System.out.println("No student found with that roll number.");
                        }
                        break;

                    case 3: 
                        System.out.print("Enter Roll No to update: ");
                        int updateRoll = sc.nextInt();
                        sc.nextLine(); 
                        System.out.print("Enter New Name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter New Address: ");
                        String newAddress = sc.nextLine();

                        PreparedStatement update = con.prepareStatement(
                                "UPDATE student SET Name = ?, Address = ? WHERE RollNo = ?");
                        update.setString(1, newName);
                        update.setString(2, newAddress);
                        update.setInt(3, updateRoll);
                        int rowsUpdated = update.executeUpdate();
                        if (rowsUpdated > 0) {
                            System.out.println("Student updated successfully!");
                        } else {
                            System.out.println("No student found with that roll number.");
                        }
                        break;

                    case 4: 
                        System.out.print("Enter Roll No to delete: ");
                        int deleteRoll = sc.nextInt();

                        PreparedStatement delete = con.prepareStatement(
                                "DELETE FROM student WHERE RollNo = ?");
                        delete.setInt(1, deleteRoll);
                        int rowsDeleted = delete.executeUpdate();
                        if (rowsDeleted > 0) {
                            System.out.println("Student deleted successfully!");
                        } else {
                            System.out.println("No student found with that roll number.");
                        }
                        break;

                    case 5:
                        System.out.println("Exiting program...");
                        con.close();
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
