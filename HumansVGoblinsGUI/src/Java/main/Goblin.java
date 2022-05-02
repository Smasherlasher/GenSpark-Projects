package main;

import Items.Items;

public class Goblin extends Humanoid {


    public Goblin(int level, int strength, int intelligence, int agility, String name,
                  int[] location){
        setLevel(level);
        setMaxHealth(100+(int)(strength*Math.log(strength)));
        setMaxMana(100+(int)(intelligence*Math.log(intelligence)));
        setHealth(100+(int)(strength*Math.log(strength)));
        setMana(0);
        setIntelligence(intelligence);
        setAgility(agility);
        setStrength(strength);
        setName(name);
        setSpeed(1+(float)(Math.log(agility)));
        setId("&");
        setLocation(location);
    }
    //method fro the goblin to have a 50% chance to drop an item
    public Items drop(int difficulty){
        int rng = (int) (Math.random() * 26);
        return new Items().getItem(rng,difficulty);
    }
    //method for the goblin to find the location of the player from the last turn and start to follow him
    public String follow(int[] to,int[] from){
        int diffX = to[1]-from[1];
        int diffY = to[0]-from[0];
        if(diffX>0 && diffY>0){
            if(diffX>diffY){
                return "E";
            }else{
                return "S";
            }
        }else if(diffX<0 && diffY<0){
            if(diffX>diffY){
                return "W";
            }else{
                return "N";
            }
        }else if(diffY>0){
            return "E";
        }else if(diffX<0){
            return "N";
        }else if(diffY<0){
            return "W";
        }else{
            return "S";
        }
    }
    @Override
    public String toString() {
        return getName();
    }
}
