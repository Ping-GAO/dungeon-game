package unsw.dungeon;

public class EmptySpace extends Entity{

	public EmptySpace(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
        this.setMoveTowardsBehavior(new MoveTowardsPassThrough(this));
        this.setPickUpBehavior(new PickUpNoWay());

	}

}
