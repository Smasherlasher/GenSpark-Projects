package main;

import Items.*;

public class Buff  {
    private int buffTime;
    private Consumables buffId;
    private String activeBuff;
    private boolean active;



    public void setBuffTime(int buffTime) {
        this.buffTime = buffTime;
    }

    public void setActiveBuff(String activeBuff) {
        this.activeBuff = activeBuff;
    }

    public void setBuffId(Consumables buffId) {
        this.buffId = buffId;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public Consumables getBuffId() {
        return buffId;
    }

    public String getActiveBuff() {
        return activeBuff;
    }

    public int getBuffTime() {
        return buffTime;
    }

    @Override
    public String toString() {
        return "main.Buff{" +
                "buffTime=" + buffTime +
                ", buffId=" + buffId +
                ", activeBuff='" + activeBuff + '\'' +
                ", active=" + active +
                '}';
    }
}


