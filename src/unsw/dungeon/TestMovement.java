package unsw.dungeon;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestMovement {

	@Test
	void testMoveToWall() {
		Wall wall = new Wall(null, 0, 0);
		Player player = new Player(null, 0, 1);
		wall.PerformBeMovedTowardsbyPlayer();
		assertEquals(0, player.getX(), "player can't move through wall");
		assertEquals(1, player.getY(), "player can't move through wall");
	}

	@Test
	void testMoveToBoulder() throws FileNotFoundException {
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
		toFind.PerformBeMovedTowardsbyPlayer();

		assertEquals(1, toFind.getX(), "Boulder can't move becuase there are other om the way");
		assertEquals(4, toFind.getY(), "Boulder can't move becuase there are other om the way");

		for (Entity e : dungeon.getEntities()) {
			if (e != null && e.getName().equals("boulder") && e.getX() == 4 && e.getY() == 7) {
				toFind = (Boulder) e;
				break;
			}
		}
		toFind.PerformBeMovedTowardsbyPlayer();
		// System.out.println(toFind.getX() + "awea" + toFind.getY());
		assertEquals(5, toFind.getX(), "Boulder can move");
		assertEquals(7, toFind.getY(), "Boulder can move");

	}
}
