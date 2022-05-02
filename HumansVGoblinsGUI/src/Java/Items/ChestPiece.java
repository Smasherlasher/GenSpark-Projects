package Items;

import Items.Armor;

public class ChestPiece extends Armor {
    public ChestPiece(int difficulty) {
        setName("Chest Piece V" + difficulty);
        setuId(3);
        setStats(new int[]{(int) (Math.random() * difficulty), (int) (Math.random() * difficulty),(int) (Math.random()*difficulty)});

    }
}
