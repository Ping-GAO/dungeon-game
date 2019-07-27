package unsw.dungeon;

public class Exit extends Entity {
	public Exit(Dungeon dungeon, int x, int y, String name) {
		super(dungeon, x, y);
		this.setMoveTowardsBehavior(new PlayerMoveTowardsExit(this));
		this.setPickUpBehavior(new PickUpNoWay());
		this.setBoulderMoveTowadsBeheavior(new BoulderMoveTowardsNoWay());
		this.name = "exit";
	}
}
