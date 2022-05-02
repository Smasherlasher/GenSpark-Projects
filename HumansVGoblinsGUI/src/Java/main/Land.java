package main;

import main.Chest;

public class Land{

    private Chest chest;
    private boolean hasChest;
    private String id;
    private int x;
    private int y;

    public Land(String id, int x, int y, int difficulty){
        //rng to see if this specific land tile has a chest or not. 5% chance
        int rng = (int) (Math.random() * 260)+1;
        if (rng<13) {
            setChest(new Chest(rng,difficulty));
            setId(getChest().getuId());
            setHasChest(true);
        }else {
            setId(id);
            setHasChest(false);
        }
        setX(x);
        setY(y);
    }

    public Chest getChest() {
        return chest;
    }
    public String getId() {
        return id;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public boolean isHasChest() {
        return hasChest;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public void setHasChest(boolean hasChest) {
        this.hasChest = hasChest;
    }

    public void setChest(Chest chest) {
        this.chest = chest;
    }

    @Override
    public String toString() {
        return "main.Land{" +
                "chest=" + chest +
                ", hasChest=" + hasChest +
                ", id='" + id + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
