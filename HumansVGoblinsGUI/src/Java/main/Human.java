package main;

import Items.*;
import java.util.ArrayList;

public class Human extends Humanoid {
    private ArrayList<Items> inventory;
    private Buff buff;
    public Human(int level, int strength, int intelligence, int agility, String name,
                 int[] location, Helmet head, Gloves hands, ChestPiece chest, Greaves legs, Boots feet, Weapon weapon){
        setId("@");
        setLevel(level);
        setMaxHealth(100+(int)(strength*Math.log(strength)));
        setMaxMana(100+(int)(intelligence*Math.log(intelligence)));
        setHealth(100+(int)(strength*Math.log(strength)));
        setMana(100+(int)(intelligence*Math.log(intelligence)));
        setIntelligence(intelligence);
        setAgility(agility);
        setStrength(strength);
        setName(name);
        setSpeed(1+(float)(Math.log(agility)));
        setLocation(location);
        initArmor(head);
        initArmor(hands);
        initArmor(chest);
        initArmor(legs);
        initArmor(feet);
        setWeapon(weapon);
        setInventory(new ArrayList<>());
        setBuff(new Buff());

    }
    //initial armor equip
    public void initArmor(Armor equipable){
        int Uid = equipable.getuId();
        if (Uid == 2) {
            setFeet((Boots) equipable);
        } else if (Uid == 3) {
            setChest((ChestPiece) equipable);
        } else if (Uid == 5) {
            setHands((Gloves) equipable);
        } else if (Uid == 6) {
            setLegs((Greaves) equipable);
        } else if (Uid == 8) {
            setHead((Helmet) equipable);
        }
        setStrength(getStrength()+equipable.getStats()[0]);
        setIntelligence(getIntelligence()+equipable.getStats()[1]);
        setAgility(getAgility()+equipable.getStats()[2]);
        setMaxHealth(100+(int)(getStrength()*Math.log(getStrength())));
        setMaxMana(100+(int)(getIntelligence()*Math.log(getIntelligence())));
        setSpeed(1+(float)(Math.log(getAgility())));
    }
    //method to add an item to the inventory
    public void insertInventory(Items item){
        getInventory().add(item);
    }
    //method that first unequips the armour then equips the new armor piece
    public void equip(Armor equipable) {
        int Uid = equipable.getuId();
        if (Uid == 2) {
            setStrength(getStrength()-getFeet().getStats()[0]);
            setIntelligence(getIntelligence()-getFeet().getStats()[1]);
            setAgility(getAgility()-getFeet().getStats()[2]);
            setFeet((Boots) equipable);
        } else if (Uid == 3) {
            setStrength(getStrength()-getChest().getStats()[0]);
            setIntelligence(getIntelligence()-getChest().getStats()[1]);
            setAgility(getAgility()-getChest().getStats()[2]);
            setChest((ChestPiece) equipable);
        } else if (Uid == 5) {
            setStrength(getStrength()-getHands().getStats()[0]);
            setIntelligence(getIntelligence()-getHands().getStats()[1]);
            setAgility(getAgility()-getHands().getStats()[2]);
            setHands((Gloves) equipable);
        } else if (Uid == 6) {
            setStrength(getStrength()-getLegs().getStats()[0]);
            setIntelligence(getIntelligence()-getLegs().getStats()[1]);
            setAgility(getAgility()-getLegs().getStats()[2]);
            setLegs((Greaves) equipable);
        } else if (Uid == 8) {
            setStrength(getStrength()-getHead().getStats()[0]);
            setIntelligence(getIntelligence()-getHead().getStats()[1]);
            setAgility(getAgility()-getHead().getStats()[2]);
            setHead((Helmet) equipable);
        }
        setStrength(getStrength()+equipable.getStats()[0]);
        setIntelligence(getIntelligence()+equipable.getStats()[1]);
        setAgility(getAgility()+equipable.getStats()[2]);
        setMaxHealth(100+(int)(getStrength()*Math.log(getStrength())));
        setMaxMana(100+(int)(getIntelligence()*Math.log(getIntelligence())));
        setHealth(getMaxHealth());
        setMana(getMaxMana());
        setSpeed(1+(float)(Math.log(getAgility())));

    }
    //method that equips weapons
    public void equipWeapon(Weapon weapon) {
        setStrength(getStrength() - getWeapon().getStats()[0]);
        setIntelligence(getIntelligence() - getWeapon().getStats()[1]);
        setAgility(getAgility() - getWeapon().getStats()[2]);
        setWeapon(weapon);

        setStrength(getStrength()+weapon.getStats()[0]);
        setIntelligence(getIntelligence()+weapon.getStats()[1]);
        setAgility(getAgility()+weapon.getStats()[2]);

        setMaxHealth(100+(int)(getStrength()*Math.log(getStrength())));
        setMaxMana(100+(int)(getIntelligence()*Math.log(getIntelligence())));
        setHealth(getMaxHealth());
        setMana(getMaxMana());
        setSpeed(1+(float)(Math.log(getAgility())));
    }
    //method that removes the stat buff after a certain turn limit
    public void removeBuff(Consumables consumable) {
        if (consumable.getuId() == 1) {
            setAgility(getAgility() - consumable.getEffect());
            System.out.println("Your agility is now " + getAgility());
        } else if (consumable.getuId() == 9) {
            setIntelligence(getIntelligence() - consumable.getEffect());
            System.out.println("Your intelligence is now " + getIntelligence());
        } else if (consumable.getuId() == 12) {
            setStrength(getStrength() - consumable.getEffect());
            System.out.println("Your strength is now " + getStrength());
        }
        buff.setActive(false);
        setMaxHealth(100 + (int) (getStrength() * Math.log(getStrength())));
        setMaxMana(100 + (int) (getIntelligence() * Math.log(getIntelligence())));
        setSpeed(1 + (float) (Math.log(getAgility())));
    }
    //method that lets the player drink a potion with different effects
    public void use(Consumables consumable) {
        buff.setBuffId(consumable);
        buff.setBuffTime(consumable.getTime());
        buff.setActiveBuff(consumable.getName());
        buff.setActive(true);
        if(consumable.getuId() == 1){
            setAgility(getAgility()+consumable.getEffect());
        }else if (consumable.getuId() == 7) {
            if (getHealth() + consumable.getEffect() > getMaxHealth()) {
                setHealth(getMaxHealth());
            } else {
                setHealth(getHealth() + consumable.getEffect());
            }
        }else if(consumable.getuId() == 9){
            setIntelligence(getIntelligence()+consumable.getEffect());
        }else if (consumable.getuId() == 10) {
            if (getMana() + consumable.getEffect() > getMaxMana()) {
                setMana(getMaxMana());
            } else {
                setMana(getMana() + consumable.getEffect());
            }
        }else if(consumable.getuId() == 12){
            setStrength(getStrength()+consumable.getEffect());
        }

        setMaxHealth(100+(int)(getStrength()*Math.log(getStrength())));
        setMaxMana(100+(int)(getIntelligence()*Math.log(getIntelligence())));
        setSpeed(1+(float)(Math.log(getAgility())));
    }
    public void setInventory(ArrayList<Items> inventory) {
        this.inventory = inventory;
    }

    public void setBuff(Buff buff) {
        this.buff = buff;
    }

    public Buff getBuff() {
        return buff;
    }

    public ArrayList<Items> getInventory() {
        return inventory;
    }

    @Override
    public String toString() {
        return "main.Human{" +
                "inventory=" + inventory +
                ", buff=" + buff +
                '}';
    }
}
