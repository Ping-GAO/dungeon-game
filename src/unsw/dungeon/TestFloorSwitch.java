package unsw.dungeon;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class TestFloorSwitch {

	@Test
	void testFloorSwitch() throws FileNotFoundException {
		DungeonLoderForTest dungeonLoderForTest = new DungeonLoderForTest("advanced.json");
		Dungeon dungeon = dungeonLoderForTest.load();
		FloorSwitch toFind = null;
		for (Entity e : dungeon.getEntities()) {
			if (e != null && e.getName().equals("floorSwitch")) {
				toFind = (FloorSwitch) e;
				break;
			}
		}

		Boulder toFind2 = null;
		for (Entity e : dungeon.getEntities()) {
			if (e != null && e.getName().equals("boulder") && e.getX() == 1 && e.getY() == 4) {
				toFind2 = (Boulder) e;
				break;
			}
		}
		toFind.PerformBeMovedTowardsbyBoulder(toFind2);
		// System.out.println(toFind.isActive().getValue());
		assertTrue("switch activate", toFind.isActive().getValue());
	}

}
