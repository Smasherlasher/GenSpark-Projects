import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.*;


class HangManTest {
    HangMan hangMan;

    @BeforeEach
    void setUp() {
        hangMan = new HangMan();

    }
    @Test
    void updateUsedLettersThrowsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class,()->hangMan.updateUsedLetters("1"));
    }

    @Test
    void setWordsDoesntThrow(){
        assertDoesNotThrow(()->hangMan.setWords("Hangman Words.txt"));
    }

    @Test
    void setWordNullPointerException(){
        assertThrows(NullPointerException.class,()-> hangMan.setWord(null));
    }
    @Test
    void revealWordThrowsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class,()->hangMan.revealWord("1"));
    }

    @Test
    void revealWord(){
        String[] str = new String[1];
        str[0] = "word";
        hangMan.updatehiddenWord("____");
        hangMan.setWord(str);
        assertEquals(hangMan.revealWord("o"),"_o__");
    }


    @AfterEach
    void tearDown() {
    }
}