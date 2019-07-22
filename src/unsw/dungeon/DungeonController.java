package unsw.dungeon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;


public class DungeonController {

	@FXML
	private GridPane squares;

	private List<ImageView> initialEntities;

	private Player player;

	private Dungeon dungeon;
	private HashMap<ImageView, Entity> imageViewToEntity;

	public DungeonController(Dungeon dungeon, List<ImageView> initialEntities,
			HashMap<ImageView, Entity> imageViewToEntity) {
		this.dungeon = dungeon;
		this.player = dungeon.getPlayer();
		this.initialEntities = new ArrayList<>(initialEntities);
		this.imageViewToEntity = imageViewToEntity;
	}

	@FXML
	public void initialize() {
		Image ground = new Image("/dirt_0_new.png");

		// Add the ground first so it is below all other entities
		for (int x = 0; x < dungeon.getWidth(); x++) {
			for (int y = 0; y < dungeon.getHeight(); y++) {
				squares.add(new ImageView(ground), x, y);
			}
		}

		for (ImageView entity : initialEntities) {
			squares.getChildren().add(entity);
			trackExistence(entity);
			if (imageViewToEntity.get(entity).getName().equals("door")) {
				trackDoorState(entity);
			} else if (imageViewToEntity.get(entity).getName().equals("floorSwitch")) {
				trackSwitchState(entity);
			}

		}
	}

	private void trackExistence(Node node) {

		imageViewToEntity.get(node).alive().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				squares.getChildren().remove(node);

			}
		});

	}

	private void trackDoorState(Node node) {

		Image doorImageOpen = new Image("/open_door.png");
		ImageView view = new ImageView(doorImageOpen);
		int x = imageViewToEntity.get(node).getX();
		int y = imageViewToEntity.get(node).getY();
		((Door) imageViewToEntity.get(node)).isOpen().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				squares.getChildren().remove(node);
				squares.add(view, x, y);

			}
		});

	}

	private void trackSwitchState(Node node) {
		FloorSwitch floorswitch = (FloorSwitch) imageViewToEntity.get(node);
		floorswitch.isActive().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				floorswitch.acitivate();
			}
		});

		

	}

	@FXML
	public void handleKeyPress(KeyEvent event) {
		switch (event.getCode()) {
		case UP:
			player.moveUp();
			break;
		case DOWN:
			player.moveDown();
			break;
		case LEFT:
			player.moveLeft();
			break;
		case RIGHT:
			player.moveRight();
			break;
		default:
			break;
		}
//		if (player.getBagPack().getBagPack().size() != 0) {
//			System.out.println("player has : " + player.getBagPack().toString());
//		}
		for (Entity e : dungeon.getEntities()) {
			if (e != null) {
				if (e.getX() == 5 && e.getY() == 2) {
					System.out.println("(5,2) is " + e.getName());
				}
			}

		}

	}

}
