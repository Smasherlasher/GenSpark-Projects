package Items;

public class Consumables extends Items {
    private int effect;
    private int time;

    public void setEffect(int effect) {
        this.effect = effect;
    }
    public void setTime(int time) {
        this.time = time;
    }
    public int getEffect() {
        return effect;
    }
    public int getTime() {
        return time;
    }


    @Override
    public String toString() {
        return "Consumables{" +
                "effect=" + effect +
                ", time=" + time +
                '}';
    }
}
