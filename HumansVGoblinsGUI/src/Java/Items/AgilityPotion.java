package Items;

public class AgilityPotion extends Consumables{
    public AgilityPotion(int difficulty){
        setName("Agility Potion V"  + difficulty);
        setEffect(1 + difficulty);
        setuId(1);
        setTime(5 + difficulty);
    }
}
