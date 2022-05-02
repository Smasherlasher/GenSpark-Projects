package main;

import Items.*;
import Items.Gloves;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

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
    private boolean attacking;
    private int[] location;
    private String id;
    private String name;
    private Helmet head;
    private Gloves hands;
    private ChestPiece chest;
    private Greaves legs;
    private Boots feet;
    private Weapon weapon;
    private Human player;
    private Goblin enemy;
    private Scene preScene;
    private Items goblinDrop;

    @FXML ImageView weaponType;
    @FXML Pane gameOver;
    @FXML Pane humanPane;
    @FXML Pane goblinPane;
    @FXML Pane dropPane;
    @FXML Pane inventoryPane;
    @FXML GridPane grid = new GridPane();
    @FXML Rectangle goblinHealth;
    @FXML Rectangle playerHealth;
    @FXML Rectangle playerMana;
    @FXML Button attack;
    @FXML Button characterStats;
    @FXML Button enemyStats;
    @FXML Button inventory;
    @FXML Button pickUp;
    @FXML Button leave;
    @FXML Text strGoblin;
    @FXML Text intellGoblin;
    @FXML Text agiGoblin;
    @FXML Text speGoblin;
    @FXML Text allManaGoblin;
    @FXML Text allHealthGoblin;
    @FXML Text strPlayer;
    @FXML Text intellPlayer;
    @FXML Text agiPlayer;
    @FXML Text spePlayer;
    @FXML Text allManaPlayer;
    @FXML Text allHealthPlayer;
    @FXML Text playerName;
    @FXML Text droppedItem;

    //Button initialization
    public void initialize(){
        playerHealth.setWidth(100*(float)player.getHealth()/(float)player.getMaxHealth());
        playerMana.setWidth(100*(float)player.getMana()/(float)player.getMaxMana());
        goblinHealth.setWidth(100*(float)enemy.getHealth()/(float)enemy.getMaxHealth());
        attack.setOnAction(event -> {
            try {
                attack();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        characterStats.setOnAction(event -> {
            try {
                displayCharacter();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        enemyStats.setOnAction(event -> {
            try {
                displayEnemy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        inventory.setOnAction(event -> {
            try {
                inventory();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        pickUp.setOnAction(event -> {
            try {
                pickUp();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        leave.setOnAction(event -> {
            try {
                leave();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
}
    //Humanoid objects ability to move
    public void move(String dir, Humanoid unit){
        int[] newLoc;
        switch (dir){
            case "N":
                if(!(unit.getLocation()[1] - 1 < 0)) {
                    newLoc = new int[]{unit.getLocation()[0], unit.getLocation()[1]-1};
                    unit.setLocation(newLoc);
                }
                break;
            case"E":
                if(!(unit.getLocation()[0] + 1 > 9)) {
                    newLoc = new int[]{unit.getLocation()[0]+1,unit.getLocation()[1]};
                    unit.setLocation(newLoc);
                }
                break;
            case "S":
                if(!(unit.getLocation()[1] + 1 > 9 )) {
                    newLoc = new int[]{unit.getLocation()[0], unit.getLocation()[1] + 1};
                    unit.setLocation(newLoc);
                }
                break;
            case "W":
                if(!(unit.getLocation()[0] - 1 < 0)) {
                    newLoc = new int[]{unit.getLocation()[0]-1, unit.getLocation()[1]};
                    unit.setLocation(newLoc);
                }
                break;
            default:
                break;
        }
    }
    //main.Humanoid checks to see if there is another main.Humanoid at that location
    public boolean checkOccupancy(Humanoid mover,Humanoid stationary){
        return mover.getLocation()[0] == stationary.getLocation()[0] &&  mover.getLocation()[1] == stationary.getLocation()[1];
    }
    //button effects to display the character stats or enemy stats
    @FXML
    public void displayCharacter(){
        if (humanPane.getScaleX() == 0 && inventoryPane.getScaleX() == 0 && goblinPane.getScaleX() == 0){
            humanPane.setScaleX(1);
            if(player.getWeapon().getuId() == 4){
                weaponType.setImage(new Image("C:\\GenSpark\\HumansVGoblins\\src\\Java\\Images\\Daggers.png"));
            }else if(player.getWeapon().getuId() == 13){
                weaponType.setImage(new Image("C:\\GenSpark\\HumansVGoblins\\src\\Java\\Images\\Sword.png"));
            }else if(player.getWeapon().getuId() == 11){
                weaponType.setImage(new Image("C:\\GenSpark\\HumansVGoblins\\src\\Java\\Images\\Staff.png"));
            }

            strPlayer.setText("Strength: "+player.getStrength());
            intellPlayer.setText("Intelligence: "+player.getIntelligence());
            agiPlayer.setText("Agility:"+player.getAgility());
            spePlayer.setText("Speed: "+String.format("%.02f",player.getSpeed()));
            allManaPlayer.setText("Mana: "+player.getMana()+"/"+player.getMaxMana());
            allHealthPlayer.setText("Health: "+player.getHealth()+"/"+player.getMaxHealth());
            playerName.setText("Name: "+player.getName());
        }else{
            humanPane.setScaleX(0);

        }
    }
    @FXML
    public void displayEnemy(){
        if (humanPane.getScaleX() == 0 && inventoryPane.getScaleX() == 0 && goblinPane.getScaleX() == 0){
            goblinPane.setScaleX(1);
            strGoblin.setText("Strength: "+enemy.getStrength());
            intellGoblin.setText("Intelligence: "+enemy.getIntelligence());
            agiGoblin.setText("Agility:"+enemy.getAgility());
            speGoblin.setText("Speed: "+String.format("%.02f",enemy.getSpeed()));
            allManaGoblin.setText("Mana: "+enemy.getMana()+"/"+enemy.getMaxMana());
            allHealthGoblin.setText("Health: "+enemy.getHealth()+"/"+enemy.getMaxHealth());

        }else{
            goblinPane.setScaleX(0);

        }
    }
    //button effects for whether to pickup or leave the item after the goblin drops it
    @FXML
    public void leave(){
        Stage window  = (Stage) attack.getScene().getWindow();
        window.setScene(preScene);
        window.show();
    }
    @FXML
    public void pickUp(){
        player.insertInventory(goblinDrop);
        Stage window  = (Stage) attack.getScene().getWindow();
        window.setScene(preScene);
        window.show();
    }
    @FXML
    //method that determines the value of the attacker attack
    public void attack(){

        if(player.isAttacking()) {
            int modifier = Math.max(player.getStrength(), Math.max(player.getAgility(), player.getIntelligence()));
            int attackVal = (int) (Math.random() * modifier);
            enemy.setHealth(enemy.getHealth() - attackVal);
            player.setAttacking(false);
            enemy.setAttacking(true);
        }else{
            int modifier = Math.max(enemy.getStrength(), Math.max(enemy.getAgility(), enemy.getIntelligence()));
            int attackVal = (int) (Math.random() * modifier);
            player.setHealth(player.getHealth() - attackVal);
            enemy.setAttacking(false);
            player.setAttacking(true);
        }
        playerHealth.setWidth(100*(float)player.getHealth()/(float)player.getMaxHealth());
        playerMana.setWidth(100*(float)player.getMana()/(float)player.getMaxMana());

        goblinHealth.setWidth(100*(float)enemy.getHealth()/(float)enemy.getMaxHealth());
        if(player.getHealth()<=0){
            gameOver.setScaleX(1);
        }

        if(enemy.getHealth()<=0){
            Items drop = enemy.drop(enemy.getLevel());
            if(drop != null){
                inventory.setDisable(true);
                attack.setDisable(true);
                characterStats.setDisable(true);
                enemyStats.setDisable(true);
                goblinDrop = drop;
                droppedItem.setText(drop.getName());
                dropPane.setScaleX(1);
            }else{
                Stage window  = (Stage) attack.getScene().getWindow();
                window.setScene(preScene);
                window.show();
            }
        }

    }
    @FXML
    public void inventory(){
        if (humanPane.getScaleX() == 0 && inventoryPane.getScaleX() == 0 && goblinPane.getScaleX() == 0){
            ArrayList<Items> inventory = player.getInventory();
            Rectangle rec = new Rectangle();
            rec.setWidth(inventoryPane.getWidth()-2);
            rec.setHeight(inventoryPane.getHeight()-3);
            rec.setFill(Color.WHITE);
            rec.setStroke(Color.BLACK);
            rec.setStrokeWidth(5);
            int row = 0;
            inventoryPane.setScaleX(1);
            for(Items item: inventory){
                Text text = new Text(item.getName());
                text.setOnMouseClicked(e -> {
                    if(item.getuId() == 1 || item.getuId() == 7 || item.getuId() == 9 || item.getuId() == 10 || item.getuId() == 12){
                        player.use((Consumables) item);
                        player.getInventory().remove(item);
                        grid.getChildren().remove(text);
                    }else if(item.getuId() == 2 || item.getuId() == 3 || item.getuId() == 5 || item.getuId() == 6 || item.getuId() == 8){
                        player.equip((Armor) item);
                        player.getInventory().remove(item);
                        grid.getChildren().remove(text);
                    }else{
                        player.equipWeapon((Weapon)item);
                        player.getInventory().remove(item);
                        grid.getChildren().remove(text);
                    }
                });
                text.setFill(Color.BLACK);
                grid.setLayoutX(10);
                grid.setLayoutY(5);
                grid.addRow(row);
                grid.add(text,0,row);
                row++;
            }
            inventoryPane.getChildren().add(rec);
            inventoryPane.getChildren().add(grid);
        }else{
            inventoryPane.setScaleX(0);
            grid.getChildren().clear();
            inventoryPane.getChildren().clear();

        }


    }

    public void setHuman(Human human) {
        this.player = human;
    }
    public void setEnemy(Goblin goblin) {
        this.enemy = goblin;
    }
    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
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
    public void setPreScene(Scene preScene) {
        this.preScene = preScene;
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
    public Goblin getEnemy(){return enemy;}
    public Human getPlayer(){return player;}
    public boolean isAttacking() {
        return attacking;
    }

    @Override
    public String toString() {
        return "main.Humanoid{" +
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
