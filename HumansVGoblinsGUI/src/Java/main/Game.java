package main;

import Items.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.util.ArrayList;


public class Game {
    @FXML GridPane gameBoard;
    @FXML GridPane grid = new GridPane();
    @FXML ImageView weaponType;
    @FXML ImageView buffImage;
    @FXML Pane chestPane;
    @FXML Pane inventoryPane;
    @FXML Pane humanPane;
    @FXML Pane buffPane;
    @FXML Button pickUp;
    @FXML Button leave;
    @FXML Button characterStats;
    @FXML Button inventory;
    @FXML Button north;
    @FXML Button west;
    @FXML Button south;
    @FXML Button east;
    @FXML Button stay;
    @FXML Text strChange;
    @FXML Text intellChange;
    @FXML Text agiChange;
    @FXML Text buffTimer;
    @FXML Text chestItem;
    @FXML Text strPlayer;
    @FXML Text intellPlayer;
    @FXML Text agiPlayer;
    @FXML Text spePlayer;
    @FXML Text allManaPlayer;
    @FXML Text allHealthPlayer;
    @FXML Text playerName;

    private World world;
    private Human human;
    private ArrayList<Goblin> goblins = new ArrayList<>();
    private final int dimension = 10;
    private int difficulty = 6;
    private boolean nextFloor;
    private Items item;
    //init human object with random gear and random location
    public Game(String name) {
        int rng = (int) (Math.random() * 2);
        Weapon weapon;

        if(rng == 0) {
            weapon = new Dagger(difficulty);
        }else if(rng == 1){
            weapon = new Staff(difficulty);
        }else{
            weapon = new Sword(difficulty);
        }
        setHuman(new Human(1, 5, 5, 5, name, new int[]{(int) (Math.random()*(dimension-1)), (int) (Math.random()*(dimension-1))}
                , new Helmet(difficulty), new Gloves(difficulty), new ChestPiece(difficulty), new Greaves(difficulty), new Boots(difficulty),weapon));
        getHuman().getInventory().add(new HealthPotion(1));
        getHuman().getInventory().add(new AgilityPotion(1));

    }

