import Items.*;
//import Items.Consumables;
//import Items.Gloves;

import java.util.ArrayList;
import java.util.Scanner;

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
    //method for the inventory management
    public void inventoryManagement(String input){
        Scanner sc = new Scanner(System.in);
        label:
        //loop while the player doesnt back out
        while(!input.equals("B")){
            int count = 1;
            for (Items item : getInventory()) {
                System.out.println(count+". "+item.getName());
                count++;
            }
            System.out.println("What would you like to do?(H for help)");
            input = sc.nextLine();
            //choice between drinking a potion,or equipping a piece of armor
            switch (input) {
                case "H":
                    helpInventory();
                    break;
                case "D": {
                    System.out.println("What would you like to drink?");
                    int uid = sc.nextInt() - 1;
                    Items item = getInventory().remove(uid);
                    use((Consumables) item);
                    sc.nextLine();
                    break label;
                }
                case "E": {
                    System.out.println("What would you like to equip?");
                    int uid = sc.nextInt() - 1;
                    Items item = getInventory().remove(uid);
                    equip((Armor) item);
                    break label;
                }
            }
        }
    }
    //method that compares to similar pieces of armor after
    public void compareItems(Items item){
        int oldIntel = 0,newIntel = 0;
        int oldStr = 0,newStr = 0;
        int oldAgi = 0,newAgi = 0;

        switch (item.getuId()){
            case 2:
                oldStr = getFeet().getStats()[0];
                oldIntel = getFeet().getStats()[1];
                oldAgi = getFeet().getStats()[2];
                newStr = item.getStats()[0];
                newIntel = item.getStats()[1];
                newAgi = item.getStats()[2];
                break;
            case 3:
                oldStr = getChest().getStats()[0];
                oldIntel = getChest().getStats()[1];
                oldAgi = getChest().getStats()[2];
                newStr = item.getStats()[0];
                newIntel = item.getStats()[1];
                newAgi = item.getStats()[2];
                break;
            case 5:
                oldStr = getHands().getStats()[0];
                oldIntel = getHands().getStats()[1];
                oldAgi = getHands().getStats()[2];
                newStr = item.getStats()[0];
                newIntel = item.getStats()[1];
                newAgi = item.getStats()[2];
                break;
            case 6:
                oldStr = getLegs().getStats()[0];
                oldIntel = getLegs().getStats()[1];
                oldAgi = getLegs().getStats()[2];
                newStr = item.getStats()[0];
                newIntel = item.getStats()[1];
                newAgi = item.getStats()[2];
                break;
            case 8:
                oldStr = getHead().getStats()[0];
                oldIntel = getHead().getStats()[1];
                oldAgi = getHead().getStats()[2];
                newStr = item.getStats()[0];
                newIntel = item.getStats()[1];
                newAgi = item.getStats()[2];
                break;
            default:
                break;
        }
        System.out.format("%1$-14s %2$-10s %3$-1s %4$-1s %5$-1s\n","Stat","Current equipment","New equipment","-->","Resulting change");
        System.out.format("%1$-22s %2$-15s %3$-7s %4$-8s %5$-1s\n","Strength",oldStr,newStr,"-->",String.format("%5s",(newStr-oldStr)));
        System.out.format("%1$-22s %2$-15s %3$-7s %4$-8s %5$-1s\n","Intelligence",oldIntel,newIntel,"-->",String.format("%5s",(newIntel-oldIntel)));
        System.out.format("%1$-22s %2$-15s %3$-7s %4$-8s %5$-1s\n","Agility",oldAgi,newAgi,"-->",String.format("%5s",(newAgi-oldAgi)));
    }

    public void helpInventory(){
        System.out.println("I for Inventory");
        System.out.println("B to back out");
        System.out.println("D then the position of the item is in to drink");
        System.out.println("E then the position of the Armour you want to equip");
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
        if(consumable.getuId() == 1){
            setAgility(getAgility()+consumable.getEffect());
            System.out.println("Your agility is now " + getAgility() + " for " + buff.getBuffTime() + " turns");
        }else if (consumable.getuId() == 7) {
            if (getHealth() + consumable.getEffect() > getMaxHealth()) {
                setHealth(getMaxHealth());
            } else {
                setHealth(getHealth() + consumable.getEffect());
            }
            System.out.println("Your health is now " + getHealth() + "/" + getMaxHealth());
        }else if(consumable.getuId() == 9){
            setIntelligence(getIntelligence()+consumable.getEffect());
            System.out.println("Your intelligence is now " + getIntelligence() + " for " + buff.getBuffTime() + " turns");
        }else if (consumable.getuId() == 10) {
            if (getMana() + consumable.getEffect() > getMaxMana()) {
                setMana(getMaxMana());
            } else {
                setMana(getMana() + consumable.getEffect());
            }
            System.out.println("Your mana is now " + getMana() + "/" + getMaxMana());
        }else if(consumable.getuId() == 12){
            setStrength(getStrength()+consumable.getEffect());
            System.out.println("Your strength is now " + getStrength() + " for " + buff.getBuffTime() + " turns");
        }
        buff.setBuffId(consumable);
        buff.setBuffTime(consumable.getTime());
        buff.setActiveBuff(consumable.getName());
        buff.setActive(true);
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
        return "Human{" +
                "inventory=" + inventory +
                ", buff=" + buff +
                '}';
    }
}
