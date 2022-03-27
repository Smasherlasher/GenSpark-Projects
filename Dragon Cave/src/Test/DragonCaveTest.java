import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import java.io.ByteArrayInputStream;

class DragonCaveTest {
    DragonCave dragonCave;


    @BeforeEach
    void setUp() {
        dragonCave = new DragonCave();
    }
    @Test
    void setInput1() {
        String userInput = "1\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        assertDoesNotThrow(()->dragonCave.setInput());
    }
    @Test
    void setInput2(){
        String userInput = "2\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        assertDoesNotThrow(()->dragonCave.setInput());
    }
    @Test
    void setInput3(){
        String userInput = "3\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        assertDoesNotThrow(()->dragonCave.setInput());
    }
    @Test
    void setInput4(){
        String userInput = "Hello\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        assertDoesNotThrow(()->dragonCave.setInput());
    }
    @AfterEach
    void tearDown() {
    }
}