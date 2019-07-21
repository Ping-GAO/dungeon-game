package unsw.dungeon;

public class boulderMoveTowardsPassThrough implements boulderMoveTowadsBeheavior {
	Boulder boulder;
	Entity obstacle;
	public boulderMoveTowardsPassThrough(Entity obstacle) {
		
		this.obstacle = obstacle;
	}
	public void setBoulder(Boulder boulder) {
		this.boulder = boulder;
	}
	@Override
	public void moveTowards() {
		boulder.x().set(obstacle.getX());
		boulder.y().set(obstacle.getY());
	}

}
