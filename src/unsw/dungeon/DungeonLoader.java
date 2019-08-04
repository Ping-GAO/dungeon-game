package unsw.dungeon;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author Ping GAO
 */
public abstract class DungeonLoader {

    private JSONObject json;

    DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    Dungeon load() throws FileNotFoundException {
        int width = json.getInt("width");
        int height = json.getInt("height");
        Dungeon dungeon = new Dungeon(width, height);
        JSONArray jsonEntities = json.getJSONArray("entities");
        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        return dungeon;
    }

    /**
     * @param dungeon dungeon
     * @param json    a json file
     */
    private void loadEntity(Dungeon dungeon, JSONObject json) throws FileNotFoundException {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");
        int id;
        Entity entity = null;
        switch (type) {
            case "player":
                Player player = new Player(dungeon, x, y);
                dungeon.setPlayer(player);
                onLoad(player);
                entity = player;
                break;
            case "wall":
                Wall wall = new Wall(dungeon, x, y);
                onLoad(wall);
                entity = wall;
                break;

            case "floorSwitch":
                FloorSwitch floorSwitch = new FloorSwitch(dungeon, x, y);
                id = json.getInt("id");

                floorSwitch.setId(id);
                onLoad(floorSwitch);
                entity = floorSwitch;
                break;
            case "boulder":
                Boulder boulder = new Boulder(dungeon, x, y);
                onLoad(boulder);
                entity = boulder;
                break;

            case "bomb":
                Bomb bomb = new Bomb(dungeon, x, y);
                onLoad(bomb);
                entity = bomb;
                break;
            case "treasure":
                Treasure treasure = new Treasure(dungeon, x, y);
                onLoad(treasure);
                entity = treasure;
                break;
            case "invincibility":
                Invincibility invincibility = new Invincibility(dungeon, x, y);
                onLoad(invincibility);
                entity = invincibility;
                break;
            case "sword":
                Sword sword = new Sword(dungeon, x, y);
                onLoad(sword);
                entity = sword;
                break;
            case "enemy":
                Enemy enemy = new Enemy(dungeon, x, y);
                onLoad(enemy);
                entity = enemy;
                break;
            case "exit":
                Exit exit = new Exit(dungeon, x, y);
                onLoad(exit);
                entity = exit;
                break;
            case "door":
                Door door = new Door(dungeon, x, y);
                id = json.getInt("id");
                door.setId(id);
                onLoad(door);
                entity = door;
                break;
            case "key":
                Key key = new Key(dungeon, x, y);
                id = json.getInt("id");
                key.setId(id);
                onLoad(key);
                entity = key;
                break;
            case "hole":
                Hole hole = new Hole(dungeon, x, y);
                id = json.getInt("id");
                hole.setId(id);
                onLoad(hole);
                entity = hole;
                break;
            case "gnome":
                Gnome gnome = new Gnome(dungeon, x, y);
                onLoad(gnome);
                entity = gnome;
                break;

        }

        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

    public abstract void onLoad(Boulder boulder);

    public abstract void onLoad(FloorSwitch floorSwitch);

    public abstract void onLoad(Bomb bomb);

    public abstract void onLoad(Treasure treasure);

    public abstract void onLoad(Invincibility invincibility);

    public abstract void onLoad(Sword sword);

    public abstract void onLoad(Enemy enemy);

    public abstract void onLoad(Exit exit);

    public abstract void onLoad(Door door);

    public abstract void onLoad(Key key);

    public abstract void onLoad(Hole hole);

    public abstract void onLoad(Gnome gnome);

}
