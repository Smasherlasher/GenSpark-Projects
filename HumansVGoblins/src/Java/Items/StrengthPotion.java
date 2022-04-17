package Items;

public class StrengthPotion extends Consumables{
    public StrengthPotion(int difficulty) {
        setName("Strength Potion V" + difficulty);
        setuId(12);
        setTime(5 + difficulty);
        setEffect(1 + difficulty);

    }
}
