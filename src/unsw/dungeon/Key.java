package unsw.dungeon;

import javafx.scene.image.Image;

/**
 * @author Ping GAO
 */
public class Key extends Entity {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Key(Dungeon dungeon, int x, int y) {

        super(dungeon, x, y);
        this.setMoveTowardsBehavior(new PlayerMoveTowardsPassThrough(this));
        this.setPickUpBehavior(new PickUpIntoBag(this));
        this.setBoulderMoveTowadsBeheavior(new BoulderMoveTowardsNoWay());
        this.name = "key";
        this.setEntityImage(new Image("images/key.png"));

    }
}
