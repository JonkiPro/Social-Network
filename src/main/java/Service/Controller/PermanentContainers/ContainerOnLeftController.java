package Service.Controller.PermanentContainers;

import Service.LoadPage.LoadPage;
import Service.Person.Person;
import Service.ServiceWindow;
import com.jfoenix.controls.JFXButton;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;

/**
 * Created by Jonatan on 2017-01-19.
 */
public class ContainerOnLeftController {

    @FXML
    private Accordion accordionPostOffice;
    @FXML
    private TitledPane titledPanePostOffice;
    @FXML
    private JFXButton labelNameAndLastName;
    @FXML
    private ImageView avatar;

    private Person person;

    @FXML
    public void initialize() {
        person = ServiceWindow.getPerson();

        labelNameAndLastName.textProperty().bind(Bindings.concat(person.nameProperty(), "\n", person.lastNameProperty()));
        avatar.imageProperty().bind(person.avatarProperty());
        accordionPostOffice.setExpandedPane(titledPanePostOffice);
    }

    @FXML
    public void myProfile() {
        new LoadPage().loadMyProfile(person);
    }

    @FXML
    public void notes() {
        new LoadPage().loadNotes(person);
    }

    @FXML
    public void readMessage() {
        new LoadPage().loadReadMessage(person);
    }

    @FXML
    public void receivedMessage() {
        new LoadPage().loadReceivedMessage(person);
    }

    @FXML
    public void sentMessage() {
        new LoadPage().loadSentMessage(person);
    }

    @FXML
    public void sendMessage() {
        new LoadPage().loadSendMessage(person);
    }

    @FXML
    public void workingCopy() {
        new LoadPage().loadWorkingCopy(person);
    }

    @FXML
    public void trash() {
        new LoadPage().loadTrash(person);
    }


    @FXML
    public void news() {
        new LoadPage().loadNews(person);
    }

    @FXML
    public void recentActivity() {
        new LoadPage().loadRecentActivity(person);
    }

    @FXML
    public void events() {
        new LoadPage().loadEvent(person);
    }


    @FXML
    public void friends() {
        new LoadPage().loadFriends(person);
    }

}
