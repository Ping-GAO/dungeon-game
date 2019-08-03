package unsw.dungeon;

import javafx.scene.image.Image;

public class Wall extends Entity {

    public Wall(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.setMoveTowardsBehavior(new PlayerMoveTowardsNoWay());
        this.setPickUpBehavior(new PickUpNoWay());
        this.setBoulderMoveTowadsBeheavior(new BoulderMoveTowardsNoWay());
        this.setGetBombedBehavior(new GetBombedNoEffect());
        this.name = "wall";
        this.setEntityImage(new Image("images/brick_brown_0.png"));
    }

}
