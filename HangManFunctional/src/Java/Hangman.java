import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Hangman {
    public void main(String[] args) {
        playgame();
    }
    //game method
    public void playgame() {
        String file = "C:\\GenSpark\\HangManFunctional\\src\\Java\\Hangman Words.txt";
        String highScore = "C:\\GenSpark\\HangManFunctional\\src\\Java\\High Score.txt";
        Map<String, String> leaderboard = new HashMap<>();
        ArrayList<String> word = setWords(file);
        String usedLetters = "";
        Scanner sc = new Scanner(System.in);
        int x = 0;
        int len = word.get(0).length();
        List<String> list = word.stream().map(n->n.split("")).flatMap(Arrays::stream).collect(Collectors.toList());
        //main game loop
        while (x < 6 && !(word.get(0).compareTo(list.subList(len, len * 2).stream().reduce("", (partialString, element) ->partialString + element))==0)) {
            System.out.println("Your guess?");
            String userInput = gui(x,len,usedLetters,list);
            if(word.get(0).contains(userInput)){
                List<Integer> indexes = getIndexes(word,userInput);
                indexes.stream().forEach(n->Collections.swap(list,n, n+len));
            }else if (word.get(0).equals(userInput)){
                System.out.println("Congrats on guessing " + userInput);
            }else {
                System.out.println("Guessed wrong");
                usedLetters+=userInput;
                x++;
            }
        }
        System.out.println("Enter your name.");
        String name = gui(x,len,usedLetters,list);
        leaderboard.put(name,String.valueOf(x));
        if(epilogue(highScore,leaderboard)){
            System.out.println("congrats on getting a new high score");
        }else{
            System.out.println("Sorry you didn't get a new high score");
        }
    }
    //method that displays the gallows artwork returns asking for the next user input
    public String gui(int x,int len,String usedLetters,List<String> list){
        Scanner sc = new Scanner(System.in);
        String pic = "C:\\GenSpark\\HangManFunctional\\src\\Java\\Hangman Art.txt";

        ArrayList<String> art = getPic(pic,x);
        art.stream().forEach(System.out::println);
        list.subList(len, len * 2).stream().forEach(System.out::print);

        System.out.println();
        System.out.println("Used letters: " + usedLetters);
        return sc.nextLine();
    }
    //method that determines where all the indexs of a given user input
    public List<Integer> getIndexes(ArrayList<String> word,String userInput){
        return IntStream
                .iterate(word.get(0).indexOf(userInput), index -> index >= 0, index -> word.get(0).indexOf(userInput, index + 1))
                .boxed()
                .collect(Collectors.toList());
    }
    //method that checks if a file is made,writes to the file, and then checks if the current run was a new high score
    public boolean epilogue(String file,Map<String, String> leaderboard){
        String writeToFile = leaderboard.keySet()+ "," + leaderboard.values();
        String current = String.valueOf(leaderboard.values());
        ArrayList<String[]> list = new ArrayList<>();
        leaderboard.clear();
        try {
            if(Files.exists(Path.of(file))){
                Files.write(Paths.get(file), Collections.singleton(writeToFile), StandardOpenOption.APPEND);
            }else {
                Files.write(Path.of(file), Collections.singleton(writeToFile));
            }
            Files.lines(Path.of(file)).forEach(n->list.add(n.split(",")));
            list.stream().forEach(n->leaderboard.put(n[0], n[1]));
            if(current.compareTo(leaderboard.values().stream().min(String::compareTo).get())<=0 ){
                return true;
            }else{
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
//method that reads a file and returns an image in an arraylist
    public ArrayList<String> getPic(String file, int x){
        try {
            Object[] pics = Files.lines(Path.of(file)).toArray();
            ArrayList<String> arrayPics = new ArrayList<>();
            List<Integer> indexes = new ArrayList<>();
            indexes.add(0);indexes.add(1);indexes.add(2);indexes.add(3);indexes.add(4);
            indexes.stream().forEach(n->arrayPics.add((String) pics[n+(x)*5]));
            return arrayPics;
        }catch (IOException e) {
        e.printStackTrace();
    }
        return null;
    }
    //method that reads a file of words and randomly pics a single word and returns an arrylist of that word and blank word with the same length of the visble word
    public ArrayList<String> setWords(String file) {
        try {
            String words = Files.readString(Path.of(file));
            ArrayList<String> arrayWords = new ArrayList<>();
            if (words != null) {
                String[] list = words.split("\\s+");
                int rng = (int) (Math.random() * list.length);
                String word = Arrays.toString(Arrays.stream(list).filter(n -> n.compareTo(list[rng]) == 0).toArray());
                word = word.replaceAll("\\[", "");
                word = word.replaceAll("\\]", "");
                arrayWords.add(word);
                String[] hiddenWord = word.split("");
                List<String> hidden = Arrays.stream(hiddenWord).map(n -> n.replace(n, "_")).collect(Collectors.toList());
                arrayWords.add(String.join("",hidden));
                return arrayWords;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
