package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class FloorSwitch extends Entity {
	private BooleanProperty isActive;

	public FloorSwitch(Dungeon dungeon, int x, int y, String name) {
		super(dungeon, x, y, name);
		this.setMoveTowardsBehavior(new playerMoveTowardsPassThrough(this));
		this.setPickUpBehavior(new PickUpNoWay());
		this.setBoulderMoveTowadsBeheavior(new boulderMoveTowardsSwitch(this));
		this.isActive = new SimpleBooleanProperty(false);
	}

	public BooleanProperty isActive() {
		return isActive;
	}

	public void acitivate() {

		// or activate a closed door
		System.out.println("foor switch activaed");
		// when activate should have a boulder at same spot
		Boulder boulder = null;
		for(Entity e : dungeon.getEntities()) {
			if(e!=null) {
				if(e.getX()==this.getX() && e.getY()==this.getY() && e.getName().equals("boulder")) {
					boulder = (Boulder) e;
				}
			}
		}
		
		
		
		
		this.setBoulderMoveTowadsBeheavior(new boulderMoveTowardsNoWay());
		this.setMoveTowardsBehavior(new playerMoveTowardsBoulder(boulder));
	}
	
	
	public void deactivate() {
		System.out.println("foor switch DEactivaed");
		this.setMoveTowardsBehavior(new playerMoveTowardsPassThrough(this));
		this.setBoulderMoveTowadsBeheavior(new boulderMoveTowardsSwitch(this));
		this.isActive = new SimpleBooleanProperty(false);
	}

}
