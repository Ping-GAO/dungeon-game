package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;

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
	}

	public BooleanProperty isActive() {
		return isActive;
	}

	public void activate() {
		this.isActive().set(true);
		// or activate a closed door
		System.out.println("foor switch activaed");
		// when activate should have a boulder at same spot
		Boulder boulder = null;
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
					// System.out.println("switch has id " + this.getId() + " door has id " +
					// door.getId());
					// System.out.println("matched!");
					found = true;
					break;
				}  // System.out.println("switch has id " + this.getId() + " door has id " +
				// door.getId());


			}
		}
		if (found) {
			door.changeToOpenState();
		}

	}

	public void deactivate() {
		System.out.println("foor switch DEactivaed");
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
				}  // System.out.println("switch has id " + this.getId() + " door has id " +
				// door.getId());


			}
		}
		if (found) {
			door.changeToClosedState();
		}
	}

}
