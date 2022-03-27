import java.util.Scanner;

public class GuessTheNumber {
    private String input;
    private int ran;
    private boolean replay;

    //setters and getters methods
    public void setInput(){
        Scanner sc = new Scanner(System.in);
        this.input = sc.nextLine();
    }
    public void setRan(){
        int min = 1;
        int max = 20;
        this.ran = (int) (Math.random() * max) + min;
    }
    public void setReplay(){
        this.replay = true;
    }
    public void updateReplay(){
        this.replay = false;
    }
    public String getInput(){
        return input;
    }
    public int getRan(){
        return ran;
    }
    public boolean getReplay(){
        return replay;
    }

    public String response(int index,int num){
        // Response dialogue for the user.
        switch (index){
            case 1:
                return "Hello! What is your name?";
            case 2:
                return "Well, %s, I am thinking of a number between 1 and 20.\n Take a guess.";
            case 3:
                return "Your guess is too high.\n Take a guess.";
            case 4:
                return "Your guess is too low. \n Take a guess.";
            case 5:
                return "Good job, %s! You guessed my number in " + (num + 1) + " guesses! \n Would you like to play again? (y or n)";
            case 6:
                return "Sorry, %s! You couldn't guessed my number in 6 guesses! \n Would you like to play again? (y or n)";
            case 7:
                return "Sorry %s! Your number is over 20. Try again.";
            default:
                throw new IllegalStateException("Unexpected value: " + index);
        }
    }
    //method to initialize game
    private void init(){
        setInput();
        setReplay();
        setRan();
    }

    // method for the Guess the number game
    public void game(String name){
        // Main loop of the game.
        for(int x = 0;x<6;x++) {
            // Checks to see if this is the first time and sets the random number.
            if (x == 0) {
                System.out.printf((response(2, -1)) + "%n", name);
            }
            // User input to guess the random number.
            setInput();
            // Conditional statements to check if the number is higher, lower, or exactly the same.
            if (Integer.parseInt(getInput()) > getRan() && Integer.parseInt(getInput()) <= 20) {
                System.out.println(response(3,-1));
            } else if (Integer.parseInt(getInput()) < getRan()) {
                System.out.println(response(4,-1));
            } else if(Integer.parseInt(getInput()) > 20){
                System.out.printf((response(7, -1)) + "%n", name);
            }
            else {
                // User input to see if the game should be continued or not.
                System.out.printf((response(5, x)) + "%n", name);
                setInput();
                if (getInput().compareTo("n") == 0) {
                    updateReplay();
                }else{
                    break;
                }
            }
            // Checks to see if the user uses all of their tries and either resets or ends.
            if(x==5) {
                System.out.printf((response(6, -1)) + "%n", name);
                setInput();
                if (getInput().compareTo("n") == 0) {
                    updateReplay();
                }
            }
        }
    }


    public void main(String[] args) {
        // User input
        System.out.println(response(1,-1));
        try {
            init();
            // While loop for the game to keep going
            while (getReplay()) {
                game(getInput());
            }
        }catch(Exception e){System.out.println(e);}

    }
}
