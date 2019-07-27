package unsw.dungeon;

public class Treasure extends Entity {

	public Treasure(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.setMoveTowardsBehavior(new PlayerMoveTowardsPassThrough(this));
        this.setPickUpBehavior(new PickUpIntoBag(this));
        this.setBoulderMoveTowadsBeheavior(new BoulderMoveTowardsNoWay());
        this.name = "treasure";
	}

}
