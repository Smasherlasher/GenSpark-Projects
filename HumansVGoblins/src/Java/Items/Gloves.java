package Items;

import Items.Armor;

public class Gloves extends Armor {

    public Gloves(int difficulty) {
        setName("Gloves V" + difficulty);
        setuId(5);
        setStats(new int[]{(int) (Math.random() * difficulty), (int) (Math.random() * difficulty),(int) (Math.random()*difficulty)});
    }
}
