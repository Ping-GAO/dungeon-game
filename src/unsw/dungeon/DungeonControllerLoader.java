package unsw.dungeon;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;
    private HashMap<ImageView, Entity> imageViewToEntity;

    DungeonControllerLoader(String filename) throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        imageViewToEntity = new HashMap<>();
    }

    @Override
    public void onLoad(Entity player) {
        addEntity(player, player.MakeImageViewFromEntity());
    }

    @Override
    public void onLoad(Wall wall) {
        addEntity(wall, wall.MakeImageViewFromEntity());
    }

    @Override
    public void onLoad(Boulder boulder) {
        addEntity(boulder, boulder.MakeImageViewFromEntity());
    }

    @Override
    public void onLoad(FloorSwitch floorSwitch) {
        addEntity(floorSwitch, floorSwitch.MakeImageViewFromEntity());
    }

    @Override
    public void onLoad(Bomb bomb) {
        addEntity(bomb, bomb.MakeImageViewFromEntity());
    }

    @Override
    public void onLoad(Hole hole) {
        addEntity(hole, hole.MakeImageViewFromEntity());
    }

    @Override
    public void onLoad(Gnome gnome) {
        addEntity(gnome, gnome.MakeImageViewFromEntity());
    }

    @Override
    public void onLoad(Treasure treasure) {
        addEntity(treasure, treasure.MakeImageViewFromEntity());
    }

    @Override
    public void onLoad(Exit exit) {
        addEntity(exit, exit.MakeImageViewFromEntity());
    }

    @Override
    public void onLoad(Invincibility invincibility) {
        addEntity(invincibility, invincibility.MakeImageViewFromEntity());
    }

    @Override
    public void onLoad(Sword sword) {
        addEntity(sword, sword.MakeImageViewFromEntity());
    }

    @Override
    public void onLoad(Enemy enemy) {
        addEntity(enemy, enemy.MakeImageViewFromEntity());
    }

    @Override
    public void onLoad(Door door) {
        addEntity(door, door.MakeImageViewFromEntity());
    }

    @Override
    public void onLoad(Key key) {
        addEntity(key, key.MakeImageViewFromEntity());
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

    DungeonController loadController() {
        return new DungeonController(load(), entities, imageViewToEntity);
    }

}
