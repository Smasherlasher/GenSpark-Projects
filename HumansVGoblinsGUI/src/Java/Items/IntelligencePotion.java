package Items;

public class IntelligencePotion extends Consumables{
    public IntelligencePotion(int difficulty) {
        setName("Intelligence Potion V" + difficulty);
        setuId(9);
        setTime(10 + difficulty);
        setEffect(5 + difficulty);

    }
}
