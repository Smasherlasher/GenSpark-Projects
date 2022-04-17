package Items;

public class ManaPotion extends Consumables{
    public ManaPotion(int difficulty){
        setName("Mana Potion V" + difficulty);
        setEffect(5 + difficulty*2);
        setuId(10);
    }
}
