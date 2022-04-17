import Items.*;
import Items.Gloves;

import java.util.Arrays;
import java.util.Scanner;

public class Humanoid {
    private int level;
    private int health;
    private int maxHealth;
    private int mana;
    private int maxMana;
    private int strength;
    private int intelligence;
    private int agility;
    private float speed;
    private int[] location;
    private String id;
    private String name;
    private Helmet head;
    private Gloves hands;
    private ChestPiece chest;
    private Greaves legs;
    private Boots feet;
    private Weapon weapon;

    public void Humanoid(){}
//Humaniod objects ability to move
    public void move(String dir, Humanoid unit){
        int[] newLoc;
        switch (dir){
            case "N":
                if(!(unit.getLocation()[0] - 1 < 0)) {
                    newLoc = new int[]{unit.getLocation()[0] - 1, unit.getLocation()[1]};
                    unit.setLocation(newLoc);
                }
                break;
            case"E":
                if(!(unit.getLocation()[1] + 1 > 9)) {
                    newLoc = new int[]{unit.getLocation()[0],unit.getLocation()[1]+1};
                    unit.setLocation(newLoc);
                }
                break;
            case "S":
                if(!(unit.getLocation()[0] + 1 > 9 )) {
                    newLoc = new int[]{unit.getLocation()[0] + 1, unit.getLocation()[1]};
                    unit.setLocation(newLoc);
                }
                break;
            case "W":
                if(!(unit.getLocation()[1] - 1 < 0)) {
                    newLoc = new int[]{unit.getLocation()[0], unit.getLocation()[1] - 1};
                    unit.setLocation(newLoc);
                }
                break;
            default:
                break;
        }
    }
    //Humanoid checks to see if there is another Humanoid at that location
    public boolean checkOccupancy(Humanoid mover,Humanoid stationary){
        return mover.getLocation()[0] == stationary.getLocation()[0] &&  mover.getLocation()[1] == stationary.getLocation()[1];
    }
    //method that deals with the combat loop
    public void initCombat(Human human, Goblin goblin, int difficulty){
        boolean turn;
        int dmg;
        Scanner sc = new Scanner(System.in);

        System.out.println(human.getName() + " has been challenged by a " + goblin.getName());
        //checks to see who is quicker
        if(human.getSpeed()>goblin.getSpeed()){
            turn = false;
        }else{
            turn = true;
        }
        //combat keeps going until one of the combatants dies
        while(human.getHealth()>0 && goblin.getHealth()>0){
            if (!turn){
                System.out.println("what would you like to do?");
                System.out.println("");
                System.out.println("A-Attack     I-Inventory");
                System.out.println("C-Character  E-Enemy");
                String input = sc.nextLine();
                //menu choice for the player to either, open the inventory,Attack the goblin, open your character sheet, or open the goblins sheet
                if(input.equals("I")){
                    human.inventoryManagement(input);
                    turn = true;
                }else if(input.equals("A")){
                    dmg = human.attack(human,goblin);
                    System.out.println(human + " hit for " + dmg);
                    turn = true;
                }else if(input.equals("C")){
                    System.out.println(human.getStats(human));
                }else if(input.equals("E")){
                    System.out.println(goblin.getStats(goblin));

                }
            }
            // combat for the goblin
            if(turn){
                dmg = goblin.attack(goblin,human);
                System.out.println(goblin + " hit for " + dmg);
                turn = false;
            }
            //checks to see if the players buff has expired or not
            if(human.getBuff().getBuffTime()>0){
                human.getBuff().setBuffTime(human.getBuff().getBuffTime()-1);
            }else if(human.getBuff().getBuffTime() == 0 && human.getBuff().isActive()){
                human.removeBuff(human.getBuff().getBuffId());
            }
            System.out.println();

        }
        if(human.getHealth()<=0){
            turn = true;
        }else{
            turn = false;
        }
        //result of the fight
        if(!turn){
            System.out.println(human + " has won the fight.");
            Items item = goblin.drop(difficulty);
            if(item != null) {
                System.out.println(goblin + " has dropped " + item);
                System.out.println("Pickup?(Y or N)");

                String input = sc.nextLine();
                 if(input.equals("Y")){
                    human.insertInventory(item);
                }else{
                    item = null;
                 }
            }
        }else{
            System.out.println(goblin + " has won the fight.");
        }
        System.out.println("");

    }
    //method that determines the value of the attacker attack
    public int attack(Humanoid attacker, Humanoid defender){
        int modifier = Math.max(attacker.getStrength(),Math.max(attacker.getAgility(),attacker.getIntelligence()));
        int attackVal = (int) (Math.random() * modifier);
        defender.setHealth(defender.getHealth() - attackVal);
        return attackVal;
    }
    //method for the character sheet
    public String getStats(Humanoid unit){
        if (unit.getName().compareTo("Goblin")==0){
            return "Name: " + name + " Race: " + "Goblin" + " Level: " + level + "\n" +
                    "Strength: " + strength +" Health: " + health + "/" + maxHealth + "\n"+
                    "Intelligence: " + intelligence + " Mana: " + mana + "/" + maxMana + "\n"+
                    "Agility: " + agility  + " Speed: " + speed + "\n";
        }else {
            return "Name: " + name + " Race: " + "Human" + " Level: " + level + "\n" +
                    "Strength: " + strength + " Health: " + health + "/" + maxHealth + "\n"+
                    "Intelligence: " + intelligence + " Mana: " + mana + "/" + maxMana + "\n"+
                    "Agility: " + agility  + " Speed: " + speed + "\n" +
                    "Helmet: " + head + "\n" + "Chest: " + chest + "\n" + "Gloves: " + hands+ "\n"+
                    "Greaves: " + legs + "\n" + "Boots: " + feet;

        }
      }


    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
    public void setAgility(int agility) {
        this.agility = agility;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setMana(int mana) {
        this.mana = mana;
    }
    public void setSpeed(float movement) {
        this.speed = movement;
    }
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    public void setHead(Helmet head) {
        this.head = head;
    }
    public void setHands(Gloves hands) {
        this.hands = hands;
    }
    public void setChest(ChestPiece chest) {
        this.chest = chest;
    }
    public void setLegs(Greaves legs) {
        this.legs = legs;
    }
    public void setFeet(Boots feet) {
        this.feet = feet;
    }
    public void setLocation(int[] location) {
        this.location = location;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getLevel() {
        return level;
    }
    public int getStrength() {
        return strength;
    }
    public int getIntelligence() {
        return intelligence;
    }
    public int getAgility() {
        return agility;
    }
    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }
    public int getMaxMana() {
        return maxMana;
    }
    public int getMana() {
        return mana;
    }
    public float getMovement() {
        return speed;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Weapon getWeapon() {
        return weapon;
    }
    public Helmet getHead() {
        return head;
    }
    public Gloves getHands() {
        return hands;
    }
    public ChestPiece getChest() {
        return chest;
    }
    public Greaves getLegs() {
        return legs;
    }
    public Boots getFeet() {
        return feet;
    }
    public int[] getLocation() {
        return location;
    }

    public double getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return "Humanoid{" +
                "level=" + level +
                ", health=" + health +
                ", maxHealth=" + maxHealth +
                ", mana=" + mana +
                ", maxMana=" + maxMana +
                ", strength=" + strength +
                ", intelligence=" + intelligence +
                ", agility=" + agility +
                ", speed=" + speed +
                ", location=" + Arrays.toString(location) +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", head=" + head +
                ", hands=" + hands +
                ", chest=" + chest +
                ", legs=" + legs +
                ", feet=" + feet +
                ", weapon=" + weapon +
                '}';
    }
}
