package unsw.dungeon;

public class BoulderMoveTowardsSwitch implements BoulderMoveTowardsBehaviour {
	private Boulder boulder;
	private FloorSwitch floorSwitch;

	public BoulderMoveTowardsSwitch(FloorSwitch floorSwitch) {
		this.floorSwitch = floorSwitch;
	}

	@Override
	public void setBoulder(Boulder boulder) {
		this.boulder = boulder;
	}

	// a boulder try to move onto a switch
	@Override
	public void moveTowards() {
		boulder.x().set(floorSwitch.getX());
		boulder.y().set(floorSwitch.getY());
		floorSwitch.activate();

	}

}
