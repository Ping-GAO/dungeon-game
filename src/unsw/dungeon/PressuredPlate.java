package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class PressuredPlate extends Entity {
	private BooleanProperty isActive;

	public PressuredPlate(Dungeon dungeon, int x, int y, String name) {
		super(dungeon, x, y, name);
		this.setMoveTowardsBehavior(new playerMoveTowadsPressurePlate(this));
		this.setPickUpBehavior(new PickUpNoWay());
		this.setBoulderMoveTowadsBeheavior(new boulderMoveTowardsNoWay());

		this.isActive = new SimpleBooleanProperty(true);
	}

	public BooleanProperty isActive() {
		return isActive;
	}
	public void deactivate() {
		System.out.println("pressure plate triggrt");
	}
}
