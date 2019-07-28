package unsw.dungeon;

public class BoulderMoveTowardsPassThrough implements BoulderMoveTowardsBehaviour {
	Entity entity;
	Boulder boulder;
	public BoulderMoveTowardsPassThrough(Entity entity) {
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
		// after a boulder move to an empty space, that empty space should  be destroyed
		Entity toRemove = null;
		for(Entity e: entity.getDungeon().getEntities()) {
				if(e.getX()==entity.getX() && e.getY()==entity.getY()&& e.getName().equals("emptySpace")) {
					toRemove = e;
				}
			
		}
		entity.getDungeon().getEntities().remove(toRemove);
	}

}
