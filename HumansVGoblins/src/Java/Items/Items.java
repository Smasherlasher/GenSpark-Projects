package Items;

import java.util.Arrays;

public class Items {
    private String name;
    private int uId;
    private int[] stats;

    @Override
    public String toString() {
        return "Items{" +
                "name='" + name + '\'' +
                ", uId=" + uId +
                ", stats=" + Arrays.toString(stats) +
                '}';
    }

    public Items(){
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setuId(int uId) {
        this.uId = uId;
    }
    public void setStats(int[] stats) {
        this.stats = stats;
    }
    public int[] getStats() {
        return stats;
    }
    public int getuId() {
        return uId;
    }
    public String getName() {
        return name;
    }
    //method that returns and item based on the items UID
    public Items getItem(int Uid,int difficulty){
        if(Uid==1){
            return new AgilityPotion(difficulty);
        }else if(Uid==2){
            return new Boots(difficulty);
        }else if(Uid==3){
            return new ChestPiece(difficulty);
        }else if(Uid==4){
            return new Dagger(difficulty);
        }else if(Uid==5){
            return new Gloves(difficulty);
        }else if(Uid==6){
            return new Greaves(difficulty);
        }else if(Uid==7){
            return new HealthPotion(difficulty);
        }else if(Uid==8){
            return new Helmet(difficulty);
        }else if(Uid==9){
            return new IntelligencePotion(difficulty);
        }else if(Uid==10){
            return new ManaPotion(difficulty);
        }else if(Uid==11){
            return new Staff(difficulty);
        }else if(Uid==12){
            return new StrengthPotion(difficulty);
        }else if(Uid==13){
            return new Sword(difficulty);
        }
        return null;
    }
}
