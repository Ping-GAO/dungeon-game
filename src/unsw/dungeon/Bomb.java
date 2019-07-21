package unsw.dungeon;

public class Bomb extends Entity {

	public Bomb(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.setMoveTowardsBehavior(new MoveTowardsPassThrough(this));
        this.setPickUpBehavior(new PickUpIntoBag(this));
		
	}

}
