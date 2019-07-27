package unsw.dungeon;

import javafx.scene.image.Image;

public class Boulder extends Entity {

    public Boulder(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.setMoveTowardsBehavior(new PlayerMoveTowardsBoulder(this));
        this.setPickUpBehavior(new PickUpNoWay());
        this.setBoulderMoveTowadsBeheavior(new BoulderMoveTowardsNoWay());
        this.setGetBombedBehavior(new GetDestroyed(this));
        this.name = "boulder";
        this.setEntityImage(new Image("images/boulder.png"));
        this.setPushpriority(1);
    }

}
