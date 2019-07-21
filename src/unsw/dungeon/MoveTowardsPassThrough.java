package unsw.dungeon;

public class MoveTowardsPassThrough implements MoveTowardsBehavior{
	private Player player;
	private Entity entity;
	
	public MoveTowardsPassThrough(Entity enity) {
		
		this.entity = enity;
		this.player = entity.getDungeon().getPlayer();
	}
	@Override
	public void moveTowards() {
		player.x().set(entity.getX());
		player.y().set(entity.getY());
		
	}

}
