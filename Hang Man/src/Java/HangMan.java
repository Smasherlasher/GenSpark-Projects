import java.util.Scanner;
import java.io.File;

public class HangMan {
    private static String[] words;
    private static String word;
    private static String hiddenWord;
    private static String title;
    private static String bar;
    private static String pole1;
    private static String pole2;
    private static String pole3;
    private static String base;
    private static String usedLetters;



    //updates the hangman visual
    public void updateHangMan(int update) {
        switch (update) {
            case 1:
                pole1 = "  o   |";
                break;
            case 2:
                pole2 = "  |   |";
                break;
            case 3:
                pole2 = " /|   |";
                break;
            case 4:
                pole2 = " /|\\  |";
                break;
            case 5:
                pole3 = " /    |";
                break;
            case 6:
                pole3 = " / \\  |";
                break;
            default:
                break;
        }
    }
    //updates the already used words
    public void updateUsedLetters(String update){
        if(update.matches("[a-z]+")) {
            usedLetters += update;
        }else{
            throw new IllegalArgumentException("Not a valid letter");
        }
    }


    //setters and getters
    public String getHiddenWord(){
        return hiddenWord;
    };
    public void getHangMan() {
        System.out.println(title);
        System.out.println(bar);
        System.out.println(pole1);
        System.out.println(pole2);
        System.out.println(pole3);
        System.out.println(base);
        System.out.println(usedLetters);
        System.out.println(hiddenWord);
        System.out.println("");
        System.out.println("Guess a letter");
    }
    public String[] getWords() {
        return words;
    }
    public String getWord() {
        return word;
    }
    public void setWords(String file) {
        try {
            File wordFile = new File(file);
            Scanner read = new Scanner(wordFile);
            int x = 0;
            words = new String[20];
            while (read.hasNext()) {
                words[x] = read.nextLine();
                x++;
            }
            read.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void setWord(String[] list) {
        word = list[(int) (Math.random() * (list.length))];
    }
    public void setHangMan() {
        int x = 0;
        title = "HANGMAN";
        bar = "  +---+";
        pole1 = "      |";
        pole2 = "      |";
        pole3 = "      |";
        base = "      ===";
        hiddenWord = "";
        usedLetters = "Used letters: ";

        while (x < getWord().length()) {
            hiddenWord += "_";
            x++;
        }
    }
    public void sethiddenWord(String newWord){
        hiddenWord = newWord;
    };


    //method that checks if the user input is the word and if its in the word it will show it to the user
    public String revealWord(String c) {
        if (c.matches("[a-z]+")) {
            char[] arr = getWord().toCharArray();
            int index = 0;
            String revealedWord = getHiddenWord();
            while (index < arr.length) {
                if (c.charAt(0) == getWord().charAt(index)) {
                    StringBuilder string = new StringBuilder(revealedWord);
                    revealedWord = String.valueOf(string.replace(index, index + 1, c));
                }
                index++;
            }
            return revealedWord;
        } else {
            throw new IllegalArgumentException("Not a valid letter");
        }
    }
    //maing game loop
    public boolean game() {
        try {
            int x = 1;
            initGame();
            getHangMan();
            Scanner sc = new Scanner(System.in);
            while (x <= 6) {
                String userInput = sc.nextLine();
                if(getWord().compareTo(getHiddenWord())==0 || userInput.compareTo(getWord()) == 0){
                    return epilogue(sc);
                }
                if(word.contains(userInput)){
                    sethiddenWord(revealWord(userInput));
                }else {
                    updateUsedLetters(userInput);
                    updateHangMan(x);
                    x++;
                }
                getHangMan();
            }
            return epilogue(sc);
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
    //method initializing the game with a .txt file
    public void initGame() {
        if (words == null) {
            String file = "C:\\GenSpark\\Hang Man\\src\\Java\\Hangman Words.txt";
            setWords(file);
            setWord(getWords());
        } else {
            setWord(getWords());
        }
        setHangMan();
    }
    // ending asking if you want to play again
    public boolean epilogue(Scanner sc){
        System.out.println("The word was " + getWord()+ ".");
        System.out.println("Play Again?(yes or no)");
        return sc.nextLine().compareTo("yes") == 0;
    }


    public void main(String[] arg) {
        while(game()){}
    }

}