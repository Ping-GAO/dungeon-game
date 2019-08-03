package unsw.dungeon;

/**
 * @author Ping GAO
 */
public class PickUpIntoBag implements PickUpBehavior {
	private BagPack bagPack;
	private Entity entity;

	public PickUpIntoBag(Entity entity) {
		this.entity = entity;
		this.bagPack = entity.getDungeon().getPlayer().getBagPack();

	}

	@Override
	public void pickUp() {
		bagPack.addToBagPack(entity);
		entity.alive().set(false);

	}

}
