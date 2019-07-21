package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Entity {

	// IntegerProperty is used so that changes to the entities position can be
	// externally observed.
	protected IntegerProperty x, y;
	protected MoveTowardsBehavior moveTowardsBehavior;
	protected Dungeon dungeon;

	public void setMoveTowardsBehavior(MoveTowardsBehavior moveTowardsBehavior) {
		this.moveTowardsBehavior = moveTowardsBehavior;
	}

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

	public void PerformMoveTowards() {
		moveTowardsBehavior.moveTowards();
	}
}
