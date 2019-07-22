package unsw.dungeon;

public class Boulder extends Entity {

	public Boulder(Dungeon dungeon, int x, int y,String name) {
		super(dungeon, x, y,name);
		this.setMoveTowardsBehavior(new playerMoveTowardsBoulder(this));
        this.setPickUpBehavior(new PickUpNoWay());
        this.setBoulderMoveTowadsBeheavior(new boulderMoveTowardsNoWay());
	}

}
