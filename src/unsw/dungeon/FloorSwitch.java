package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class FloorSwitch extends Entity {
	private BooleanProperty isActive;
	public FloorSwitch(Dungeon dungeon, int x, int y,String name) {
		super(dungeon, x, y,name);
		this.setMoveTowardsBehavior(new playerMoveTowardsPassThrough(this));
        this.setPickUpBehavior(new PickUpNoWay());
        this.setBoulderMoveTowadsBeheavior(new boulderMoveTowardsSwitch(this));
        this.isActive =  new SimpleBooleanProperty(false);
	}
	public BooleanProperty isActive() {
		return isActive;
	}
	
	
	
}
