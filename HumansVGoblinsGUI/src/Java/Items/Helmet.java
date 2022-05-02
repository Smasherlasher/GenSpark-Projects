package Items;

import Items.Armor;

public class Helmet extends Armor {

    public Helmet(int difficulty) {
        setName("Helmet V" + difficulty);
        setuId(8);
        setStats(new int[]{(int) (Math.random() * difficulty), (int) (Math.random() * difficulty),(int) (Math.random()*difficulty)});

    }
}
