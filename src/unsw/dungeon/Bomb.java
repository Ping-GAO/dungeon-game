package unsw.dungeon;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * @author Ping GAO
 */
public class Bomb extends Entity {
    private Integer i;
    private Integer j;
    private ImageView bombLitView = null;

    /**
     * @param dungeon dungeon
     * @param x       x
     * @param y       y
     */
    public Bomb(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.setMoveTowardsBehavior(new PlayerMoveTowardsPassThrough(this));
        this.setPickUpBehavior(new PickUpIntoBag(this));
        this.setBoulderMoveTowadsBeheavior(new BoulderMoveTowardsNoWay());
        this.name = "bomb";
        this.setEntityImage(new Image("images/bomb_unlit.png"));
        i = 1;
        j = 0;

    }


    /**
     * lit the bomb
     */
    public void Lit() {
        this.setMoveTowardsBehavior(new PlayerMoveTowardsNoWay());
        this.setPickUpBehavior(new PickUpNoWay());
    }

    public void AfterBlowUp() {
        this.alive().set(false);

    }

    public void LitBomb(ImageView bombView, GridPane squares) {
        Bomb bomb = null;
        int x = this.dungeon.getPlayer().getX();
        int y = this.dungeon.getPlayer().getY();
        EmptySpace toRemove = null;
        for (Entity e : this.dungeon.getEntities()) {
            if (e != null && e.getName().equals("bomb") && e.getX() == x && e.getY() == y) {
                bomb = (Bomb) e;
            }
            if (e != null && e.getName().equals("emptySpace") && e.getX() == x && e.getY() == y) {
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
        // bomb animation
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.3), (ActionEvent event1) -> {

            String temp = "images/bomb_lit_".concat(Integer.toString(i)).concat(".png");
            String tempPrev = "images/bomb_lit_".concat(Integer.toString(j)).concat(".png");

            Image bombLitImage = new Image(temp);
            bombLitView = new ImageView(bombLitImage);
            bombLitView.setAccessibleHelp(temp);
            bombLitView.setX(bomb_x);
            bombLitView.setY(bomb_y);
            removeNodeByAccessHelp(tempPrev, bomb_x, bomb_y, squares);
            squares.add(bombLitView, x, y);

            i += 1;
            j += 1;

        }));
        timeline.setCycleCount(5);

        timeline.setOnFinished(event -> {
            removeNodeByAccessHelp("images/bomb_lit_5.png", bomb_x, bomb_y, squares);
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {

                    //  bomb everything in that list
                    // this may or may not get implemented
                    Entity e = findEntityAt(bomb_x + i, bomb_y + j);
                    if (e != null) {
                        e.PerformGetBombed();
                    }
                }
            }
        });
        timeline.play();
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

}
