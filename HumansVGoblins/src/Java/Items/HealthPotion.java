package Items;

import Items.Consumables;

public class HealthPotion extends Consumables {
    public HealthPotion(int difficulty){
        setName("Health Potion V" + difficulty);
        setEffect(5 + difficulty*2);
        setuId(7);
    }

}