    public Game() {}
    //button effects for whether to pickup or leave the item after the goblin drops it
    @FXML
    public void leave(){
        chestPane.setScaleX(0);
    }
    @FXML
    public void pickUp(){
        chestPane.setScaleX(0);
        world.getMap()[getHuman().getLocation()[0]][getHuman().getLocation()[1]].setHasChest(false);
        getHuman().insertInventory(item);
    }
    //Button initialization
    @FXML
    public void initialize(){
        initGame();
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
        north.setOnAction(event -> {
            try {
                handleButtonPressed("N");
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        east.setOnAction(event -> {
            try {
                handleButtonPressed("E");
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        south.setOnAction(event -> {
            try {
                handleButtonPressed("S");
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        west.setOnAction(event -> {
            try {
                handleButtonPressed("W");
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        stay.setOnAction(event -> {
            try {
                handleButtonPressed("");
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
        characterStats.setOnAction(event -> {
            try {
                displayStats();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }
    //button effect to display character stats
    @FXML
    public void displayStats(){
        if (humanPane.getScaleX() == 0){
            humanPane.setScaleX(1);
            if(getHuman().getWeapon().getuId() == 4){
                weaponType.setImage(new Image("C:\\GenSpark\\HumansVGoblins\\src\\Java\\Images\\Daggers.png"));
            }else if(getHuman().getWeapon().getuId() == 13){
                weaponType.setImage(new Image("C:\\GenSpark\\HumansVGoblins\\src\\Java\\Images\\Sword.png"));
            }else if(getHuman().getWeapon().getuId() == 11){
                weaponType.setImage(new Image("C:\\GenSpark\\HumansVGoblins\\src\\Java\\Images\\Staff.png"));
            }

            strPlayer.setText("Strength: "+getHuman().getStrength());
            intellPlayer.setText("Intelligence: "+getHuman().getIntelligence());
            agiPlayer.setText("Agility:"+getHuman().getAgility());
            spePlayer.setText("Speed: "+String.format("%.02f",getHuman().getSpeed()));
            allManaPlayer.setText("Mana: "+getHuman().getMana()+"/"+getHuman().getMaxMana());
            allHealthPlayer.setText("Health: "+getHuman().getHealth()+"/"+getHuman().getMaxHealth());
            playerName.setText("Name: "+getHuman().getName());
        }else{
            humanPane.setScaleX(0);

        }
    }
    //button effect to siplay the characters inventory
    @FXML
    public void inventory(){
        if (inventoryPane.getScaleX() == 0){
            ArrayList<Items> inventory = human.getInventory();
            Rectangle rec = new Rectangle();
            rec.setWidth(inventoryPane.getWidth()-2);
            rec.setHeight(inventoryPane.getHeight()-3);
            rec.setFill(Color.WHITE);
            rec.setStroke(Color.BLACK);
            rec.setStrokeWidth(5);
            int row = 0;
            int col = 0;
            inventoryPane.setScaleX(1);
            for(Items item: inventory){
                Text text = new Text(item.getName());
                text.setOnMouseClicked(e -> {
                        if(item.getuId() == 1 || item.getuId() == 7 || item.getuId() == 9 || item.getuId() == 10 || item.getuId() == 12){
                            if(item.getuId() != 7 || item.getuId() != 10){
                                buffPane.setScaleX(1);
                                getHuman().use((Consumables) item);
                                getHuman().getInventory().remove(item);
                                grid.getChildren().remove(text);
                                if(item.getuId() == 1){
                                    buffImage.setImage(new Image("C:\\GenSpark\\HumansVGoblins\\src\\Java\\Images\\AgilityPotion.png"));
                                }else if(item.getuId() == 9){
                                    buffImage.setImage(new Image("C:\\GenSpark\\HumansVGoblins\\src\\Java\\Images\\IntelligencePotion.png"));
                                }else if(item.getuId() == 12){
                                    buffImage.setImage(new Image("C:\\GenSpark\\HumansVGoblins\\src\\Java\\Images\\StrengthPotion.png"));
                                }
                                buffTimer.setText("Turns remaining: " + human.getBuff().getBuffTime());
                            }

                        }else if(item.getuId() == 2 || item.getuId() == 3 || item.getuId() == 5 || item.getuId() == 6 || item.getuId() == 8){
                            getHuman().equip((Armor) item);
                            getHuman().getInventory().remove(item);
                            grid.getChildren().remove(text);
                        }else{
                            getHuman().equipWeapon((Weapon)item);
                            getHuman().getInventory().remove(item);
                            grid.getChildren().remove(text);
                        }
                });
                text.setFill(Color.BLACK);
                grid.setLayoutX(10);
                grid.setLayoutY(5);
                grid.addRow(row);
                grid.add(text,col,row);
                row++;

                if(row > 4){
                    row = 0;
                    col++;
                }
            }
            inventoryPane.getChildren().add(rec);
            inventoryPane.getChildren().add(grid);
        }else{
            inventoryPane.setScaleX(0);
            grid.getChildren().clear();
            inventoryPane.getChildren().clear();

        }


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
        setGoblins(goblins);
        world.replace(human.getLocation()[0], human.getLocation()[1], human.getId());
    }
    public void displayImage()throws Exception{
        gameBoard.getChildren().clear();
        Image humanImage = new Image(new FileInputStream("C:\\GenSpark\\HumansVGoblins\\src\\Java\\Images\\Human.png"));
        Image goblinImage = new Image(new FileInputStream("C:\\GenSpark\\HumansVGoblins\\src\\Java\\Images\\Goblin.png"));
        Image chestImage = new Image(new FileInputStream("C:\\GenSpark\\HumansVGoblins\\src\\Java\\Images\\Chest.png"));
        Image doorImage = new Image(new FileInputStream("C:\\GenSpark\\HumansVGoblins\\src\\Java\\Images\\Door.png"));

        ImageView imageView = new ImageView(humanImage);
        imageView.setFitHeight(75);
        imageView.setFitWidth(75);
        gameBoard.add(imageView,getHuman().getLocation()[0],getHuman().getLocation()[1]);

        for (Goblin goblin:goblins) {
            imageView = new ImageView(goblinImage);
            imageView.setFitHeight(75);
            imageView.setFitWidth(75);
            gameBoard.add(imageView,goblin.getLocation()[0],goblin.getLocation()[1]);
        }
        int count = 0;
        for(int i = 0;i<10 && count<10;i++){
            if(world.getMap()[i][count].isHasChest()) {
                imageView = new ImageView(chestImage);
                imageView.setFitHeight(75);
                imageView.setFitWidth(75);
                //System.out.println(i+","+count+":"+(world.getMap()[i][count].isHasChest()));
                gameBoard.add(imageView,i,count);
            }
            if(i == 9){
                i = 0;
                count++;
            }
        }
        if(world.isOpenDoor()) {
            imageView = new ImageView(doorImage);
            imageView.setFitHeight(75);
            imageView.setFitWidth(75);
            gameBoard.add(imageView, world.getXPos(), world.getYpos());
        }
    }
    public void compareItems(Items item){
        int oldIntel = 0,newIntel = 0;
        int oldStr = 0,newStr = 0;
        int oldAgi = 0,newAgi = 0;

        switch (item.getuId()){
            case 2:
                oldStr = getHuman().getFeet().getStats()[0];
                oldIntel = getHuman().getFeet().getStats()[1];
                oldAgi = getHuman().getFeet().getStats()[2];
                newStr = item.getStats()[0];
                newIntel = item.getStats()[1];
                newAgi = item.getStats()[2];
                break;
            case 3:
                oldStr = getHuman().getChest().getStats()[0];
                oldIntel = getHuman().getChest().getStats()[1];
                oldAgi = getHuman().getChest().getStats()[2];
                newStr = item.getStats()[0];
                newIntel = item.getStats()[1];
                newAgi = item.getStats()[2];
                break;
            case 5:
                oldStr = getHuman().getHands().getStats()[0];
                oldIntel = getHuman().getHands().getStats()[1];
                oldAgi = getHuman().getHands().getStats()[2];
                newStr = item.getStats()[0];
                newIntel = item.getStats()[1];
                newAgi = item.getStats()[2];
                break;
            case 6:
                oldStr = getHuman().getLegs().getStats()[0];
                oldIntel = getHuman().getLegs().getStats()[1];
                oldAgi = getHuman().getLegs().getStats()[2];
                newStr = item.getStats()[0];
                newIntel = item.getStats()[1];
                newAgi = item.getStats()[2];
                break;
            case 8:
                oldStr = getHuman().getHead().getStats()[0];
                oldIntel = getHuman().getHead().getStats()[1];
                oldAgi = getHuman().getHead().getStats()[2];
                newStr = item.getStats()[0];
                newIntel = item.getStats()[1];
                newAgi = item.getStats()[2];
                break;
            default:
                break;
        }
        strChange.setText("Current strength bonus: "+oldStr +" New strength bonus: "+newStr+"-->"+(oldStr-newStr));
        intellChange.setText("Current intelligence bonus: "+oldIntel +" New intelligence bonus: "+newIntel+"-->"+(oldIntel-newIntel));
        agiChange.setText("Current agility bonus: "+oldAgi +" New agility bonus: "+newAgi+"-->"+(oldAgi-newAgi));

    }
    //main game loop button
    public void handleButtonPressed(String userInput)throws Exception{
        setNextFloor(false);
        if(!goblins.isEmpty() || !isNextFloor()){
            if(world.checkOccupancy(human)){
                Items chestDrop = world.getMap()[human.getLocation()[0]][human.getLocation()[1]].getChest().getItem();
                compareItems(chestDrop);
                chestItem.setText(chestDrop.getName());
                chestPane.setScaleX(1);
                item = chestDrop;

            }
            if(goblins.isEmpty() && !world.isOpenDoor()){
                world.openDoor((int)(Math.random()*(dimension-1)),(int) (Math.random()*(dimension-1)));
            }
            human.move(userInput, human);
                //goblins only move after the players turn
            for (Goblin goblin:goblins) {
                if (goblin.getHealth() <= 0) {
                    goblins.remove(goblin);
                }
                goblin.move(goblin.follow(human.getLocation(), goblin.getLocation()), goblin);
                //if the goblin runs into the player combat is started
                if (goblin.checkOccupancy(goblin, human)) {
                    if (human.getSpeed() > goblin.getSpeed()) {
                        human.setAttacking(true);
                        goblin.setAttacking(false);

                    } else {
                        human.setAttacking(false);
                        goblin.setAttacking(true);
                    }
                    FXMLLoader fxmlLoader = new FXMLLoader(HumanVGoblin.class.getResource("HumanoidController.fxml"));
                    Stage window = (Stage) gameBoard.getParent().getScene().getWindow();
                    Humanoid humanoidController = new Humanoid();
                    fxmlLoader.setController(humanoidController);
                    humanoidController.setEnemy(goblin);
                    humanoidController.setHuman(human);
                    humanoidController.setPreScene(gameBoard.getScene());
                    window.setScene(new Scene(fxmlLoader.load(), 600, 400));
                }
            }

            //checks to see if the player is at the location for the next floor
            if(human.getLocation()[0] == world.getXPos() && human.getLocation()[1] == world.getYpos()){
                FXMLLoader fxmlLoader = new FXMLLoader(HumanVGoblin.class.getResource("HumanVGoblinGame.fxml"));
                Stage window = (Stage) gameBoard.getScene().getWindow();

                Game gameController = new Game();
                fxmlLoader.setController(gameController);
                gameController.setHuman(human);
                gameController.setDifficulty(++difficulty);
                window.setScene(new Scene(fxmlLoader.load(),750,900));
                setNextFloor(true);
            }
            //checks to see if the players buff a has expired or not
            if(human.getBuff().getBuffTime()>0){
                human.getBuff().setBuffTime(human.getBuff().getBuffTime()-1);
                buffTimer.setText("Turns remaining: " + human.getBuff().getBuffTime());
            }else if(human.getBuff().getBuffTime() == 0 && human.getBuff().isActive()){
                human.removeBuff(human.getBuff().getBuffId());
                buffPane.setScaleX(0);
            }
        }
        displayImage();

    }
    public void setGoblins(ArrayList<Goblin> goblins){this.goblins = goblins;}
    public void setHuman(Human human){this.human = human;}
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
    public Human getHuman() {
        return human;
    }
    public ArrayList<Goblin> getGoblins() {
        return goblins;
    }
    @Override
    public String toString() {
        return "main.Game{" +
                "world=" + world +
                ", human=" + human +
                ", goblins=" + goblins +
                ", dimension=" + dimension +
                ", difficulty=" + difficulty +
                ", nextFloor=" + nextFloor +
                '}';
    }
}
