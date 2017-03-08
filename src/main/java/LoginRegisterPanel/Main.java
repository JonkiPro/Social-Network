package LoginRegisterPanel;

import LoginRegisterPanel.Controller.Login.LoginController;
import LoginRegisterPanel.Controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by Jonatan on 2016-12-16.
 */
public class Main extends Application {

    static public void main(String[] args) {
        launch(args);
    }

    static private final String FXML_MAIN = "/LoginRegisterPanel/Main.fxml";

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_MAIN));
        StackPane stackPane = loader.load();

        Scene scene = new Scene(stackPane);

        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();

        MainController mainController = loader.getController();
        mainController.setStage(primaryStage);

        LoginController.setStage(primaryStage);
    }
}

