package unsw.dungeon;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class DungeonLoderForTest {

	private JSONObject json;

	public DungeonLoderForTest(String filename) throws FileNotFoundException {
		json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
	}

	public Dungeon load() {
		int width = json.getInt("width");
		int height = json.getInt("height");

		Dungeon dungeon = new Dungeon(width, height);

		JSONArray jsonEntities = json.getJSONArray("entities");

		for (int i = 0; i < jsonEntities.length(); i++) {
			loadEntity(dungeon, jsonEntities.getJSONObject(i));
		}
		return dungeon;
	}

	private void loadEntity(Dungeon dungeon, JSONObject json) {
		String type = json.getString("type");
		int x = json.getInt("x");
		int y = json.getInt("y");
		int id;
		Entity entity = null;
		switch (type) {
		case "player":
			Player player = new Player(dungeon, x, y);
			dungeon.setPlayer(player);
			entity = player;
			break;
		case "wall":
			entity = new Wall(dungeon, x, y);
			break;
		case "floorSwitch":
			FloorSwitch floorSwitch = new FloorSwitch(dungeon, x, y);
			id = json.getInt("id");
			floorSwitch.setId(id);
			entity = floorSwitch;
			break;
		case "boulder":
			entity = new Boulder(dungeon, x, y);
			break;
		case "bomb":
			entity = new Bomb(dungeon, x, y);
			break;
		case "treasure":
			entity = new Treasure(dungeon, x, y);
			break;
		case "invincibility":
			entity = new Invincibility(dungeon, x, y);
			break;
		case "sword":
			entity = new Sword(dungeon, x, y);
			break;
		case "enemy":
			entity = new Enemy(dungeon, x, y);
			break;
		case "exit":
			entity = new Exit(dungeon, x, y);
			break;
		case "door":
			Door door = new Door(dungeon, x, y);
			id = json.getInt("id");
			door.setId(id);
			entity = door;
			break;
		case "key":
			Key key = new Key(dungeon, x, y);
			id = json.getInt("id");
			key.setId(id);
			entity = key;
			break;

		}

		dungeon.addEntity(entity);
	}

}
