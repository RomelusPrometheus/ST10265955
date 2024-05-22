/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progpoepart2;

/**
 *
 * @author RC_Student_lab
 */
public class Login {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean loggedIn;

    public Login() {
        this.username = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.loggedIn = false;
    }

    public String registerUser(String username, String password, String firstName, String lastName) {
        // Check if the username and password meet the required conditions
        if (checkUsername(username) && checkPasswordComplexity(password)) {
            this.username = username;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
            return "Username successfully captured.\nPassword successfully captured.";
        } else {
            return "Username is not formatted correctly, please ensure that your username contains an underscore and is no more than 5 characters long.\n" +
                    "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.";
        }
    }

    public boolean loginUser(String enteredUsername, String enteredPassword) {
        if (enteredUsername.equals(this.username) && enteredPassword.equals(this.password)) {
            loggedIn = true;
            return true;
        } else {
            return false;
        }
    }

    public String returnLoginStatus() {
        if (loggedIn) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    private boolean checkUsername(String username) {
        return username.length() <= 5 && username.contains("_");
    }

    private boolean checkPasswordComplexity(String password) {
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecialChar = false;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                hasCapital = true;
            } else if (Character.isDigit(ch)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                hasSpecialChar = true;
            }
        }

        return password.length() >= 8 && hasCapital && hasNumber && hasSpecialChar;
    }
}
