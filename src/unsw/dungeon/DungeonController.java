package unsw.dungeon;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Ping GAO
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    @FXML
    private Label message;

    @FXML
    private Label bagpack;

    @FXML
    private Label enemyKill;

    @FXML
    private Label treasureFound;

    @FXML
    private Button restart;

    private List<ImageView> initialEntities;

    private Player player;

    public Player getPlayer() {
        return player;
    }

    private Dungeon dungeon;
    private HashMap<ImageView, Entity> imageViewToEntity;





    /**
     * @param dungeon           the dungeon class
     * @param initialEntities   all the image Views
     * @param imageViewToEntity a hashmap
     */
    DungeonController(Dungeon dungeon, List<ImageView> initialEntities,
                      HashMap<ImageView, Entity> imageViewToEntity) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        this.imageViewToEntity = imageViewToEntity;
    }


    @FXML
    public void initialize() {
        Image ground = new Image("images/dirt_0_new.png");
        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView imageView : initialEntities) {

            squares.add(imageView, (int) imageView.getX(), (int) imageView.getY());
            trackExistence(imageView);
            if (imageViewToEntity.get(imageView).getName().equals("door")) {
                trackDoorState(imageView);
            } else if (imageViewToEntity.get(imageView).getName().equals("floorSwitch")) {
                trackSwitchState(imageView);
            }

        }
        // should start enemy here
        for (Entity e : dungeon.getEntities()) {
            if (e.getName().equals("enemy")) {
                ((Enemy) e).startTimeLine();
            }
        }

        treasureFound.setText(String.valueOf(0));
        enemyKill.setText(String.valueOf(0));

    }


    public void setUpRestartButton(Stage primaryStage) {
        restart.setOnAction(__ ->
        {
            System.out.println("Restarting app!");
            primaryStage.close();
            Platform.runLater(() -> {
                try {
                    new DungeonApplication().start(new Stage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
    }


    private void trackExistence(Node node) {
        imageViewToEntity.get(node).alive().addListener((observable, oldValue, newValue) -> {
            squares.getChildren().remove(node);
            Entity entity = imageViewToEntity.get(node);
            Entity toRemove = null;
            for (Entity e : dungeon.getEntities()) {
                if (e != null) {
                    if (e.getName().equals(entity.getName()) && e.getX() == entity.getX()
                            && entity.getY() == e.getY()) {
                        toRemove = e;
                    }

                }
            }

            dungeon.getEntities().remove(toRemove);

            assert toRemove != null;
            if (toRemove.getName().equals("enemy")) {
                dungeon.getPlayer().getBagPack().addToBagPack(new EnemyBodyPart(dungeon,
                        toRemove.getX(), toRemove.getY()));
                Sword s = dungeon.getPlayer().getBagPack().getSword();
                if (s != null) {
                    s.decreaseDurability();
                }

            }
        });
    }

    private void trackDoorState(Node node) {
        DoorStateTracker doorStateTracker = new DoorStateTracker((ImageView) node, squares);
        ((Door) imageViewToEntity.get(node)).isOpen().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                doorStateTracker.toOpenState();
            } else {
                doorStateTracker.toClosedState();
            }
        });

    }

    private void trackSwitchState(Node node) {
        FloorSwitch floorswitch = (FloorSwitch) imageViewToEntity.get(node);
        floorswitch.isActive().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                floorswitch.activate();
            } else {
                floorswitch.deactivate();
            }
        });

    }



    private ImageView getOutABombFromBagPack() {
        int x = this.dungeon.getPlayer().getX();
        int y = this.dungeon.getPlayer().getY();
        Bomb bomb = new Bomb(dungeon, x, y);

        this.dungeon.addEntity(bomb);
        ImageView view = bomb.MakeImageViewFromEntity();
        view.setX(x);
        view.setY(y);

        squares.add(view, x, y);

        imageViewToEntity.put(view, bomb);
        trackExistence(view);

        Bomb toRemove = null;
        for (Entity e : this.dungeon.getPlayer().getBagPack().getBagPack()) {
            if (e.getName().equals("bomb")) {
                toRemove = (Bomb) e;
            }
        }
        this.dungeon.getPlayer().getBagPack().getBagPack().remove(toRemove);
        return view;
    }






    private void updateMessage() {
        // update the bagpack information
        if (player.getBagPack().getBagPack().size() != 0) {
            // System.out.println("player has : " + player.getBagPack().toString());
            bagpack.setText(player.getBagPack().toString());
        } else {
            bagpack.setText("");
        }

        if (!player.getMessage().isEmpty()) {
            if ((!"You died.".equals(player.getMessage()))) {
                message.setText(player.getMessage());
                player.clearMessage();
            } else {
                message.setText(player.getMessage());
            }
        } else {
            message.setText("");
        }
    }


    private void handlePlayerDropBomb() {
        if (player.checkIfHaveBomb()) {
            ImageView bombView = getOutABombFromBagPack();
            Bomb bomb = (Bomb) imageViewToEntity.get(bombView);
            bomb.LitBomb(bombView, squares);
            bomb.AfterBlowUp();
        }
    }

    private void handlePlayerSwingSword() {
        int x = player.getX();
        int y = player.getY();

        Sword sword = null;
        for (Entity e : dungeon.getPlayer().getBagPack().getBagPack()) {
            if (e != null) {
                if (e.getName().equals("sword")) {
                    sword = (Sword) e;
                    break;
                }
            }
        }
        if (sword != null) {

            for (int i = -2; i <= 2; i++) {
                for (int j = -2; j <= 2; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    Enemy enemy = findEnemy(x + i, y + j);
                    if (enemy != null) {
                        enemy.alive().setValue(false);

                    }
                }
            }
        }

    }

    private Enemy findEnemy(int x, int y) {
        Enemy entity = null;
        for (Entity e : dungeon.getEntities()) {
            if (e != null) {
                if (e.getName().equals("enemy") && e.getX() == x && e.getY() == y) {
                    entity = (Enemy) e;
                    break;
                }
            }
        }
        return entity;
    }

    /**
     * @param event player keyboard input
     */
    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                player.moveUp();
                break;
            case DOWN:
                player.moveDown();
                break;
            case LEFT:
                player.moveLeft();
                break;
            case RIGHT:
                player.moveRight();
                break;
            case L:
                handlePlayerDropBomb();
                break;
            case A:
                // press A to attack enemy in 2*2
                handlePlayerSwingSword();
                break;
            default:
                break;
        }

        updateMessage();
    }


}
