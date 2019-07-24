package unsw.dungeon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

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

				Entity entity = imageViewToEntity.get(node);
				Entity toRemove = null;
				for (Entity e : dungeon.getEntities()) {
					if (e != null) {
						if (e.getName().equals(entity.getName()) && e.getX() == entity.getX()
								&& entity.getY() == e.getY()) {
							toRemove = e;
						}
					}
				}
				dungeon.getEntities().remove(toRemove);

			}
		});

	}

	private void trackDoorState(Node node) {

		Image doorImageOpen = new Image("/open_door.png");
		ImageView view = new ImageView(doorImageOpen);
		Image doorImageClosed = new Image("/closed_door.png");
		ImageView closedDoorview = new ImageView(doorImageClosed);
		int x = imageViewToEntity.get(node).getX();
		int y = imageViewToEntity.get(node).getY();
		((Door) imageViewToEntity.get(node)).isOpen().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue.booleanValue() == true) {
					System.out.println("ative in view");
					squares.getChildren().remove(node);
					squares.add(view, x, y);
				} else {
					System.out.println("decative in view");
					squares.getChildren().remove(node);
					squares.add(closedDoorview, x, y);
				}

			}
		});

	}

	private void trackSwitchState(Node node) {
		FloorSwitch floorswitch = (FloorSwitch) imageViewToEntity.get(node);
		floorswitch.isActive().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

				if (newValue.booleanValue() == true) {
					floorswitch.acitivate();
				} else {
					floorswitch.deactivate();
				}
			}
		});

	}

	private void trackPosition(Entity entity, Node node) {
		GridPane.setColumnIndex(node, entity.getX());
		GridPane.setRowIndex(node, entity.getY());
		entity.x().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				GridPane.setColumnIndex(node, newValue.intValue());

			}
		});
		entity.y().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				GridPane.setRowIndex(node, newValue.intValue());
			}
		});
	}

	public void removeNodeByAccessHelp(String help, GridPane gridPane) {

		ObservableList<Node> childrens = gridPane.getChildren();
		for (Node node : childrens) {
			if (node != null && node.getAccessibleHelp() != null) {
				if (node instanceof ImageView && node.getAccessibleHelp().equals(help)) {
					ImageView imageView = (ImageView) node;
					gridPane.getChildren().remove(imageView);
					break;
				}
			}
		}

	}

	private ImageView getOutABombFromBagPack() {
		int x = this.dungeon.getPlayer().getX();
		int y = this.dungeon.getPlayer().getY();
		Bomb bomb = new Bomb(dungeon, x, y, "bomb");

		this.dungeon.addEntity(bomb);

		Image bombImage = new Image("/bomb_unlit.png");
		ImageView view = new ImageView(bombImage);

		squares.add(view, x, y);
		trackPosition(bomb, view);
		imageViewToEntity.put(view, bomb);
		trackExistence(view);

		Bomb toRemove = null;
		for (Entity e : this.dungeon.getPlayer().getBagPack().getBagPack()) {
			if (e.getName().equals("bomb")) {
				toRemove = (Bomb) e;
			}
		}
		this.dungeon.getPlayer().getBagPack().getBagPack().remove(toRemove);
		return view;
	}

	public void LitBomb(ImageView bombView) {
		Bomb bomb = null;
		int x = this.dungeon.getPlayer().getX();
		int y = this.dungeon.getPlayer().getY();
		EmptySpace toRemove = null;
		for (Entity e : this.dungeon.getEntities()) {
			if (e.getName().equals("bomb") && e.getX() == x && e.getY() == y) {
				bomb = (Bomb) e;
			}
			if (e.getName().equals("emptySpace") && e.getX() == x && e.getY() == y) {
				toRemove = (EmptySpace) e;
			}
		}

		this.dungeon.getEntities().remove(toRemove);
		bomb.Lit();
		bomb.setLit(true);
		squares.getChildren().remove(bombView);
		int bomb_x = bomb.getX();
		int bomb_y = bomb.getY();
		i = 1;
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event1) -> {

			String temp = "/bomb_lit_".concat(Integer.toString(i)).concat(".png");
			String tempPrev = "/bomb_lit_".concat(Integer.toString(j)).concat(".png");
			Image bombLitImage = new Image(temp);
			bombLitView = new ImageView(bombLitImage);
			bombLitView.setAccessibleHelp(temp);

			removeNodeByAccessHelp(tempPrev, squares);
			squares.add(bombLitView, x, y);

			i += 1;
			j += 1;

		}));
		timeline.setCycleCount(4);

		timeline.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// some concurrent bug here
				removeNodeByAccessHelp("bomb_lit_4.png", squares);
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						if (i == 0 && j == 0) {
							continue;
						}
						Entity e = findEntityAt(bomb_x + i, bomb_y + j);
						if (e != null) {
							e.PerformGetBombed();
						}
					}
				}
				// System.out.println("done");

			}
		});
		timeline.play();
		// try to destroy eveything in range

	}

	public Entity findEntityAt(int x, int y) {
		Entity found = null;
		for (Entity e : dungeon.getEntities()) {
			if (e.getX() == x && e.getY() == y) {
				found = e;
				return found;
			}
		}

		return found;
	}

	Integer i = 1;
	Integer j = 0;
	ImageView bombLitView = null;

	@FXML
	public void handleKeyPress(KeyEvent event) throws InterruptedException {
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
		case L:
			if (player.checkIfHaveBomb()) {

				ImageView bombView = getOutABombFromBagPack();
				LitBomb(bombView);
				Bomb bomb = (Bomb) imageViewToEntity.get(bombView);
				bomb.After();

			}
			break;
		case A:
			// press A to attack enemy in front of the player

			System.out.println("the user pressed A");
			break;
		default:
			break;
		}
		if (player.getBagPack().getBagPack().size() != 0) {
			System.out.println("player has : " + player.getBagPack().toString());
		}
//		for (Entity e : dungeon.getEntities()) {
//			if (e != null) {
//				if (e.getX() == 1 && e.getY() == 3) {
//					System.out.println("(1,3) is " + e.getName());
//				}
//			}
//
//		}

	}

}
