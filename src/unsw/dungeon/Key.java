package unsw.dungeon;

public class Key extends Entity {

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Key(Dungeon dungeon, int x, int y, String name) {

		super(dungeon, x, y, name);
		this.setMoveTowardsBehavior(new playerMoveTowardsPassThrough(this));
		this.setPickUpBehavior(new PickUpIntoBag(this));
		this.setBoulderMoveTowadsBeheavior(new boulderMoveTowardsNoWay());

	}
}
