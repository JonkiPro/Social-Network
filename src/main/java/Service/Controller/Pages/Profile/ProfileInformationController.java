package Service.Controller.Pages.Profile;

import Service.LoadPage.LoadPage;
import Service.Person.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Created by Jonatan on 2017-02-27.
 */
public class ProfileInformationController {

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

    public void initData() {
        labelNameAndLastName.setText(new StringBuilder(person.getName()).append(" ").append(person.getLastName()).toString());
        labelSecondName.setText(person.getSecondName());
        labelDateOfBirth.setText(person.getDateOfBirth().toString());
        labelSex.setText(person.getSex());
        labelLogin.setText(person.getLogin());
        labelEmail.setText(person.getEmail());
        labelSomeInfoAboutMe.setText(person.getAboutMe());
    }


    public void setPersonOther(Person person) { this.person = person; }

}
