package unsw.dungeon;

public class PickUpIntoBag implements PickUpBehavior {
	private BagPack bagPack;
	private Entity entity;

	public PickUpIntoBag(Entity enity) {
		this.entity = enity;
		this.bagPack = entity.getDungeon().getPlayer().getBagPack();

	}

	@Override
	public void pickUp() {
		bagPack.addToBagPack(entity);
		int index = entity.getDungeon().getEntities().indexOf(entity);
		int x_new = entity.getX();
		int y_new = entity.getY();
		entity.alive().set(false);
		// trigger to delete the imageView
		// next need to delete in entities arrayList

		entity.getDungeon().getEntities().set(index, new EmptySpace(entity.getDungeon(), x_new, y_new, "emptySpace"));

	}

}
