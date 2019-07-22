package unsw.dungeon;

public class boulderMoveTowardsPassThrough implements boulderMoveTowadsBeheavior{
	Entity entity;
	Boulder boulder;
	public boulderMoveTowardsPassThrough(Entity entity) {
		this.entity = entity;
	}
	@Override
	public void setBoulder(Boulder boulder) {
		this.boulder = boulder;
		
	}
	@Override
	public void moveTowards() {
		boulder.x().set(entity.getX());
		boulder.y().set(entity.getY());
	}
	
}
