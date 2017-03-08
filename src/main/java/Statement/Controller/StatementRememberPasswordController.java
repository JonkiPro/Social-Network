package Statement.Controller;

import FileBuilder.FileBuilder;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Created by Jonatan on 2017-01-08.
 */
public class StatementRememberPasswordController {

    private Stage stage;

    private String login, password;
    private double oldPositionStageX, oldPositionStageY;

    @FXML
    public void exit() {
        stage.close();
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

    @FXML
    public void ok() {
        FileBuilder.createFileRememberPassword(login, password);
    }

    @FXML
    public void no() {
        stage.close();
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
