package Service.Controller;

import LoadFXML.LoadFXML;
import Service.Controller.Pages.FriendsAndGroups.FriendsController;
import Service.Controller.Pages.FriendsAndGroups.InvitationsController;
import Service.Controller.Pages.FriendsAndGroups.YourInvitationsController;
import Service.Controller.PermanentContainers.ContainerOnLeftController;
import Service.Controller.PermanentContainers.ContainerOnTitlebarController;
import Service.Controller.PermanentContainers.ContainerOnTopController;
import Service.LoadPage.LoadPage;
import Service.Person.Person;
import Service.ServiceWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Jonatan on 2017-01-16.
 */
public class ServiceWindowController {

    @FXML
    private BorderPane borderPane;
    @FXML
    private BorderPane borderPaneOnTop;

    private Person person;

    private Stage stage;
    private ContainerOnTitlebarController containerOnTitlebarController;
    private ContainerOnTopController containerOnTopController;
    private ContainerOnLeftController containerOnLeftController;

    static private final String FXML_CONTAINER_ON_TITLEBAR = "/Service/PermanentContainers/ContainerOnTitlebar.fxml";
    static private final String FXML_CONTAINER_ON_TOP = "/Service/PermanentContainers/ContainerOnTop.fxml";
    static private final String FXML_CONTAINER_ON_LEFT = "/Service/PermanentContainers/ContainerOnLeft.fxml";

    @FXML
    public void initialize() {
        person = ServiceWindow.getPerson();

        loadContainerOnTitlebar();
        loadContainerOnLeft();
        loadContainerOnTop();

        LoadPage.setServiceWindowController(this);

        new LoadPage().loadHome(person);
    }

    private void loadContainerOnTitlebar() {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_CONTAINER_ON_TITLEBAR);

        HBox hbox = null;
        try {
            hbox = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        containerOnTitlebarController = loader.getController();

        setTitlebar(hbox);
    }

    private void loadContainerOnTop() {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_CONTAINER_ON_TOP);

        AnchorPane anchorPane = null;
        try {
            anchorPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        containerOnTopController = loader.getController();

        setTop(anchorPane);
    }

    private void loadContainerOnLeft() {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_CONTAINER_ON_LEFT);

        ScrollPane scrollPane = null;
        try {
            scrollPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        containerOnLeftController = loader.getController();

        setLeft(scrollPane);
    }

    public void loadContainerOnCenter(String FXML) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML);

        Node node = null;
        try {
            node = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setCenter((Pane) node);
    }

    @FXML
    public void mouseMoved(MouseEvent e) {
        if (person.isResizeWindow()) {
            if (e.getX() > stage.getWidth() - 5 && e.getY() > 25 && e.getY() < stage.getHeight() - 5)
                borderPane.setCursor(Cursor.E_RESIZE);
            if (e.getX() > stage.getWidth() - 5 && e.getY() > stage.getHeight() - 5)
                borderPane.setCursor(Cursor.SE_RESIZE);
            if (e.getX() > 5 && e.getX() < stage.getWidth() - 5 && e.getY() > stage.getHeight() - 5)
                borderPane.setCursor(Cursor.S_RESIZE);
            if (e.getX() < 5 && e.getY() > stage.getHeight() - 5)
                borderPane.setCursor(Cursor.NE_RESIZE);
            if (e.getX() < 5 && e.getY() > 25 && e.getY() < stage.getHeight() - 5)
                borderPane.setCursor(Cursor.W_RESIZE);
            if (e.getX() < stage.getWidth() - 5 && e.getX() > 5 && e.getY() < stage.getHeight() - 5 || e.getY() < 25)
                borderPane.setCursor(Cursor.DEFAULT);
        }
    }

    @FXML
    public void mouseDragged(MouseEvent e) {
        if (person.isResizeWindow()) {
            if (borderPane.getCursor() == Cursor.E_RESIZE) {
                if (e.getX() > 115) {
                    stage.setWidth(e.getX() + 5);
                    containerOnTopController.setAnchorPaneWidth(e.getX() + 3);
                    containerOnTitlebarController.setHBoxWidth(e.getX() + 3);
                    {
                        FriendsController.setWidthPage(stage.getWidth());
                        InvitationsController.setWidthPage(stage.getWidth());
                        YourInvitationsController.setWidthPage(stage.getWidth());
                    }
                }
            } else if (borderPane.getCursor() == Cursor.SE_RESIZE) {
                if (e.getX() > 115 && e.getY() > 100) {
                    stage.setWidth(e.getX() + 5);
                    stage.setHeight(e.getY() + 5);
                    containerOnTopController.setAnchorPaneWidth(e.getX() + 3);
                    containerOnTitlebarController.setHBoxWidth(e.getX() + 3);
                    {
                        FriendsController.setWidthAndHeightPage(stage.getWidth(), stage.getHeight());
                        InvitationsController.setWidthAndHeightPage(stage.getWidth(), stage.getHeight());
                        YourInvitationsController.setWidthAndHeightPage(stage.getWidth(), stage.getHeight());
                    }
                }
            } else if (borderPane.getCursor() == Cursor.S_RESIZE) {
                if (e.getY() > 100) {
                    stage.setHeight(e.getY() + 5);
                    {
                        FriendsController.setHeightPage(stage.getHeight());
                        InvitationsController.setHeightPage(stage.getHeight());
                        YourInvitationsController.setHeightPage(stage.getHeight());
                    }
                }
            }
        }
    }

    private void setTitlebar(Pane pane) {
        borderPaneOnTop.setTop(pane);
    }

    public void setTop(Pane pane) {
        borderPaneOnTop.setBottom(pane);
    }

    public void setLeft(ScrollPane scrollPane) {
        borderPane.setLeft(scrollPane);
    }

    public void setCenter(Pane pane) {
        AnchorPane anpa = new AnchorPane();
        anpa.getChildren().add(pane);
        AnchorPane.setBottomAnchor(pane, 0d);
        AnchorPane.setTopAnchor(pane, 0d);
        AnchorPane.setLeftAnchor(pane, 0d);
        AnchorPane.setRightAnchor(pane, 0d);
        borderPane.setCenter(anpa);
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
