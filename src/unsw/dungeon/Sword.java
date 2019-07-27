package unsw.dungeon;

import javafx.scene.image.Image;

public class Sword extends Entity {

    public Sword(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.setMoveTowardsBehavior(new PlayerMoveTowardsPassThrough(this));
        this.setPickUpBehavior(new PickUpIntoBag(this));
        this.setBoulderMoveTowadsBeheavior(new BoulderMoveTowardsNoWay());
        this.name = "sword";
        this.setEntityImage(new Image("images/greatsword_1_new.png"));
    }

}
