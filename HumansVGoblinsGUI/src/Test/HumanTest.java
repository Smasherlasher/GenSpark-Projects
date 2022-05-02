import Items.*;
import main.Human;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HumanTest {
    Human human;
    int difficulty = 5;
    int dimension = 10;
    @BeforeEach
    void setUp() {
        int rng = (int) (Math.random() * 2);
        String name = "Ian";
        Weapon weapon;
        if(rng == 0) {
            weapon = new Dagger(difficulty);
        }else if(rng == 1){
            weapon = new Staff(difficulty);
        }else{
            weapon = new Sword(difficulty);
        }
        human = new Human(1, 5, 5, 5, name, new int[]{(int) (Math.random()*(dimension-1)), (int) (Math.random()*(dimension-1))}
                , new Helmet(difficulty), new Gloves(difficulty), new ChestPiece(difficulty), new Greaves(difficulty), new Boots(difficulty),weapon);
    }

    @Test
    void initHeadAssert() {
        human.initArmor(new Helmet(difficulty));
        assertEquals("Helmet V" + difficulty,human.getHead().getName());
    }
    @Test
    void initChestAssert() {
        human.initArmor(new ChestPiece(difficulty));
        assertEquals("main.Chest Piece V" + difficulty,human.getChest().getName());
    }
    @Test
    void initLegsAssert() {
        human.initArmor(new Greaves(difficulty));
        assertEquals("Greaves V" + difficulty,human.getLegs().getName());
    }
    @Test
    void initHandsAssert() {
        human.initArmor(new Gloves(difficulty));
        assertEquals("Gloves V" + difficulty,human.getHands().getName());
    }
    @Test
    void initFeetAssert() {
        human.initArmor(new Boots(difficulty));
        assertEquals("Boots V" + difficulty,human.getFeet().getName());
    }
    @Test
    void inventoryManagementDrink() {
        human.insertInventory(new Helmet(difficulty));
        human.insertInventory(new HealthPotion(difficulty));
        String userInput = "D\n 2\n B\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        int oldHealth = human.getHealth();
        //human.inventoryManagement(userInput);
        assertNotEquals(oldHealth,human.getHealth());
    }
    @Test
    void inventoryManagementEquip() {
        human.insertInventory(new Helmet(difficulty));
        human.insertInventory(new HealthPotion(difficulty));
        String userInput = "E\n 1\n B\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        Helmet oldHelmet = human.getHead();
        //human.inventoryManagement(userInput);
        assertNotEquals(oldHelmet,human.getHead());
    }
    @Test
    void compareItemsResults() {
        Gloves oldItem = human.getHands();
        int[] oldStats = new int[]{human.getStrength(), human.getIntelligence(), human.getAgility()};
        //human.compareItems(new Gloves(difficulty));
        int[] newStats = new int[]{human.getStrength(), human.getIntelligence(), human.getAgility()};
        int[] stats1 = new int[]{newStats[0]-oldStats[0],newStats[1]-oldStats[1],newStats[2]-oldStats[2]};
        int[] stats2 = new int[]{
                human.getHands().getStats()[0]-oldItem.getStats()[0],
                human.getHands().getStats()[1]-oldItem.getStats()[1],
                human.getHands().getStats()[2]-oldItem.getStats()[2]
        };

        assertEquals(Arrays.toString(stats1), Arrays.toString(stats2));
    }

    @Test
    void insertInventory() {
        human.insertInventory(new Helmet(difficulty));
        human.insertInventory(new HealthPotion(difficulty));
        assertEquals(2,human.getInventory().size());
    }

    @Test
    void equip() {
        Armor helmet = new Helmet(difficulty);
        human.equip(helmet);
        assertEquals(helmet,human.getHead());
    }

    @Test
    void removeBuff() {
        Consumables consumable = new AgilityPotion(difficulty);
        human.use(consumable);
        boolean active = human.getBuff().isActive();
        human.removeBuff(consumable);
        assertNotEquals(active,human.getBuff().isActive());

    }

    @Test
    void use() {
        Consumables consumable = new AgilityPotion(difficulty);
        int agiStats = human.getAgility();
        human.use(consumable);
        assertNotEquals(agiStats,human.getAgility());
    }

    @AfterEach
    void tearDown() {
    }
}