package Service.Controller.Pages.EditMyProfile;

import Service.LoadPage.LoadPage;
import Service.Person.Person;
import Validation.Validation;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Created by Jonatan on 2017-03-02.
 */
public class OtherDataController {

    @FXML
    private Label labelAboutMe;

    private Person person;


    @FXML
    public void changeAboutMe() {
        new LoadPage().loadChangeAboutMe(person);
    }

    @FXML
    public void changeAvatar() {
        new LoadPage().loadChangeAvatar(person);
    }


    public void bindPerson() {
        labelAboutMe.textProperty().bind(Bindings.concat(person.aboutMeProperty()));
    }

    @FXML
    public void previous() {
        new LoadPage().loadEditMyProfile(person);
    }


    public void setPerson(Person person) {
        this.person = person;
    }

}
