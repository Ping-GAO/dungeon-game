package unsw.dungeon;



public class FloorSwitch extends Entity {
	
	public FloorSwitch(Dungeon dungeon, int x, int y,String name) {
		super(dungeon, x, y,name);
		this.setMoveTowardsBehavior(new playerMoveTowardsPassThrough(this));
        this.setPickUpBehavior(new PickUpNoWay());
       
	}
	
	
}
