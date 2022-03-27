import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import java.io.ByteArrayInputStream;

class GuessTheNumberTest {
    GuessTheNumber guessTheNumber;
    @BeforeEach
    void setUp() {
        guessTheNumber = new GuessTheNumber();
    }

    @Test
    void game() {
    }
    @Test
    void setInput(){
        String userInput = "1\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        guessTheNumber.setInput();
        assertEquals("1",guessTheNumber.getInput());
    }
    @Test
    void setRan(){
        guessTheNumber.setRan();
        assertTrue(1 <= guessTheNumber.getRan() && guessTheNumber.getRan() <= 20);
    }

    @Test
    void setReplay(){
        guessTheNumber.setReplay();
        assertTrue(guessTheNumber.getReplay());
    }

    @Test
    void updateReplay(){
        guessTheNumber.updateReplay();
        assertFalse(guessTheNumber.getReplay());
    }

    @Test
    void response1() {
        assertEquals("Hello! What is your name?"
                ,guessTheNumber.response(1,-1));
    }

    @Test
    void response2() {
        assertEquals("Well, %s, I am thinking of a number between 1 and 20.\n Take a guess."
                ,guessTheNumber.response(2,-1));
    }

    @Test
    void response3() {
        assertEquals("Your guess is too high.\n Take a guess."
                ,guessTheNumber.response(3,-1));
    }

    @Test
    void response4() {
        assertEquals("Your guess is too low. \n Take a guess."
                ,guessTheNumber.response(4,-1));
    }

    @Test
    void response5() {
        assertEquals("Good job, %s! You guessed my number in " + (4 + 1) + " guesses! \n Would you like to play again? (y or n)"
                ,guessTheNumber.response(5,4));
    }

    @Test
    void response6() {
        assertEquals("Sorry, %s! You couldn't guessed my number in 6 guesses! \n Would you like to play again? (y or n)"
                ,guessTheNumber.response(6,-1));
    }

    @Test
    void response7() {
        assertEquals("Sorry %s! Your number is over 20. Try again."
                ,guessTheNumber.response(7,-1));
    }

    @Test
    void response8() {
        assertThrows(IllegalStateException.class,()->guessTheNumber.response(8,-1));

    }

    @AfterEach
    void tearDown() {
    }

}