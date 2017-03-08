package Service.Controller.Pages.Profile;

import Service.LoadPage.LoadPage;
import Service.Person.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Created by Jonatan on 2017-02-26.
 */
public class MyProfileInformationController {

    @FXML
    private Label labelNameAndLastName;
    @FXML
    private Label labelSecondName;
    @FXML
    private Label labelDateOfBirth;
    @FXML
    private Label labelSex;
    @FXML
    private Label labelLogin;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelSomeInfoAboutMe;

    private Person person;

    @FXML
    void changeNameAndLastName() {
        new LoadPage().loadChangeNameAndLastName(person);
    }

    @FXML
    void changeSecondName() {
        new LoadPage().loadChangeNameAndLastName(person);
    }

    @FXML
    void changeDateOfBirth() {
        new LoadPage().loadChangeOther(person);
    }

    @FXML
    void changeSex() {
        new LoadPage().loadChangeOther(person);
    }

    @FXML
    void changeLogin() {
        new LoadPage().loadChangeLogin(person);
    }

    @FXML
    void changeEmail() {
        new LoadPage().loadChangeEmail(person);
    }

    @FXML
    void changeSomeInfoAboutMe() {
        new LoadPage().loadChangeAboutMe(person);
    }

    @FXML
    void changeAvatar() {
        new LoadPage().loadChangeAvatar(person);
    }

    public void initData() {
        labelNameAndLastName.setText(new StringBuilder(person.getName()).append(" ").append(person.getLastName()).toString());
        labelSecondName.setText(person.getSecondName());
        labelDateOfBirth.setText(person.getDateOfBirth().toString());
        labelSex.setText(person.getSex());
        labelLogin.setText(person.getLogin());
        labelEmail.setText(person.getEmail());
        labelSomeInfoAboutMe.setText(person.getAboutMe());
    }


    public void setPerson(Person person) { this.person = person; }

}
