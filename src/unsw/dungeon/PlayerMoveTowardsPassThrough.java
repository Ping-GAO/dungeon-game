package unsw.dungeon;

public class PlayerMoveTowardsPassThrough implements PlayerMoveTowardsBehavior {
	private Player player;
	private Entity entity;
	
	public PlayerMoveTowardsPassThrough(Entity enity) {
		
		this.entity = enity;
		this.player = entity.getDungeon().getPlayer();
	}
	@Override
	public void moveTowards() {
		player.x().set(entity.getX());
		player.y().set(entity.getY());
		
	}
	
	

}
