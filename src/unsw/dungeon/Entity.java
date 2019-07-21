package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * @author pinggao
 *
 */
public class Entity {

	// IntegerProperty is used so that changes to the entities position can be
	// externally observed.
	protected IntegerProperty x, y;
	protected BooleanProperty alive;
	protected playerMoveTowardsBehavior moveTowardsBehavior;
	protected boulderMoveTowadsBeheavior boulderMoveTowadsBeheavior;
	protected PickUpBehavior pickUpBehavior;
	protected Dungeon dungeon;

	/**
	 * Create an entity positioned in square (x,y)
	 * 
	 * @param x
	 * @param y
	 * @param dungeon
	 */
	public Entity(Dungeon dungeon, int x, int y) {
		this.x = new SimpleIntegerProperty(x);
		this.y = new SimpleIntegerProperty(y);
		this.dungeon = dungeon;
		this.alive = new SimpleBooleanProperty(true);

	}

	public BooleanProperty alive() {
		return alive;
	}

	public IntegerProperty x() {
		return x;
	}

	public IntegerProperty y() {
		return y;
	}

	public int getY() {
		return y().get();
	}

	public int getX() {
		return x().get();
	}

	public Dungeon getDungeon() {
		return dungeon;
	}

	public void PerformBeMovedTowardsbyPlayer() {
		moveTowardsBehavior.moveTowards();
	}
	public void PerformBeMovedTowardsbyBoulder(Boulder b) {
		boulderMoveTowadsBeheavior.setBoulder(b);
		boulderMoveTowadsBeheavior.moveTowards();
	}

	public void PerformBePickedUp() {
		pickUpBehavior.pickUp();
	}

	public void setMoveTowardsBehavior(playerMoveTowardsBehavior moveTowardsBehavior) {
		this.moveTowardsBehavior = moveTowardsBehavior;
	}

	public void setPickUpBehavior(PickUpBehavior pickUpBehavior) {
		this.pickUpBehavior = pickUpBehavior;
	}

	public void setBoulderMoveTowadsBeheavior(boulderMoveTowadsBeheavior boulderMoveTowadsBeheavior) {
		this.boulderMoveTowadsBeheavior = boulderMoveTowadsBeheavior;
		
	}
}
