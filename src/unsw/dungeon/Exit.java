package unsw.dungeon;

public class Exit extends Entity {
	public Exit(Dungeon dungeon, int x, int y, String name) {
		super(dungeon, x, y);
		this.setMoveTowardsBehavior(new playerMoveTowardsExit(this));
		this.setPickUpBehavior(new PickUpNoWay());
		this.setBoulderMoveTowadsBeheavior(new boulderMoveTowardsNoWay());
		this.name = "exit";
	}
}
