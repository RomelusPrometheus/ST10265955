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
        Login[] users = new Login[1]; 

        // Register users
        for (int i = 0; i < users.length; i++) {
            users[i] = new Login(); 
            String newUsername = JOptionPane.showInputDialog("Register User " + (i) + "\nEnter username:");
            String newPassword = JOptionPane.showInputDialog("Enter password:");
            String newFirstName = JOptionPane.showInputDialog("Enter first name:");
            String newLastName = JOptionPane.showInputDialog("Enter last name:");

            
            String registrationMessage = users[i].registerUser(newUsername, newPassword, newFirstName, newLastName);
            JOptionPane.showMessageDialog(null, registrationMessage);
        }

        // Login The User
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
                    break;                 }
            }

            JOptionPane.showMessageDialog(null, loginStatus);
        }

        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban!");

        // Main application loop
        boolean exit = false;
        Task[] tasks = null;
        String[] developers = null;
        String[] taskNames = null;
        String[] taskIDs = null;
        int[] taskDurations = null;
        String[] taskStatuses = null;

        while (!exit) {
            String menuOption = JOptionPane.showInputDialog(
                    "Please select an option:\n" +
                    "1. Add tasks\n" +
                    "2. Show report\n" +
                    "3. Quit\n" +
                    "4. Display tasks with status 'Done'\n" +
                    "5. Display task with longest duration\n" +
                    "6. Search for a task by name\n" +
                    "7. Search for tasks by developer\n" +
                    "8. Delete a task by name"
            );

            switch (menuOption) {
                case "1":
                    int totalTasks = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of tasks you wish to enter:"));
                    tasks = new Task[totalTasks];
                    developers = new String[totalTasks];
                    taskNames = new String[totalTasks];
                    taskIDs = new String[totalTasks];
                    taskDurations = new int[totalTasks];
                    taskStatuses = new String[totalTasks];

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
                        developers[i] = firstName + " " + lastName;
                        taskNames[i] = taskName;
                        taskIDs[i] = task.createTaskID();
                        taskDurations[i] = duration;
                        taskStatuses[i] = status;

                        JOptionPane.showMessageDialog(null, "Task successfully captured");
                    }
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, "Coming soon");
                    break;
                case "3":
                    exit = true;
                    break;
                case "4":
                    StringBuilder doneTasks = new StringBuilder("Tasks with status 'Done':\n");
                    for (int i = 0; i < tasks.length; i++) {
                        if ("Done".equals(taskStatuses[i])) {
                            doneTasks.append("Developer: ").append(developers[i])
                                    .append(", Task Name: ").append(taskNames[i])
                                    .append(", Task Duration: ").append(taskDurations[i]).append(" hours\n");
                        }
                    }
                    JOptionPane.showMessageDialog(null, doneTasks.toString());
                    break;
                case "5":
                    int maxDuration = 0;
                    String longestTaskDeveloper = "";
                    for (int i = 0; i < tasks.length; i++) {
                        if (taskDurations[i] > maxDuration) {
                            maxDuration = taskDurations[i];
                            longestTaskDeveloper = developers[i];
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Developer: " + longestTaskDeveloper + ", Duration: " + maxDuration + " hours");
                    break;
                case "6":
                    String searchTaskName = JOptionPane.showInputDialog("Enter the task name to search:");
                    StringBuilder taskSearchResult = new StringBuilder("Search result:\n");
                    for (int i = 0; i < tasks.length; i++) {
                        if (taskNames[i].equalsIgnoreCase(searchTaskName)) {
                            taskSearchResult.append("Task Name: ").append(taskNames[i])
                                    .append(", Developer: ").append(developers[i])
                                    .append(", Task Status: ").append(taskStatuses[i]).append("\n");
                        }
                    }
                    JOptionPane.showMessageDialog(null, taskSearchResult.toString());
                    break;
                case "7":
                    String searchDeveloper = JOptionPane.showInputDialog("Enter the developer's name to search:");
                    StringBuilder developerSearchResult = new StringBuilder("Tasks assigned to developer:\n");
                    for (int i = 0; i < tasks.length; i++) {
                        if (developers[i].equalsIgnoreCase(searchDeveloper)) {
                            developerSearchResult.append("Task Name: ").append(taskNames[i])
                                    .append(", Task Status: ").append(taskStatuses[i]).append("\n");
                        }
                    }
                    JOptionPane.showMessageDialog(null, developerSearchResult.toString());
                    break;
                case "8":
                    String deleteTaskName = JOptionPane.showInputDialog("Enter the task name to delete:");
                    boolean taskDeleted = false;
                    Task[] updatedTasks = new Task[tasks.length - 1];
                    String[] updatedDevelopers = new String[tasks.length - 1];
                    String[] updatedTaskNames = new String[tasks.length - 1];
                    String[] updatedTaskIDs = new String[tasks.length - 1];
                    int[] updatedTaskDurations = new int[tasks.length - 1];
                    String[] updatedTaskStatuses = new String[tasks.length - 1];
                    int index = 0;
                    for (int i = 0; i < tasks.length; i++) {
                        if (taskNames[i].equalsIgnoreCase(deleteTaskName)) {
                            taskDeleted = true;
                        } else {
                            updatedTasks[index] = tasks[i];
                            updatedDevelopers[index] = developers[i];
                            updatedTaskNames[index] = taskNames[i];
                            updatedTaskIDs[index] = taskIDs[i];
                            updatedTaskDurations[index] = taskDurations[i];
                            updatedTaskStatuses[index] = taskStatuses[i];
                            index++;
                        }
                    }
                    if (taskDeleted) {
                        tasks = updatedTasks;
                        developers = updatedDevelopers;
                        taskNames = updatedTaskNames;
                        taskIDs = updatedTaskIDs;
                        taskDurations = updatedTaskDurations;
                        taskStatuses = updatedTaskStatuses;
                        JOptionPane.showMessageDialog(null, "Task deleted successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Task not found.");
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please select a valid menu option.");
            }
        }

        
        if (tasks != null) {
            StringBuilder tasksDetails = new StringBuilder("Tasks:\n");
            for (Task task : tasks) {
                tasksDetails.append(task.printTaskDetails()).append("\n");
            }

            JOptionPane.showMessageDialog(null, tasksDetails.toString());

           
            int totalHours = Task.returnTotalHours(tasks);
            JOptionPane.showMessageDialog(null, "Total combined hours of all tasks: " + totalHours + " hours");
        }
    }
}
