/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.progpoepart2;

/**
 *
 * @author RC_Student_lab
 */
import javax.swing.JOptionPane;

public class PROGPOEPART2 {
    public static void main(String[] args) {
        Login[] users = new Login[1]; // Array to store user accounts, assuming 3 users for demonstration

        // Register users
        for (int i = 0; i < users.length; i++) {
            users[i] = new Login(); // Initialize each element in the array as a Login object
            String newUsername = JOptionPane.showInputDialog("Register User "  + "\nEnter username:");
            String newPassword = JOptionPane.showInputDialog("Enter password:");
            String newFirstName = JOptionPane.showInputDialog("Enter first name:");
            String newLastName = JOptionPane.showInputDialog("Enter last name:");

            // Register the user and display the registration message
            String registrationMessage = users[i].registerUser(newUsername, newPassword, newFirstName, newLastName);
            JOptionPane.showMessageDialog(null, registrationMessage);
        }

        // Login users
        boolean loggedIn = false;
        String loginStatus = "Username or password incorrect, please try again.";

        // Loop until the user successfully logs in
        while (!loggedIn) {
            String enteredUsername = JOptionPane.showInputDialog("Login to the account\nEnter username:");
            String enteredPassword = JOptionPane.showInputDialog("Enter password:");

            for (Login user : users) {
                if (user.loginUser(enteredUsername, enteredPassword)) {
                    loggedIn = true;
                    loginStatus = user.returnLoginStatus();
                    break; // Exit the loop if a successful login is found
                }
            }

            JOptionPane.showMessageDialog(null, loginStatus);
        }

        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban!");

        // Main application loop
        boolean exit = false;
        Task[] tasks = null;

        while (!exit) {
            String menuOption = JOptionPane.showInputDialog(
                    "Please select an option:\n" +
                    "1. Add tasks\n" +
                    "2. Show report\n" +
                    "3. Quit"
            );

            switch (menuOption) {
                case "1":
                    int totalTasks = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of tasks you wish to enter:"));
                    tasks = new Task[totalTasks];

                    for (int i = 0; i < totalTasks; i++) {
                        JOptionPane.showMessageDialog(null, "Task " + (i + 1));
                        String taskName = JOptionPane.showInputDialog("Enter task name:");
                        String description;
                        do {
                            description = JOptionPane.showInputDialog("Enter task description (max 50 characters):");
                            if (description.length() > 50) {
                                JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters");
                            }
                        } while (description.length() > 50);

                        String firstName = JOptionPane.showInputDialog("Enter developer's first name:");
                        String lastName = JOptionPane.showInputDialog("Enter developer's last name:");
                        int duration = Integer.parseInt(JOptionPane.showInputDialog("Enter task duration in hours:"));

                        String[] statuses = {"To Do", "Done", "Doing"};
                        String status = (String) JOptionPane.showInputDialog(null, "Select task status:", "Task Status",
                                JOptionPane.QUESTION_MESSAGE, null, statuses, statuses[0]);

                        Task task = new Task(taskName, description, firstName, lastName, duration, status);
                        tasks[i] = task;

                        JOptionPane.showMessageDialog(null, "Task successfully captured");
                    }
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, "Coming soon");
                    break;
                case "3":
                    exit = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please select 1, 2, or 3.");
            }
        }

        // Display tasks
        if (tasks != null) {
            StringBuilder tasksDetails = new StringBuilder("Tasks:\n");
            for (Task task : tasks) {
                tasksDetails.append(task.printTaskDetails()).append("\n");
            }

            JOptionPane.showMessageDialog(null, tasksDetails.toString());

            // Calculate and display total hours
            int totalHours = Task.returnTotalHours(tasks);
            JOptionPane.showMessageDialog(null, "Total combined hours of all tasks: " + totalHours + " hours");
        }
    }
}
