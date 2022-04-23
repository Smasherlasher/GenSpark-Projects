import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HangmanTest {
    Hangman hangman;
    @BeforeEach
    void setUp() {
        hangman = new Hangman();
    }
    @Test
    void getIndexes() {
        ArrayList<String> word = new ArrayList<>();
        word.add("cats");word.add("____");
        List<Integer> index = hangman.getIndexes(word,"c");
        assertEquals(0,index.get(0));
    }
    @Test
    void getIndexesMoreThanOne() {
        ArrayList<String> word = new ArrayList<>();
        word.add("paper");word.add("_____");
        List<Integer> index = hangman.getIndexes(word,"p");
        assertTrue(index.get(0) == 0 && index.get(1) == 2);
    }
    @Test
    void epilogueTrue() {
        String file = "C:\\GenSpark\\HangManFunctional\\src\\Java\\High Score.txt";
        Map<String, String> leaderboard = new HashMap<> ();
        leaderboard.put("Charles","2");
        assertTrue(hangman.epilogue(file,leaderboard));
    }
    @Test
    void epilogueFalse() {
        String file = "C:\\GenSpark\\HangManFunctional\\src\\Java\\High Score.txt";
        Map<String, String> leaderboard = new HashMap<> ();
        leaderboard.put("harry","4");
        assertFalse(hangman.epilogue(file, leaderboard));
    }
    @Test
    void getPic() {
        String file = "C:\\GenSpark\\HangManFunctional\\src\\Java\\Hangman Art.txt";
        ArrayList<String> list = new ArrayList<>();
        list.add("    +---+");
        list.add("        |");
        list.add("        |");
        list.add("        |");
        list.add("        ===");

        assertEquals(list,hangman.getPic(file,0));
    }

    @Test
    void setWordsReturnsAWord() {
        String file = "C:\\GenSpark\\HangManFunctional\\src\\Java\\Hangman Words.txt";
        assertNotNull(hangman.setWords(file));
    }

    @AfterEach
    void tearDown() {
    }
}