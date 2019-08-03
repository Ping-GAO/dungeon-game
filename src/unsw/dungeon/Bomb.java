package unsw.dungeon;

import javafx.scene.image.Image;

/**
 * @author Ping GAO
 */
public class Bomb extends Entity {


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
    }


    /**
     * lit the bomb
     */
    public void Lit() {
        this.setMoveTowardsBehavior(new PlayerMoveTowardsNoWay());
        this.setPickUpBehavior(new PickUpNoWay());
    }

    public void After() {
        this.alive().set(false);

    }
}
