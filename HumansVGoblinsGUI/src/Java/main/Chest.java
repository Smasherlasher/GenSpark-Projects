package main;

import Items.Items;

public class Chest{
    private Items item;
    private String uId;

    public Chest(int rng,int difficulty){
          setItem(new Items().getItem(rng,difficulty));
          setuId("*");
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public void setItem(Items item) {
        this.item = item;
    }
    public Items getItem() {
        return item;
    }

    public String getuId() {
        return uId;
    }

    @Override
    public String toString() {
        return "main.Chest{" +
                "item=" + item +
                ", uId='" + uId + '\'' +
                '}';
    }
}
