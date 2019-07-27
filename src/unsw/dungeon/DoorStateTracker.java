package unsw.dungeon;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class DoorStateTracker {
    private ImageView currentView;
    private ImageView closedDoorview;
    private ImageView openDoorview;
    private GridPane squares;
    int x, y;

    public DoorStateTracker(ImageView imageView, GridPane squares) {
        this.currentView = imageView;
        this.x = (int) imageView.getX();
        this.y = (int) imageView.getY();
        Image doorImageOpen = new Image("images/open_door.png");
        openDoorview = new javafx.scene.image.ImageView(doorImageOpen);
        Image doorImageClosed = new Image("images/closed_door.png");
        closedDoorview = new ImageView(doorImageClosed);
        this.squares = squares;
    }

    public void toOpenState() {
        squares.getChildren().remove(currentView);
        squares.add(openDoorview, x, y);
        this.setCurrentView(openDoorview);
    }

    public void toClosedState() {
        squares.getChildren().remove(currentView);
        squares.add(closedDoorview, x, y);
        this.setCurrentView(closedDoorview);
    }

    public void setCurrentView(ImageView currentView) {
        this.currentView = currentView;
    }
}
