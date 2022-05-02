package Items;

public class Sword extends Weapon{
    public Sword(int difficulty){
        setName("Sword V" + difficulty);
        setuId(13);
        setStats(new int[]{(int) (Math.random() * difficulty), 0,0});
    }

}
