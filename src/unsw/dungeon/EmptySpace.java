package unsw.dungeon;

public class EmptySpace extends Entity{

	public EmptySpace(Dungeon dungeon, int x, int y,String name) {
		super(dungeon, x, y,name);
        this.setMoveTowardsBehavior(new playerMoveTowardsPassThrough(this));
        this.setPickUpBehavior(new PickUpNoWay());
        this.setBoulderMoveTowadsBeheavior(new boulderMoveTowardsPassThrough(this));

	}

	

}
