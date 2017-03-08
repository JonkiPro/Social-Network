package Service.Controller.PermanentContainers;

import Builder.PopOverBuilder;
import Database.Database;
import Service.LoadPage.LoadPage;
import Service.Person.Person;
import Service.ServiceWindow;
import com.jfoenix.controls.JFXButton;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.textfield.TextFields;

/**
 * Created by Jonatan on 2017-01-17.
 */
public class ContainerOnTopController {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXButton btnSettings;
    @FXML
    private JFXButton btnProfile;
    @FXML
    private TextField textSearch;

    private Person person;

    private static Stage stage;

    private PopOver popOverProfile, popOverSettings;

    private static AnchorPane copyAnchorPane;

    @FXML
    public void initialize() {
        person = ServiceWindow.getPerson();

        TextFields.bindAutoCompletion(textSearch, Database.loadLogins());
        btnProfile.textProperty().bind(Bindings.concat(person.nameProperty(), " ", person.lastNameProperty()));
        copyAnchorPane = anchorPane;
    }

    @FXML
    public void home() {
        new LoadPage().loadHome(person);
    }

    @FXML
    public void btnSettingsAction() {
        if(popOverSettings == null) {
            popOverSettings = new PopOver();
        }

        PopOverBuilder.showSettings(popOverSettings, btnSettings, stage, person);
    }

    @FXML
    public void btnProfileEntered() {
        if(popOverProfile == null) {
            popOverProfile = new PopOver();
        }

        PopOverBuilder.showProfile(popOverProfile, btnProfile, person);
    }

    @FXML
    public void btnProfileExited() {
        popOverProfile.hide();
    }

    @FXML
    public void search() {
        new LoadPage().loadSearch(person, textSearch.getText());
    }

    @FXML
    public void goToMyProfile() {
        new LoadPage().loadMyProfile(person);
    }



    public static void setStage(Stage stage) {
        ContainerOnTopController.stage = stage;
    }

    public void setAnchorPaneWidth(double size) {
        anchorPane.setMaxWidth(size);
    }

    static public AnchorPane getCopyAnchorPane() {
        return copyAnchorPane;
    }
}
