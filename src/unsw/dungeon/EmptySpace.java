package unsw.dungeon;

public class EmptySpace extends Entity {

	public EmptySpace(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.setMoveTowardsBehavior(new playerMoveTowardsPassThrough(this));
		this.setPickUpBehavior(new PickUpNoWay());
		this.setBoulderMoveTowadsBeheavior(new boulderMoveTowardsPassThrough(this));
		this.setGetBombedBehavior(new GetBombedNoEffect());
		this.name= "emptySpace";
	}

}
