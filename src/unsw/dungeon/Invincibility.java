package unsw.dungeon;

public class Invincibility extends Entity{

	public Invincibility(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.setMoveTowardsBehavior(new MoveTowardsPassThrough(this));
        this.setPickUpBehavior(new PickUpIntoBag(this));
	}

}
