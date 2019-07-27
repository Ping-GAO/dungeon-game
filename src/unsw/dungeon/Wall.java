package unsw.dungeon;

public class Wall extends Entity {

	public Wall(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.setMoveTowardsBehavior(new PlayerMoveTowardsNoWay());
		this.setPickUpBehavior(new PickUpNoWay());
		this.setBoulderMoveTowadsBeheavior(new BoulderMoveTowardsNoWay());
		this.setGetBombedBehavior(new GetBombedNoEffect());
		this.name="wall";
	}

}
