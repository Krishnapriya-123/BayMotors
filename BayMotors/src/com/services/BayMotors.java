package com.services;
import java.util.Scanner;
import com.dao.EmployeeDao;

public class BayMotors {

    public static void main(String[] args) {
        String username;
        String password;
        String role = null; // 1 - manager, 2 - mechanic
        int roleId;
        boolean loginStatus = false;

        Scanner sc = new Scanner(System.in);

        while (!loginStatus) { // Loop until login is successful
            System.out.println("Welcome to Bay Motors");
            System.out.println("Please Login");

            System.out.print("Enter Username: ");
            username = sc.nextLine();

            System.out.print("Enter Password: ");
            password = sc.nextLine();

            System.out.print("Select Role: 1 - Manager, 2 - Mechanic: ");
            roleId = sc.nextInt();
            sc.nextLine(); // Consume the leftover newline

            if (roleId != 1 && roleId != 2) {
                System.out.println("Invalid Role. Please select from the given Roles.");
                continue; // Skip the rest of the loop and re-prompt
            }

            role = roleId == 1 ? "Manager" : "Mechanic";
            loginStatus = EmployeeDao.validateEmployee(username, password, role);

            if (loginStatus) {
                System.out.println("Logged in successfully!");
                EmployeeDao.getDashboard(role);
            } else {
                System.out.println("Incorrect Credentials. Please try again.");
            }
        }

        sc.close();
    }
}
