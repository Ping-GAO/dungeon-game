package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

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
			Player player = new Player(dungeon, x, y, "player");
			dungeon.setPlayer(player);

			entity = player;
			break;
		case "wall":
			Wall wall = new Wall(dungeon, x, y, "wall");

			entity = wall;
			break;

		case "floorSwitch":
			FloorSwitch floorSwitch = new FloorSwitch(dungeon, x, y, "floorSwitch");
			id = json.getInt("id");

			floorSwitch.setId(id);

			entity = floorSwitch;
			break;
		case "boulder":
			Boulder boulder = new Boulder(dungeon, x, y, "boulder");

			entity = boulder;
			break;

		case "bomb":
			Bomb bomb = new Bomb(dungeon, x, y, "bomb");

			entity = bomb;
			break;
		case "treasure":
			Treasure treasure = new Treasure(dungeon, x, y, "treasure");

			entity = treasure;
			break;
		case "invincibility":
			Invincibility invincibility = new Invincibility(dungeon, x, y, "invincibility");
			entity = invincibility;
			break;
		case "sword":
			Sword sword = new Sword(dungeon, x, y, "sword");

			entity = sword;
			break;
		case "enemy":
			Enemy enemy = new Enemy(dungeon, x, y, "enemy");

			entity = enemy;
			break;
		case "exit":
			Exit exit = new Exit(dungeon, x, y, "exit");

			entity = exit;
			break;
		case "door":
			Door door = new Door(dungeon, x, y, "door");
			id = json.getInt("id");

			door.setId(id);

			entity = door;
			break;
		case "key":
			Key key = new Key(dungeon, x, y, "key");
			id = json.getInt("id");
			key.setId(id);

			entity = key;
			break;

		}

		dungeon.addEntity(entity);
	}

}
