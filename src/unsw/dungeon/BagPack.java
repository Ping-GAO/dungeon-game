package unsw.dungeon;


import java.util.ArrayList;

public class BagPack {
    private ArrayList<Entity> bagPack;

    public BagPack() {
        bagPack = new ArrayList<>();
    }

    public ArrayList<Entity> getBagPack() {
        return bagPack;
    }

    public void addToBagPack(Entity entity) {
        bagPack.add(entity);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();


        for (int i = 0; i < bagPack.size(); i++) {
            result.append(bagPack.get(i).getName());
            if (i != bagPack.size() - 1) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    public Sword getSword() {
        Sword sword = null;
        for (Entity e : bagPack) {
            if (e.getName().equals("sword")) {
                sword = (Sword) e;
            }
        }
        return sword;
    }
}
