package unsw.dungeon;

public class Invincibility extends Entity{

	public Invincibility(Dungeon dungeon, int x, int y,String name) {
		super(dungeon, x, y,name);
		this.setMoveTowardsBehavior(new playerMoveTowardsPassThrough(this));
        this.setPickUpBehavior(new PickUpIntoBag(this));
        this.setBoulderMoveTowadsBeheavior(new boulderMoveTowardsNoWay());
	}

}
