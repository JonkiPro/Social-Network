package Statement.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Created by Jonatan on 2017-01-08.
 */
public class StatementErrorController {

    @FXML
    private Hyperlink hyperlinkIDError;

    private double oldPositionStageX, oldPositionStageY;

    private Stage stage;

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
        stage.close();
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setHyperlinkIDError(String IDError) {
        this.hyperlinkIDError.setText(IDError);
    }
}
