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

		entity.alive().set(false);

	}

}
