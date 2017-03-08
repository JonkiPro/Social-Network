package Statement;

import Statement.Controller.StatementErrorController;
import Statement.Controller.StatementRememberPasswordController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Created by Jonatan on 2017-01-08.
 */
public class Statement extends Stage{

    public void showMessageError(String IDError) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Statement/StatementError.fxml"));

        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StatementErrorController messageController = loader.getController();
        messageController.setStage(this);
        messageController.setHyperlinkIDError(IDError);

        Scene scene = new Scene(pane);

        initStyle(StageStyle.UNDECORATED);
        setAlwaysOnTop(true);
        setScene(scene);
        show();
    }

    public void showMessageRememberPassword(String login, String password) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Statement/StatementRememberPassword.fxml"));

        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StatementRememberPasswordController messageController = loader.getController();
        messageController.setStage(this);
        messageController.setLogin(login);
        messageController.setPassword(password);

        Scene scene = new Scene(pane);

        initStyle(StageStyle.UNDECORATED);
        initModality(Modality.APPLICATION_MODAL);
        setAlwaysOnTop(true);
        setScene(scene);
        show();
    }
}
