package main;
//import java.awt.*;
import main.Human;
import main.Land;

import java.util.Arrays;

public class World {
    private int xPos;
    private int ypos;
    private Land[][] map;
    private boolean openDoor;

    public World(int x,int y,int difficulty) {
        fillWorld(x,y,difficulty);
    }
    //method that fills the world with land objects based on the size of the world
    public void fillWorld(int x,int y,int difficulty) {
        setOpenDoor(false);
        Land[][] filledmap = new Land[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                filledmap[i][j] = new Land("#", i, j,difficulty);

            }
        }
        setMap(filledmap);
    }
    //method that opens the door to the next floor
    public void openDoor(int x,int y){
        getMap()[x][y].setId("X");
        openDoor = true;
        xPos = x;
        ypos = y;
        System.out.println("The door to the next floor has opened.");
    }
    //method that replaces tiles when the player moves,goblin moves, or if a chest is looted
    public void replace(int x,int y,String replacememt){
        if (getMap()[x][y].isHasChest() && replacememt.equals("#")){
            getMap()[x][y].setId("*");

        }else{
            getMap()[x][y].setId(replacememt);
        }
    }
    //shows the map when its called
    public void displayMap(int bound) {
        for (int x = 0; x < bound; x++) {
            for (int y = 0; y < bound; y++) {
                System.out.print("  " + getMap()[x][y]);
            }
            System.out.println("");
        }
    }
    //method that returns true if the location has a chest or not
    public boolean checkOccupancy(Human human){

        return getMap()[human.getLocation()[0]][human.getLocation()[1]].isHasChest();
    }
    public Land[][] getMap() {
        return map;
    }


    public boolean isOpenDoor() {
        return openDoor;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYpos() {
        return ypos;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }

    public void setOpenDoor(boolean opendoor) {
        this.openDoor = opendoor;
    }
    public void setMap(Land[][] map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "main.World{" +
                "xPos=" + xPos +
                ", ypos=" + ypos +
                ", map=" + Arrays.toString(map) +
                ", openDoor=" + openDoor +
                '}';
    }
}