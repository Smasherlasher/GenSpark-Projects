package Items;

import Items.Armor;

public class Boots extends Armor {
    public Boots(int difficulty) {
        setName("Boots V" + difficulty);
        setuId(2);
        setStats(new int[]{(int) (Math.random() * difficulty), (int) (Math.random() * difficulty),(int) (Math.random()*difficulty)});
    }
}
