import Items.*;
import main.Human;
import main.Land;
import main.World;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {
    World world;

    Human human;
    private final int dimension = 10;
    private int difficulty = 10;

    @BeforeEach
    void setUp() {
        int rng = (int) (Math.random() * 2);
        Weapon weapon;
        if(rng == 0) {
            weapon = new Dagger(difficulty);
        }else if(rng == 1){
            weapon = new Staff(difficulty);
        }else{
            weapon = new Sword(difficulty);
        }
        human = new Human(1, 5, 5, 5, "Ian", new int[]{(int) (Math.random()*(1-1)), (int) (Math.random()*(1-1))}
                , new Helmet(difficulty), new Gloves(difficulty), new ChestPiece(difficulty), new Greaves(difficulty), new Boots(difficulty),weapon);
        world = new World(dimension, dimension, difficulty);
    }

    @Test
    void fillWorld() {
        world.fillWorld(dimension,dimension,difficulty);
        Land[][] land = new Land[dimension][dimension];
        land[0][0] = new Land("#",dimension,dimension,difficulty);
        assertEquals(land[0][0].getId(),world.getMap()[0][0].getId());
    }

    @Test
    void openDoor() {
        boolean open = world.isOpenDoor();
        world.openDoor(1,3);
        assertTrue(world.isOpenDoor());
    }

    @Test
    void replace() {
        world.replace(1,5,"^");
        assertEquals("^",world.getMap()[1][5].getId());
    }

    @Test
    void checkOccupancy() {
        world.fillWorld(1,1,difficulty);
        Land[][] land = new Land[1][1];
        land[0][0] = new Land("#",1,1,difficulty);
        land[0][0].setHasChest(true);
        world.setMap(land);
        assertTrue(world.checkOccupancy(human));
    }

    @AfterEach
    void tearDown() {
    }

}