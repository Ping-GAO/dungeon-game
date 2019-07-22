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
	
	public void acitivate() {

		int x_new = this.getX();
		int y_new = this.getY();
		
		// or activate a closed door
		System.out.println("foor swotucb activaed");
		//dungeon.getPlayer().getBagPack().addToBagPack(new Key(dungeon, x_new, y_new, "key"));
	    
	}
	
}
