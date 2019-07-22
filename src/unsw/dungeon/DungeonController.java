package unsw.dungeon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.sun.javafx.geom.transform.BaseTransform.Degree;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 * A JavaFX controller for the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
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

		Image pressuredPlate = new Image("/pressured_plate.png");
		ImageView view = new ImageView(pressuredPlate);
		FloorSwitch floorswitch = (FloorSwitch) imageViewToEntity.get(node);
		int index = dungeon.getEntities().indexOf(floorswitch);

		int x = floorswitch.getX();
		int y = floorswitch.getY();
		PressuredPlate p = new PressuredPlate(dungeon, x, y, "pressuredPlate");
		floorswitch.isActive().addListener(new ChangeListener<Boolean>() {
			Node temp;
			Entity tempEntity;
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

				for (Iterator<Node> i = squares.getChildren().iterator(); i.hasNext();) {
					temp = (Node) i.next();
					tempEntity = imageViewToEntity.get(temp);
					if ( tempEntity!= null) {
						if (tempEntity.getX() == x && tempEntity.getY() == y
								&& tempEntity.getName().equals("boulder")) {
							System.out.println("fond the boudler " + tempEntity.getX() + tempEntity.getY() );
							break;

						}
					}

				}
				squares.getChildren().remove(node);
				squares.getChildren().remove(temp);
				squares.add(view, x, y);
				dungeon.getEntities().set(index, p);
				Entity toRemove = null;
				for(Entity entity: dungeon.getEntities() ) {
					if(entity!=null) {
						if(entity.getX()==x && entity.getY()==y && entity.getName().equals("boulder")) {
							toRemove = entity;
						}
					}
				}
				dungeon.getEntities().remove(toRemove);
				floorswitch.acitivate();
			}
		});

		Image boudlerImage = new Image("/boulder.png");
		ImageView boudlerImageView = new ImageView(boudlerImage);
		// attach and action listener to the pressuredPlate
		p.isActive().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				squares.getChildren().remove(view);
				squares.add(boudlerImageView, x, y);
				Boulder boulder = new Boulder(dungeon, x, y, "boulder");
				System.out.println("boudler corr in tigger is " + boulder.getX() + boulder.getY());
				dungeon.getEntities().set(index, boulder);
				trackPosition(boulder, boudlerImageView);
				p.deactivate();

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
		if (player.getBagPack().getBagPack().size() != 0) {
			System.out.println("player has : " + player.getBagPack().toString());
		}
//		for (Entity e : dungeon.getEntities()) {
//			if (e != null) {
//				if (e.getX() == 6 && e.getY() == 1) {
//					System.out.println("(6,1) is "+ e.getName());
//				}
//			}
//			
//		}

	}

}
