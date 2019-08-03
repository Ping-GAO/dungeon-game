package unsw.dungeon;

import javafx.scene.image.Image;

/**
 * @author Ping GAO
 */
public class Exit extends Entity {
	public Exit(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.setMoveTowardsBehavior(new PlayerMoveTowardsExit(this));
		this.setPickUpBehavior(new PickUpNoWay());
		this.setBoulderMoveTowadsBeheavior(new BoulderMoveTowardsNoWay());
		this.name = "exit";
		this.setEntityImage(new Image("images/exit.png"));
	}
}
