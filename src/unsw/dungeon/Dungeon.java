package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ping GAO
 */
public class Dungeon {

	private int width, height;
	private List<Entity> entities;
    private String filePath;
	
	private Player player;

	/**
	 * @param width  width
	 * @param height height
	 */
	public Dungeon(int width, int height) {
		this.width = width;
		this.height = height;
		this.entities = new ArrayList<>();
		this.player = null;


	}

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void killPlayer() {
		this.player = null;
	}


}
