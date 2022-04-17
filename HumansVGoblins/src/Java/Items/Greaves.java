package Items;

import Items.Armor;

public class Greaves extends Armor {

    public Greaves(int difficulty) {
        setName("Greaves V" + difficulty);
        setuId(6);
        setStats(new int[]{(int) (Math.random() * difficulty), (int) (Math.random() * difficulty),(int) (Math.random()*difficulty)});

    }
}
