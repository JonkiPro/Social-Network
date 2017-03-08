package Service.Controller.Pages.EditMyProfile;

import Service.LoadPage.LoadPage;
import Service.Person.Person;
import Validation.Validation;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Created by Jonatan on 2017-01-22.
 */
public class EditMyProfileController {

    @FXML
    private Label labelNameAndLastName, labelLogin, labelPassword, labelEmail, labelQuestionAndAnswer;

    private Person person;

    @FXML
    public void changeNameAndLastName() {
        new LoadPage().loadChangeNameAndLastName(person);
    }

    @FXML
    public void changeLogin() {
        new LoadPage().loadChangeLogin(person);
    }

    @FXML
    public void changePassword() {
        new LoadPage().loadChangePassword(person);
    }

    @FXML
    public void changeEmail() {
        new LoadPage().loadChangeEmail(person);
    }

    @FXML
    public void changeQuestionAndAnswer() {
        new LoadPage().loadChangeQuestionAndAnswer(person);
    }

    @FXML
    public void changeOther() {
        new LoadPage().loadChangeOther(person);
    }

    @FXML
    public void otherData() {
        new LoadPage().loadOtherData(person);
    }


    public void setPerson(Person person) {
        this.person = person;
    }

    public void bindPerson() {
        labelNameAndLastName.textProperty().bind(Bindings.concat(person.nameProperty(), " ", person.lastNameProperty()));
        labelLogin.textProperty().bind(person.loginProperty());
        labelPassword.setText(Validation.changePasswordForStars(person.getPassword()));
        labelEmail.textProperty().bind(person.emailProperty());
        labelQuestionAndAnswer.textProperty().bind(person.leadingQuestionProperty());
    }

}
