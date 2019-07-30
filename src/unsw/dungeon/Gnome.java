package unsw.dungeon;

import javafx.scene.image.Image;

public class Gnome extends Entity {

    public Gnome(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.setBoulderMoveTowadsBeheavior(new BoulderMoveTowardsNoWay());
        this.name = "gnome";
        this.setEntityImage(new Image("images/gnome.png"));
        this.setMoveTowardsBehavior(new PlayerMoveTowardsGnome());
        this.setPickUpBehavior(new PickUpNoWay());
    }
}
