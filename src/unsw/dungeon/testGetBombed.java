package unsw.dungeon;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.AssertFalse.assertFalse;

class testGetBombed {

	@Test
	void testBoudlerGetBombed() throws FileNotFoundException {

		DungeonLoderForTest dungeonLoderForTest = new DungeonLoderForTest("advanced.json");
		Dungeon dungeon = dungeonLoderForTest.load();
		Boulder toFind = null;
		for (Entity e : dungeon.getEntities()) {
			if (e != null && e.getName().equals("boulder") && e.getX() == 1 && e.getY() == 4) {
				toFind = (Boulder) e;
				break;
			}
		}
		toFind.PerformGetBombed();
		// System.out.println(toFind.alive().getValue());
		assertFalse("after bombed boulder died", toFind.alive().getValue());
	}

	@Test
	void testWallGetBombed() throws FileNotFoundException {

		DungeonLoderForTest dungeonLoderForTest = new DungeonLoderForTest("advanced.json");
		Dungeon dungeon = dungeonLoderForTest.load();
		Wall toFind = null;
		for (Entity e : dungeon.getEntities()) {
			if (e != null && e.getName().equals("wall")) {
				toFind = (Wall) e;
				break;
			}
		}
		toFind.PerformGetBombed();
		// System.out.println(toFind.alive().getValue());
		assertTrue("after bombed wall lived", toFind.alive().getValue());
	}
}
