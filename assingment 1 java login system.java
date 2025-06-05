/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package loginsystem;
 import java.util.Scanner;

/**
 *
 * @author user
 */
public class LoginSystem {
  private static final String CORRECT_USERNAME = "francis mukuna";
    private static final String CORRECT_PASSWORD = "BSE-05-0166/2024";
    private static final int MAX_ATTEMPTS = 3;
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner bse = new Scanner(System.in);
        int attempts = 0;
        boolean loggedIn = false;

        while (attempts < MAX_ATTEMPTS && !loggedIn) {
            System.out.println("Attempt " + (attempts + 1) + " of " + MAX_ATTEMPTS);
            System.out.print("Enter username: ");
            String username = bse.nextLine();

            System.out.print("Enter password (each character will be shown as *): ");
            StringBuilder password = new StringBuilder();
            String input = bse.nextLine();

            for (int i = 0; i < input.length(); i++) {
                System.out.print("*");
                password.append(input.charAt(i));
            }
            System.out.println();

            if (username.equals(CORRECT_USERNAME) && password.toString().equals(CORRECT_PASSWORD)) {
                System.out.println("Login successful! Welcome, " + username + ".");
                loggedIn = true;
            } else {
                attempts++;
                if (attempts < MAX_ATTEMPTS) {
                    System.out.println("Incorrect username or password. " +
                            (MAX_ATTEMPTS - attempts) + " attempts remaining.");
                } else {
                    System.out.println("Maximum attempts reached. Access denied.");
                }
            }
        }
        bse.close();
    }
}