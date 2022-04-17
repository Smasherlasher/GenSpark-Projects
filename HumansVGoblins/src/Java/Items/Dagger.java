package Items;

public class Dagger extends Weapon{
    public Dagger(int difficulty){
        setName("Dagger V" + difficulty);
        setuId(4);
        setStats(new int[]{0, 0,(int) (Math.random()*difficulty)});

    }

}
