package Items;

public class Staff extends Weapon{
    public Staff(int difficulty){
        setName("Staff V" + difficulty);
        setuId(11);
        setStats(new int[]{0, (int) (Math.random() * difficulty),0});

    }
}
