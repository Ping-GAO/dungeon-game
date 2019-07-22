package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Door extends Entity {
	
	private BooleanProperty isOpen;
	
	public Door(Dungeon dungeon, int x, int y,String name) {
		super(dungeon, x, y,name);
		this.setMoveTowardsBehavior(new playerMoveTowardsDoor(this));
        this.setPickUpBehavior(new PickUpNoWay());
        this.setBoulderMoveTowadsBeheavior(new boulderMoveTowardsNoWay());
        this.isOpen =  new SimpleBooleanProperty(false);
	}
	public BooleanProperty isOpen() {
		return isOpen;
	}
	public void changeToOpenState() {
		this.setMoveTowardsBehavior(new playerMoveTowardsPassThrough(this));
	}

}
