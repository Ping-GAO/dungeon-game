package unsw.dungeon;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DungeonControllerLoader extends DungeonLoader {

	private List<ImageView> entities;
	private HashMap<ImageView, Entity> imageViewToEntity;
	// Images
	private Image playerImage;
	private Image wallImage;
	private Image boulderImage;
	private Image floorSwitchImage;
	private Image bombImage;
	private Image treasureImage;
	private Image invincibilityImage;
	private Image swordImage;
	private Image enemyImage;
	private Image exitImage;
	private Image doorImage;

	private Image keyImage;

	DungeonControllerLoader(String filename) throws FileNotFoundException {
		super(filename);
		entities = new ArrayList<>();
		playerImage = new Image("images/human_new.png");
		wallImage = new Image("images/brick_brown_0.png");
		boulderImage = new Image("images/boulder.png");
		floorSwitchImage = new Image("images/pressure_plate.png");
		bombImage = new Image("images/bomb_unlit.png");
		treasureImage = new Image("images/gold_pile.png");
		invincibilityImage = new Image("images/brilliant_blue_new.png");
		swordImage = new Image("images/greatsword_1_new.png");
		enemyImage = new Image("images/deep_elf_master_archer.png");
		exitImage = new Image("images/exit.png");
		doorImage = new Image("images/closed_door.png");
		keyImage = new Image("images/key.png");
		imageViewToEntity = new HashMap<>();

	}

	@Override
	public void onLoad(Entity player) {
		ImageView view = new ImageView(playerImage);
		addEntity(player, view);
	}

	@Override
	public void onLoad(Wall wall) {
		ImageView view = new ImageView(wallImage);
		addEntity(wall, view);
	}

	@Override
	public void onLoad(Boulder boulder) {
		ImageView view = new ImageView(boulderImage);
		addEntity(boulder, view);
	}

	@Override
	public void onLoad(FloorSwitch floorSwitch) {
		ImageView view = new ImageView(floorSwitchImage);
		addEntity(floorSwitch, view);
	}

	@Override
	public void onLoad(Bomb bomb) {
		ImageView view = new ImageView(bombImage);
		addEntity(bomb, view);
	}

	@Override
	public void onLoad(Treasure treasure) {
		ImageView view = new ImageView(treasureImage);
		addEntity(treasure, view);
	}

	@Override
	public void onLoad(Exit exit) {
		ImageView view = new ImageView(exitImage);
		addEntity(exit, view);
	}

	@Override
	public void onLoad(Invincibility invincibility) {
		ImageView view = new ImageView(invincibilityImage);
		addEntity(invincibility, view);
	}

	@Override
	public void onLoad(Sword sword) {
		ImageView view = new ImageView(swordImage);
		addEntity(sword, view);
	}

	@Override
	public void onLoad(Enemy enemy) {
		ImageView view = new ImageView(enemyImage);
		addEntity(enemy, view);
	}

	@Override
	public void onLoad(Door door) {
		ImageView view = new ImageView(doorImage);
		addEntity(door, view);
	}

	@Override
	public void onLoad(Key key) {
		ImageView view = new ImageView(keyImage);
		addEntity(key, view);
	}

	private void addEntity(Entity entity, ImageView view) {
		trackPosition(entity, view);
		imageViewToEntity.put(view, entity);
		entities.add(view);
	}


	private void trackPosition(Entity entity, Node node) {
		GridPane.setColumnIndex(node, entity.getX());
		GridPane.setRowIndex(node, entity.getY());
		entity.x().addListener((observable, oldValue, newValue) -> GridPane.setColumnIndex(node, newValue.intValue()));
		entity.y().addListener((observable, oldValue, newValue) -> GridPane.setRowIndex(node, newValue.intValue()));
	}


	DungeonController loadController(){
		return new DungeonController(load(), entities, imageViewToEntity);
	}

}
