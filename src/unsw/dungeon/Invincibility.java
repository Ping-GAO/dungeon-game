package unsw.dungeon;

import javafx.scene.image.Image;

/**
 * @author Ping GAO
 */
public class Invincibility extends Entity {


    /**
     * @param dungeon dungeon
     * @param x       x
     * @param y       y
     *                magic potion
     */
    public Invincibility(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.setMoveTowardsBehavior(new PlayerMoveTowardsPassThrough(this));
        this.setPickUpBehavior(new PickUpIntoBag(this));
        this.setBoulderMoveTowadsBeheavior(new BoulderMoveTowardsNoWay());
        this.name = "invincibility";
        this.setEntityImage(new Image("images/brilliant_blue_new.png"));

    }


}
