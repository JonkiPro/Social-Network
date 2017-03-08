package Service.Controller.Pages.EditMyProfile;

import Builder.NotificationBuilder;
import Database.Database;
import Service.LoadPage.LoadPage;
import Service.Person.Person;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Created by Jonatan on 2017-03-02.
 */
public class ChangeAboutMeController {

    @FXML
    private JFXTextArea textOldInfoAboutMe, textNewInfoAboutMe;

    private Person person;

    @FXML
    public void change() {
        if (Database.changeAboutMe(person.getID(), textNewInfoAboutMe.getText())) {
            person.setAboutMe(textNewInfoAboutMe.getText());
            Database.addLogger(person.getID(), "Changed information about me.");
            if (person.isNotifications()) {
                NotificationBuilder.showNotificationSuccessOperation("Change about me",
                        "About me has been changed successfully.");
            }
            new LoadPage().loadGridPaneSuccessEdition(person);
        }
    }

    @FXML
    public void back() {
        new LoadPage().loadOtherData(person);
    }

    @FXML
    public void clear() {
        textNewInfoAboutMe.setText("");
    }

    @FXML
    public void showInfo() {

    }


    public void setPerson(Person person) {
        this.person = person;
    }

    public void bindPerson() {
        textOldInfoAboutMe.textProperty().bind(person.aboutMeProperty());
    }

}
