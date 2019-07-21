package unsw.dungeon;

public class Sword extends Entity{

	public Sword(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.setMoveTowardsBehavior(new MoveTowardsPassThrough(this));
        this.setPickUpBehavior(new PickUpIntoBag(this));
	}

}
