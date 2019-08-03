package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;

/**
 * @author Ping GAO
 */
public class FloorSwitch extends Entity {
	private BooleanProperty isActive;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FloorSwitch(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.setMoveTowardsBehavior(new PlayerMoveTowardsPassThrough(this));
		this.setPickUpBehavior(new PickUpNoWay());
		this.setBoulderMoveTowadsBeheavior(new BoulderMoveTowardsSwitch(this));
		this.isActive = new SimpleBooleanProperty(false);
		this.name = "floorSwitch";
		this.setEntityImage(new Image("images/pressure_plate.png"));
		this.setGetBombedBehavior(new GetBombedNoEffect());
	}

	public BooleanProperty isActive() {
		return isActive;
	}

	/**
	 * activate the floorswitch
	 */
	public void activate() {
		this.isActive().set(true);
		Boulder boulder = null;
		// find the boulder on top of the switch
		for (Entity e : dungeon.getEntities()) {
			if (e != null) {
				if (e.getX() == this.getX() && e.getY() == this.getY() && e.getName().equals("boulder")) {
					boulder = (Boulder) e;
				}
			}
		}
		this.setBoulderMoveTowadsBeheavior(new BoulderMoveTowardsNoWay());
		assert boulder != null;
		this.setMoveTowardsBehavior(new PlayerMoveTowardsBoulder(boulder));
		Door door = null;
		boolean found = false;
		for (Entity e : dungeon.getEntities()) {
			if (e.getName().equals("door")) {
				door = (Door) e;
				if (this.getId() == door.getId()) {
					found = true;
					break;
				}
			}
		}
		if (found) {
			// open the door
			door.changeToOpenState();
		}
	}

	/**
	 * deactivate the door with same id
	 */
	public void deactivate() {
		this.setMoveTowardsBehavior(new PlayerMoveTowardsPassThrough(this));
		this.setBoulderMoveTowadsBeheavior(new BoulderMoveTowardsSwitch(this));
		this.isActive().set(false);
		Door door = null;
		boolean found = false;
		for (Entity e : dungeon.getEntities()) {
			if (e.getName().equals("door")) {
				door = (Door) e;
				if (this.getId() == door.getId()) {
					found = true;
					break;
				}
			}
		}
		if (found) {
			door.changeToClosedState();
		}
	}

}
