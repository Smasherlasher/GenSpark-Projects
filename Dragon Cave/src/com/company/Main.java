package com.company;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        // Initial story that gives you two options to choose from.
        String option = "You are in the land full of dragons. \n" +
                "In front of you, you see two caves. \n" +
                "In one cave, the dragon is friendly and share his treasure with you. \n" +
                "The other dragon is greedy and hungry and will eat you on sight. Which cave will you go into? (1 or 2)\n";
        // Results from initial story choice
        String result1 ="You approach the cave... \n" +
                "It is dark and spooky... \n" +
                "A large dragon jumps out in front of you! He opens his jaws and... \n" +
                "Gobbles you down in one bite!\n";
        String result2 ="You approach the cave... \n" +
                "It is dark and spooky... \n" +
                "A large dragon jumps out in front of you! He opens his jaws and... \n" +
                "Hands over some of his treasure!\n";
        // User input
        System.out.println(option);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        // Print command for the story choice
            if (input.compareTo("1") == 0){
                System.out.println(result1);
            }else{
                System.out.println(result2);
            }

    }
}
