package LoginRegisterPanel.Controller;

import LoadFXML.LoadFXML;
import LoginRegisterPanel.Controller.Login.LoginController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by Jonatan on 2016-12-16.
 */
public class MainController {

    @FXML
    private BorderPane borderPane;

    private Stage stage;

    private Locale locale = new Locale("en");

    private double oldPositionStageX, oldPositionStageY, newPositionStageX, newPositionStageY;

    static private final String BUNDLE_LOGIN = "ResourceBundle.LoginRegisterReminder.Login";
    static private final String FXML_LOGIN = "/LoginRegisterPanel/Login/Login.fxml";

    @FXML
    public void initialize() {
        loadPanelLogin();
    }

    @FXML
    public void minimize() {
        stage.setIconified(true);
    }

    @FXML
    public void exit() {
        Platform.exit();
    }

    @FXML
    public void mousePressed(MouseEvent e) {
        oldPositionStageX = e.getX();
        oldPositionStageY = e.getY();
    }

    @FXML
    public void mouseDragged(MouseEvent e) {
        newPositionStageX = e.getScreenX();
        newPositionStageY = e.getScreenY();

        stage.setX(newPositionStageX - oldPositionStageX);
        stage.setY(newPositionStageY - oldPositionStageY);
    }

    @FXML
    public void mousePressedOnLabel(MouseEvent e) {
        oldPositionStageX = e.getX();
        oldPositionStageY = e.getY();
    }

    @FXML
    public void mouseDraggedOnLabel(MouseEvent e) {
        newPositionStageX = e.getScreenX();
        newPositionStageY = e.getScreenY();

        stage.setX(newPositionStageX - oldPositionStageX);
        stage.setY(newPositionStageY - oldPositionStageY);
    }

    public void loadPanelLogin() {

        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_LOGIN, BUNDLE_LOGIN, locale);

        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        LoginController loginController = loader.getController();
        loginController.setLocale(locale);
        loginController.setMainController(this);

        setScreen(pane);
    }

    public void setScreen(Pane pane) {
        borderPane.setCenter(pane);
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
