package unsw.dungeon;

import javafx.scene.image.Image;

/**
 * @author Ping GAO
 */
public class Treasure extends Entity {

    public Treasure(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.setMoveTowardsBehavior(new PlayerMoveTowardsPassThrough(this));
        this.setPickUpBehavior(new PickUpIntoBag(this));
        this.setBoulderMoveTowadsBeheavior(new BoulderMoveTowardsNoWay());
        this.setGetBombedBehavior(new GetBombedNoEffect());
        this.name = "treasure";
        this.setEntityImage(new Image("images/gold_pile.png"));
    }

}
