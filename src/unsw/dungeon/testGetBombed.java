package unsw.dungeon;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;



import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


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
		assert toFind != null;
		toFind.PerformGetBombed();
		// System.out.println(toFind.alive().getValue());
		assertFalse(toFind.alive().getValue(), "after bombed boulder died");
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
		assert toFind != null;
		toFind.PerformGetBombed();
		// System.out.println(toFind.alive().getValue());
		assertTrue(toFind.alive().getValue(), "after bombed wall lived");
	}
}
