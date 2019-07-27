package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;

public class Door extends Entity {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private BooleanProperty isOpen;

    public Door(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.setMoveTowardsBehavior(new PlayerMoveTowardsDoor(this));
        this.setPickUpBehavior(new PickUpNoWay());
        this.setBoulderMoveTowadsBeheavior(new BoulderMoveTowardsNoWay());
        this.isOpen = new SimpleBooleanProperty(false);
        this.name = "door";
        this.setEntityImage(new Image("images/closed_door.png"));
    }

    public BooleanProperty isOpen() {
        return isOpen;
    }

    public void changeToOpenState() {
        this.isOpen().set(true);
        this.setMoveTowardsBehavior(new PlayerMoveTowardsPassThrough(this));
        this.setBoulderMoveTowadsBeheavior(new BoulderMoveTowardsPassThrough(this));
    }

    public void changeToClosedState() {
        this.isOpen().set(false);
        this.setMoveTowardsBehavior(new PlayerMoveTowardsDoor(this));
    }
}
