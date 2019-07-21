package unsw.dungeon;

public class Boulder extends Entity {

	public Boulder(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.setMoveTowardsBehavior(new MoveTowardsNoWay());
        this.setPickUpBehavior(new PickUpNoWay());
		
	}

}
