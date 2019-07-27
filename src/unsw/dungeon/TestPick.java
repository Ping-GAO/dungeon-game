package unsw.dungeon;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestPick {

	@Test
	void testPickUpWall() {
		Wall wall = new Wall(null, 0, 0);

		wall.PerformBePickedUp();
		assertNotNull(wall);

	}

	@Test
	void testPickUpKey() throws FileNotFoundException {

		DungeonLoderForTest dungeonLoderForTest = new DungeonLoderForTest("advanced.json");
		Dungeon dungeon = dungeonLoderForTest.load();
		Key toFind = null;
		for (Entity e : dungeon.getEntities()) {
			if (e != null && e.getName().equals("key")) {
				toFind = (Key) e;
				break;
			}
		}
		assert toFind != null;
		toFind.PerformBePickedUp();
		// System.out.println(dungeon.getPlayer().getBagPack().toString());
		assertEquals("key", dungeon.getPlayer().getBagPack().toString(), "player has key");

	}

	@Test
	void testPickUpSword() throws FileNotFoundException {

		DungeonLoderForTest dungeonLoderForTest = new DungeonLoderForTest("advanced.json");
		Dungeon dungeon = dungeonLoderForTest.load();
		Sword toFind = null;
		for (Entity e : dungeon.getEntities()) {
			if (e != null && e.getName().equals("sword")) {
				toFind = (Sword) e;
				break;
			}
		}
		assert toFind != null;
		toFind.PerformBePickedUp();
		// System.out.println(dungeon.getPlayer().getBagPack().toString());
		assertEquals("sword", dungeon.getPlayer().getBagPack().toString(), "player has key");

	}
}
