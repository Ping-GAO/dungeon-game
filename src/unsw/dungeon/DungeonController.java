package unsw.dungeon;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.*;

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


    private Integer i;
    private Integer j;
    private ImageView bombLitView = null;
    private Timeline enemyTimeline;


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
                startEnemyTimeLine((Enemy) e);
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

    private void startEnemyTimeLine(Enemy enemy) {
        boolean[][] canPassThrough = new boolean[dungeon.getHeight()][dungeon.getWidth()];
        //System.out.println("ok1");
        enemyTimeline = new Timeline(new KeyFrame(Duration.seconds(0.5), (ActionEvent event1) -> {
            // how to break from inside

            initializeMatrix(canPassThrough);
            checkPassbility(canPassThrough);
            //System.out.println("ok2");
            //System.out.println(player.getX() + "---" + player.getY());
            Pair pair = findEnemyNextMoveViaSP(canPassThrough, new Pair(player.getY(),
                    player.getX()), new Pair(enemy.getY(), enemy.getX()));

            if (pair != null) {
                updateEnemy(enemy, pair);
            }
        }));

        enemyTimeline.setCycleCount(Animation.INDEFINITE);
        enemyTimeline.play();

    }


    private void updateEnemy(Enemy enemy, Pair pair) {
        enemy.checkIfPlayer(pair);
        if (!player.alive().getValue()) {
            // System.out.println("palyer diedawdawd");
            enemyTimeline.stop();
        }
        if (pair.x != enemy.getY()) {
            if (pair.x > enemy.getY()) {
                enemy.moveDown();
            } else {
                enemy.moveUp();
            }
        } else {
            //System.out.println(pair.x +"------"+ enemy.getY());
            if (pair.y > enemy.getX()) {
                enemy.moveRight();
            } else {
                enemy.moveLeft();
            }
        }
    }

    private Pair findEnemyNextMoveViaSP(boolean[][] canPassThrough, Pair start, Pair des) {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            canPassThrough[pair.x][pair.y] = false;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    if (i * j != 0) {
                        continue;
                    }
                    if (checkIfOutOfB(pair.x + i, pair.y + j)) {
                        if (pair.x + i == des.x && pair.y + j == des.y) {
                            return pair;
                        } else if (canPassThrough[pair.x + i][pair.y + j]) {
                            queue.add(new Pair(pair.x + i, pair.y + j));
                            //System.out.println("x is"+(pair.x + i)+"y is "+(pair.y + j));
                        }

                    }
                }
            }
        }


        return null;
    }

    private boolean checkIfOutOfB(int x1, int y1) {
        if (x1 < 0 || x1 > dungeon.getHeight() - 1) {
            return false;
        }
        return y1 >= 0 && y1 <= dungeon.getWidth() - 1;
    }

    private void checkPassbility(boolean[][] canPassThrough) {
        for (Entity e : dungeon.getEntities()) {
            if (e != null) {
                // pass all can get through scenarios
                if (e.getName().equals("emptySpace")) {
                    continue;
                }
                if (e.getName().equals("door") && ((Door) e).isOpen().getValue()) {
                    continue;
                }
                canPassThrough[e.getY()][e.getX()] = false;
            }
        }
    }

    private void initializeMatrix(boolean[][] canPassThrough) {
        for (int i = 0; i < dungeon.getHeight(); i++) {
            for (int j = 0; j < dungeon.getWidth(); j++) {
                canPassThrough[i][j] = true;
            }
        }
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
            //TODO
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
//            if(toRemove.getName().equals("player")){
//                dungeon.killPlayer();
//            }
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


    private void removeNodeByAccessHelp(String help, int x, int y, GridPane gridPane) {
        ObservableList<Node> childrens = gridPane.getChildren();
        for (Node node : childrens) {
            if (node != null && node.getAccessibleHelp() != null) {
                if (node instanceof ImageView && node.getAccessibleHelp().equals(help) && ((ImageView) node).getX() == x
                        && ((ImageView) node).getY() == y) {
                    ImageView imageView = (ImageView) node;
                    gridPane.getChildren().remove(imageView);
                    break;
                }
            }
        }

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

    private void LitBomb(ImageView bombView) {
        Bomb bomb = null;
        int x = this.dungeon.getPlayer().getX();
        int y = this.dungeon.getPlayer().getY();
        EmptySpace toRemove = null;
        for (Entity e : this.dungeon.getEntities()) {
            if (e.getName().equals("bomb") && e.getX() == x && e.getY() == y) {
                bomb = (Bomb) e;
            }
            if (e.getName().equals("emptySpace") && e.getX() == x && e.getY() == y) {
                assert e instanceof EmptySpace;
                toRemove = (EmptySpace) e;
            }
        }

        this.dungeon.getEntities().remove(toRemove);
        assert bomb != null;
        bomb.Lit();

        squares.getChildren().remove(bombView);
        int bomb_x = bomb.getX();
        int bomb_y = bomb.getY();

        i = 1;
        j = 0;


        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.3), (ActionEvent event1) -> {

            String temp = "images/bomb_lit_".concat(Integer.toString(i)).concat(".png");
            String tempPrev = "images/bomb_lit_".concat(Integer.toString(j)).concat(".png");

            Image bombLitImage = new Image(temp);
            bombLitView = new ImageView(bombLitImage);
            bombLitView.setAccessibleHelp(temp);
            bombLitView.setX(bomb_x);
            bombLitView.setY(bomb_y);
            // System.out.println((int)bombView.getX()+"wadawd"+(int)bombView.getY());
            removeNodeByAccessHelp(tempPrev, bomb_x, bomb_y, squares);
            //System.out.println("i is "+i);
            squares.add(bombLitView, x, y);

            i += 1;
            j += 1;

        }));
        timeline.setCycleCount(5);

        timeline.setOnFinished(event -> {
            // some concurrent bug here
            removeNodeByAccessHelp("images/bomb_lit_5.png", bomb_x, bomb_y, squares);
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    //  bomb everything in that list
                    // this may or may not get implemented
                    Entity e = findEntityAt(bomb_x + i, bomb_y + j);
                    if (e != null) {
                        e.PerformGetBombed();
                    }
                }
            }
            // System.out.println("done");
        });
        timeline.play();
    }

    private Entity findEntityAt(int x, int y) {
        Entity found;
        for (Entity e : dungeon.getEntities()) {
            if (e.getX() == x && e.getY() == y) {
                found = e;
                return found;
            }
        }

        return null;
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
            LitBomb(bombView);
            Bomb bomb = (Bomb) imageViewToEntity.get(bombView);
            bomb.After();
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
