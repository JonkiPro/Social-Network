package Service;

import LoadFXML.LoadFXML;
import Service.Controller.PermanentContainers.ContainerOnTitlebarController;
import Service.Controller.PermanentContainers.ContainerOnTopController;
import Service.Controller.ServiceWindowController;
import Service.LoadPage.LoadPage;
import Service.Person.Person;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Created by Jonatan on 2017-01-16.
 */
public class ServiceWindow extends Stage {

    private static Person person;

    static private final String FXML_SERVICE = "/Service/ServiceWindow.fxml";

    public ServiceWindow(Person person) {
        this.person = person;
    }

    public void start() {
        Stage primaryStage = new Stage();

        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_SERVICE);

        BorderPane borderPane = null;
        try {
            borderPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(borderPane);

        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setWidth(1000);
        primaryStage.setHeight(695);

        primaryStage.show();

        ServiceWindowController serviceWindowController = loader.getController();
        serviceWindowController.setStage(primaryStage);

        ContainerOnTitlebarController.setStage(primaryStage);
        ContainerOnTopController.setStage(primaryStage);
        LoadPage.setStage(primaryStage);
    }



    static public Person getPerson() {
        return person;
    }
}
