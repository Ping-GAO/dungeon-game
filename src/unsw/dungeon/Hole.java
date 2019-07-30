package unsw.dungeon;

import javafx.scene.image.Image;

public class Hole extends Entity {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Hole(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.name = "hole";
        this.setPickUpBehavior(new PickUpNoWay());
        this.setBoulderMoveTowadsBeheavior(new BoulderMoveTowardsNoWay());
        this.setGetBombedBehavior(new GetBombedNoEffect());
        this.setEntityImage(new Image("images/hole.png"));
    }
}
