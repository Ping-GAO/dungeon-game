package unsw.dungeon;



public class Bomb extends Entity {
	private  boolean  isLit;
	public Bomb(Dungeon dungeon, int x, int y,String name) {
		super(dungeon, x, y,name);
		this.setMoveTowardsBehavior(new playerMoveTowardsPassThrough(this));
        this.setPickUpBehavior(new PickUpIntoBag(this));
        this.setBoulderMoveTowadsBeheavior(new boulderMoveTowardsNoWay());
        this.isLit = false;
	}
	public boolean isLit() {
		return isLit;
	}
	
	
	public void Lit() {
		System.out.println("light it up");
		this.isLit = true;
		this.setMoveTowardsBehavior(new playerMoveTowardsNoWay());
		this.setPickUpBehavior(new PickUpNoWay());
	}
}
