package Service.Controller.Pages.EditMyProfile;

import Builder.NotificationBuilder;
import Builder.PopOverBuilder;
import Database.Database;
import Service.LoadPage.LoadPage;
import Service.Person.Person;
import Validation.Validation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Created by Jonatan on 2017-01-24.
 */
public class ChangeEmailController {

    @FXML
    private JFXTextField textYourEmail, textEmail;
    @FXML
    private JFXButton btnChange;
    @FXML
    private Label labelOK;

    private Person person;

    @FXML
    public boolean checkCorrectness() {
        if (Validation.checkEmail(textEmail.getText())) {
            if (Database.checkEmail(textEmail.getText())) {
                btnChange.setDisable(false);
                labelOK.setVisible(true);
            } else {
                PopOverBuilder.showStatementValidation(textEmail, "E-mail exist in the database.");
                btnChange.setDisable(true);
                labelOK.setVisible(false);

                return false;
            }
        } else {
            if (Validation.checkLengthEmail(textEmail.getText())) {
                PopOverBuilder.showStatementValidation(textEmail, "Wrong length!");
            } else {
                PopOverBuilder.showStatementValidation(textEmail, "Wrong syntax!");
            }

            btnChange.setDisable(true);
            labelOK.setVisible(false);

            return false;
        }

        return true;
    }

    @FXML
    public void change() {
        if (!checkCorrectness()) {
            btnChange.setDisable(true);
        } else {
            if (Database.changeEmail(person.getID(), textEmail.getText())) {
                person.setEmail(textEmail.getText());
                Database.addLogger(person.getID(), "Changed e-mail.");
                if (person.isNotifications())
                    NotificationBuilder.showNotificationSuccessOperation("Change e-mail",
                            "E-mail has been changed successfully.");

                new LoadPage().loadGridPaneSuccessEdition(person);
            }
        }
    }

    @FXML
    public void back() {
        new LoadPage().loadEditMyProfile(person);
    }

    @FXML
    public void clear() {
        textEmail.setText("");
        if (labelOK.isVisible())
            labelOK.setVisible(false);
        if (!btnChange.isDisable())
            btnChange.setDisable(true);
    }


    public void setPerson(Person person) {
        this.person = person;
    }

    public void bindPerson() {
        textYourEmail.textProperty().bind(person.emailProperty());
    }
}
