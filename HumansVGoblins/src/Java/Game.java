import Items.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game {

    private World world;
    private final Human human;
    private final ArrayList<Goblin> goblins = new ArrayList<>();
    private final int dimension = 10;
    private int difficulty = 1;
    private boolean nextFloor;

    //init human object with random gear and random location
    public Game() {
        System.out.println("Hello adventurer what is your name?");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        int rng = (int) (Math.random() * 2);
        Weapon weapon;
        if(rng == 0) {
            weapon = new Dagger(difficulty);
        }else if(rng == 1){
            weapon = new Staff(difficulty);
        }else{
            weapon = new Sword(difficulty);
        }
        human = new Human(1, 5, 5, 5, name, new int[]{(int) (Math.random()*(dimension-1)), (int) (Math.random()*(dimension-1))}
                , new Helmet(difficulty), new Gloves(difficulty), new ChestPiece(difficulty), new Greaves(difficulty), new Boots(difficulty),weapon);
    }
    //init the rest of the game such as the amount of goblins and the world itself
    public void initGame(){
        world = new World(dimension, dimension, difficulty);
        for(int x = 0; x < difficulty;x++) {
            Goblin goblin = new Goblin((int) (Math.random()*difficulty)+1, (int) (Math.random()*difficulty)+1, (int) (Math.random()*difficulty)+1,(int) (Math.random()*difficulty)+1
                    , "Goblin", new int[]{(int) (Math.random()*(dimension-1)), (int) (Math.random()*(dimension-1))});
            world.replace(goblin.getLocation()[0], goblin.getLocation()[1], goblin.getId());
            goblins.add(goblin);
        }
        world.replace(human.getLocation()[0], human.getLocation()[1], human.getId());
    }
    //help menu for controls
    public void helpMap(){
        System.out.println("I for Inventory");
        System.out.println("C to open the Character Sheet");
        System.out.println("M to open the Map");
        System.out.println("N to move up");
        System.out.println("S to move down");
        System.out.println("E to move right");
        System.out.println("W to move left");
        System.out.println();

    }
    //main game loop. Ends when you die
    public void playGame() {
        while(human.getHealth()>0){
            initGame();
            setNextFloor(false);
            //main level loop. Ends when you kill all goblins, and you go to the exit location
            while(!goblins.isEmpty() || !isNextFloor()){
                try {
                    Scanner sc = new Scanner(System.in);
                    String input;
                    //checks to see if there is a chest on the ground and if there is ask whether to pick it up or not
                    if(world.checkOccupancy(human)){
                        Items item = world.getMap()[human.getLocation()[0]][human.getLocation()[1]].getChest().getItem();
                        System.out.println("Chest contains: " + item.getName());
                        human.compareItems(item);
                        System.out.println("Pickup?(Y or N)");

                        input = sc.nextLine();
                        if(input.equals("Y")){
                            human.insertInventory(item);
                            world.getMap()[human.getLocation()[0]][human.getLocation()[1]].setHasChest(false);
                            world.replace(human.getLocation()[0], human.getLocation()[1], "@");

                        }
                        world.displayMap(dimension);
                    }
                    //checks to see if goblins are all dead and if so opens the door to the next level
                    if(goblins.isEmpty() && !world.isOpenDoor()){
                        world.openDoor((int)(Math.random()*(dimension-1)),(int) (Math.random()*(dimension-1)));
                    }
                    System.out.println();
                    System.out.println("What would you like to do?(H for help)");
                    input = sc.nextLine();
                    //decision on whether to open the inventory,open the map,open the character sheet or move
                    if(input.equals("H")){
                         helpMap();
                    }else if(input.equals("I")) {
                        human.inventoryManagement(input);
                    }else if(input.equals("M")){
                        world.displayMap(dimension);
                    }else if(input.equals("C")){
                        System.out.println(human.getStats(human));
                    }else{
                        world.replace(human.getLocation()[0], human.getLocation()[1], "#");
                        human.move(input, human);
                        world.replace(human.getLocation()[0], human.getLocation()[1], human.getId());
                        //goblins only move after the players turn
                        for (Goblin goblin:goblins) {
                            world.replace(goblin.getLocation()[0], goblin.getLocation()[1], "#");
                            goblin.move(goblin.follow(human.getLocation(), goblin.getLocation()), goblin);
                            world.replace(goblin.getLocation()[0], goblin.getLocation()[1], goblin.getId());
                            //if the goblin runs into the player combat is started
                            if(goblin.checkOccupancy(goblin,human)){
                                goblin.initCombat(human,goblin,difficulty);
                            }
                            if(goblin.getHealth() <= 0){
                                goblins.remove(goblin);
                                world.replace(human.getLocation()[0], human.getLocation()[1], "@");

                              }
                        }
                        world.displayMap(dimension);

                    }
                    //checks to see if the player is at the location for the next floor
                    if(human.getLocation()[0] == world.getXPos() && human.getLocation()[1] == world.getYpos()){
                        setNextFloor(true);
                        difficulty++;
                        System.out.println("You have climb to floor " + difficulty);
                    }
                    //checks to see if the players buff a has expired or not
                    if(human.getBuff().getBuffTime()>0){
                        human.getBuff().setBuffTime(human.getBuff().getBuffTime()-1);
                    }else if(human.getBuff().getBuffTime() == 0 && human.getBuff().isActive()){
                        human.removeBuff(human.getBuff().getBuffId());
                        System.out.println(human.getBuff().getActiveBuff() + "has faded.");
                    }

                } catch (Exception e) {

                }
            }
            setDifficulty(getDifficulty()+1);
        }
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setNextFloor(boolean nextFloor) {
        this.nextFloor = nextFloor;
    }

    public boolean isNextFloor() {
        return nextFloor;
    }

    public int getDifficulty() {
        return difficulty;
    }

    @Override
    public String toString() {
        return "Game{" +
                "world=" + world +
                ", human=" + human +
                ", goblins=" + goblins +
                ", dimension=" + dimension +
                ", difficulty=" + difficulty +
                ", nextFloor=" + nextFloor +
                '}';
    }
}
