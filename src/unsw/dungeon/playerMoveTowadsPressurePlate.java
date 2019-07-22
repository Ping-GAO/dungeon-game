package unsw.dungeon;

public class playerMoveTowadsPressurePlate implements playerMoveTowardsBehavior {
	private PressuredPlate pressuredPlate;
	private Player player;
	private Dungeon dungeon;

	public playerMoveTowadsPressurePlate(PressuredPlate pressuredPlate) {
		this.player = pressuredPlate.getDungeon().getPlayer();
		this.pressuredPlate = pressuredPlate;
		this.dungeon = pressuredPlate.getDungeon();
	}

	@Override
	public void moveTowards() {
		
		int x = pressuredPlate.getX();
		int y = pressuredPlate.getY();
		pressuredPlate.isActive().set(false);

		for(Entity e : dungeon.getEntities()) {
			if(e!=null) {
				if(e.getX()==x && e.getY()==y ) {
					System.out.println("parrs lae pos "+e.getName());
				}
			}
		}
	
	}
}
