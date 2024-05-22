/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progpoepart2;

/**
 *
 * @author RC_Student_lab
 */
public class Task {
    private String taskName;
    private String description;
    private String firstName;
    private String lastName;
    private int duration;
    private int taskNumber;
    private String status;
    private static int taskCounter = 0;

    public Task(String taskName, String description, String firstName, String lastName, int duration, String status) {
        this.taskName = taskName;
        this.description = description;
        this.firstName = firstName;
        this.lastName = lastName;
        this.duration = duration;
        this.status = status;
        this.taskNumber = taskCounter++;
    }

    public boolean checkTaskDescription() {
        return description.length() <= 50;
    }

    public String createTaskID() {
        String firstTwoLetters = taskName.substring(0, 2).toUpperCase();
        String lastThreeLetters = lastName.substring(Math.max(lastName.length() - 3, 0)).toUpperCase();
        return firstTwoLetters + ";" + taskNumber + ";" + lastThreeLetters;
    }

    public String printTaskDetails() {
        return "Task ID: " + createTaskID() + "\n" +
                "Task Name: " + taskName + "\n" +
                "Task Description: " + description + "\n" +
                "Developer: " + firstName + " " + lastName + "\n" +
                "Task Duration: " + duration + " hours\n" +
                "Task Status: " + status + "\n";
    }

    public static int returnTotalHours(Task[] tasks) {
        int totalHours = 0;
        for (Task task : tasks) {
            totalHours += task.getDuration();
        }
        return totalHours;
    }

    public int getDuration() {
        return duration;
    }
}
