package unsw.dungeon;

public class playerMoveTowardsPassThrough implements playerMoveTowardsBehavior{
	private Player player;
	private Entity entity;
	
	public playerMoveTowardsPassThrough(Entity enity) {
		
		this.entity = enity;
		this.player = entity.getDungeon().getPlayer();
	}
	@Override
	public void moveTowards() {
		player.x().set(entity.getX());
		player.y().set(entity.getY());
		
	}
	
	

}
