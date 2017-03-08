package Service.Controller.PermanentContainers;

import Service.Controller.Pages.FriendsAndGroups.FriendsController;
import Service.Controller.Pages.FriendsAndGroups.InvitationsController;
import Service.Controller.Pages.FriendsAndGroups.YourInvitationsController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Created by Jonatan on 2017-01-19.
 */
public class ContainerOnTitlebarController {

    @FXML
    private HBox hbox;

    private static Stage stage;

    private double oldPositionStageX, oldPositionStageY;
    private double oldPosX, oldPosY, width, height;
    private boolean isMaximize = false;

    private static HBox copyHbox;

    @FXML
    public void initialize() {
        copyHbox = hbox;
    }

    @FXML
    public void minimize() {
        stage.setIconified(true);
    }

    @FXML
    public void maximize_sizeDown() {
        if (!isMaximize) {
            isMaximize = true;

            oldPosX = stage.getX();
            oldPosY = stage.getY();
            width = stage.getWidth();
            height = stage.getHeight();

            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            stage.setX(bounds.getMinX());
            stage.setY(bounds.getMinY());
            stage.setWidth(bounds.getWidth());
            stage.setHeight(bounds.getHeight());

            hbox.setMaxWidth(stage.getWidth());
            ContainerOnTopController.getCopyAnchorPane().setMaxWidth(stage.getWidth());
            {
                FriendsController.setWidthAndHeightPage(stage.getWidth(), stage.getHeight());
                InvitationsController.setWidthAndHeightPage(stage.getWidth(), stage.getHeight());
                YourInvitationsController.setWidthAndHeightPage(stage.getWidth(), stage.getHeight());
            }
        } else {
            isMaximize = false;

            stage.setX(oldPosX);
            stage.setY(oldPosY);
            stage.setWidth(width);
            stage.setHeight(height);
            {
                FriendsController.setWidthAndHeightPage(stage.getWidth(), stage.getHeight());
                InvitationsController.setWidthAndHeightPage(stage.getWidth(), stage.getHeight());
                YourInvitationsController.setWidthAndHeightPage(stage.getWidth(), stage.getHeight());
            }
        }
    }

    @FXML
    public void exit() {
        Platform.exit();
    }

    @FXML
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            maximize_sizeDown();
        }
    }

    @FXML
    public void mousePressed(MouseEvent e) {
        oldPositionStageX = e.getX();
        oldPositionStageY = e.getY();
    }

    @FXML
    public void mouseDragged(MouseEvent e) {
        double newPositionStageX = e.getScreenX();
        double newPositionStageY = e.getScreenY();

        stage.setX(newPositionStageX - oldPositionStageX);
        stage.setY(newPositionStageY - oldPositionStageY);
    }


    public static void setStage(Stage stage) {
        ContainerOnTitlebarController.stage = stage;
    }

    public void setHBoxWidth(double size) {
        hbox.setMaxWidth(size);
    }

    static public HBox getCopyHbox() {
        return copyHbox;
    }
}
