package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Door extends Entity {
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private BooleanProperty isOpen;

	public Door(Dungeon dungeon, int x, int y, String name) {
		super(dungeon, x, y, name);
		this.setMoveTowardsBehavior(new playerMoveTowardsDoor(this));
		this.setPickUpBehavior(new PickUpNoWay());
		this.setBoulderMoveTowadsBeheavior(new boulderMoveTowardsNoWay());
		this.isOpen = new SimpleBooleanProperty(false);
	}

	public BooleanProperty isOpen() {
		return isOpen;
	}

	public void changeToOpenState() {
		this.isOpen().set(true);
		this.setMoveTowardsBehavior(new playerMoveTowardsPassThrough(this));
		this.setBoulderMoveTowadsBeheavior(new boulderMoveTowardsPassThrough(this));
	}

	public void changeToClosedState() {
		this.isOpen().set(false);
		this.setMoveTowardsBehavior(new playerMoveTowardsDoor(this));
	}
}
