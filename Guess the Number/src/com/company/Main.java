package com.company;

import java.util.Scanner;

public class Main {
    // method for the Guess the number game
    public static boolean game(String input,Scanner sc){



        int min = 1;
        int max = 20;
        int ran = 0;
        // Main loop of the game.
        for(int x = 0;x<6;x++) {
            // The rest of the user dialogue needed.
            String dialogue2 = "Well, %s, I am thinking of a number between 1 and 20.\n Take a guess.";
            String dialogue3 = "Your guess is too high.\n Take a guess.";
            String dialogue4 = "Your guess is too low. \n Take a guess.";
            String dialogue5 = "Good job, %s! You guessed my number in " + (x + 1) + " guesses! \n Would you like to play again? (y or n)";
            String dialogue6 = "Sorry, %s! You couldn't guessed my number in 6 guesses! \n Would you like to play again? (y or n)";
            // Checks to see if this is the first time and sets the random number.
            if (x == 0) {
                System.out.println(String.format(dialogue2, input));
                ran = (int) (Math.random() * max) + min;
            }
            // User input to guess the random number.
            int num = sc.nextInt();
            sc.nextLine();
            // Conditional statements to check if the number is higher, lower, or exactly the same.
            if (num > ran) {
                System.out.println(dialogue3);
            } else if (num < ran) {
                System.out.println(dialogue4);
            } else {
                // User input to see if the game should be continued or not.
                System.out.println(String.format(dialogue5, input));
                String answer = sc.nextLine();
                if (answer.compareTo("n") == 0) {
                    return false;
                }else{
                    break;
                }
            }
            // Checks to see if the user uses all of their tries and either resets or ends.
            if(x==5) {
                System.out.println(String.format(dialogue6, input));
                String answer = sc.nextLine();
                if (answer.compareTo("n") == 0) {
                    return false;
                }
            }
        }

        return true;
    }


    public static void main(String[] args) {
        // User input
        boolean answer = true;
        String dialogue1 = "Hello! What is your name?";

        System.out.println(dialogue1);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        // While loop for the game to keep going
        while (answer) {
            answer = game(input,sc);
        }

    }
}
