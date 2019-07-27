package unsw.dungeon;

import javafx.scene.image.Image;

public class Enemy extends Entity {

	public Enemy(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.setMoveTowardsBehavior(new PlayerMoveTowardsNoWay());
        this.setPickUpBehavior(new PickUpNoWay());
        this.setBoulderMoveTowadsBeheavior(new BoulderMoveTowardsNoWay());
        this.name = "enemy";
        this.setEntityImage(new Image("images/deep_elf_master_archer.png"));
	}

}
